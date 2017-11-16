package com.imdglobalservices.psi.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.imdglobalservices.psi.R
import com.imdglobalservices.psi.database.PSIDatabaseImplementation
import com.imdglobalservices.psi.fragment.PSIMapsFragment
import com.imdglobalservices.psi.fragment.PSIStatisticFragment
import com.imdglobalservices.psi.network.PSIApiImplementation
import com.imdglobalservices.psi.network.api.Callback
import com.imdglobalservices.psi.network.base.RequestPSIDate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_frame.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var context: Context? = null
    private var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        initView()
        initData()
        initialize()
    }

    /**
     * this method used for initialize view on this activity
     */
    @Suppress("DEPRECATION")
    @SuppressLint("SimpleDateFormat")
    private fun initView() {
        setSupportActionBar(toolbar)
        title = getString(R.string.title_main_activity)

        toolbar.let {
            titleColor = ContextCompat.getColor(this, R.color.colorWhite)
        }

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawer_layout?.setDrawerListener(toggle)
        toggle.syncState()
        nav_view?.setNavigationItemSelectedListener(this)
        nav_view?.setCheckedItem(R.id.nav_map)

    }

    /**
     * this method used for initialize fragment
     */
    private fun initData() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_content, PSIMapsFragment())
                .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        when {
            drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(GravityCompat.START)
            else -> super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.nav_map -> switchContent(PSIMapsFragment())
            R.id.nav_statistic -> switchContent(PSIStatisticFragment())
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * this method using by activity to switch content fragment
     *
     * @param fragment Fragment
     */
    private fun switchContent(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_content, fragment)
                .commit()
    }

    /**
     * this method for initialize data request to api
     */
    @SuppressLint("SimpleDateFormat")
    private fun initialize() {
        val df = SimpleDateFormat("yyyy-MM-dd")
        val dfTime = SimpleDateFormat("hh:mm:ss")
        val date = df.format(Date())
        val time = dfTime.format(Date())

        val request = RequestPSIDate.Request(date)
        PSIApiImplementation.getInstance(context).getPsiByDates(request, object : Callback<RequestPSIDate.Response>() {
            override fun onSuccess(code: Int, body: RequestPSIDate.Response?) {
                PSIDatabaseImplementation.savePsiDate(body)
                getPSIByDateTimes(date, time)
            }

            override fun onFailed(code: Int, message: String?) {
                showAlertDialogMessage(R.string.default_error, message)
            }
        })
    }

    private fun getPSIByDateTimes(date: String, time: String) {
        try {
            val request = RequestPSIDate.Request(date + "T" + time)
            PSIApiImplementation.getInstance(context).getPsiByDateTimes(request, object : Callback<RequestPSIDate.Response>() {
                override fun onSuccess(code: Int, body: RequestPSIDate.Response?) {
                    PSIDatabaseImplementation.savePsiDateTime(body)
                }

                override fun onFailed(code: Int, message: String?) {
                    showAlertDialogMessage(R.string.default_error, message)

                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            showAlertDialogMessage(R.string.default_error, getString(R.string.error_unknown))
        }

    }

    private fun showAlertDialogMessage(@StringRes title: Int, message: String?) {
        AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(R.string.positive_button_retry) { dialog, which -> initialize() }
                .setNegativeButton(R.string.negative_button_close) { dialog, which -> System.exit(0) }
                .setCancelable(false)
                .create()
                .show()
    }

}
