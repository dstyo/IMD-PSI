package com.imdglobalservices.psi.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.imdglobalservices.psi.R;
import com.imdglobalservices.psi.database.PSIDatabaseImplementation;
import com.imdglobalservices.psi.fragment.PSIMapsFragment;
import com.imdglobalservices.psi.fragment.PSIStatisticFragment;
import com.imdglobalservices.psi.network.PSIApiImplementation;
import com.imdglobalservices.psi.network.api.Callback;
import com.imdglobalservices.psi.network.base.RequestPSIDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        initData();
        initialize();
    }

    /**
     * this method used for initialize view on this activity
     */
    private void initView() {
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        date = df.format(new Date());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.title_main_activity) + " " + date);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_map);

    }

    /**
     * this method used for initialize fragment
     */
    private void initData() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content, new PSIMapsFragment())
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            switchContent(new PSIMapsFragment());
        } else if (id == R.id.nav_statistic) {
            switchContent(new PSIStatisticFragment());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * this method using by activity to switch content fragment
     *
     * @param fragment Fragment
     */
    public void switchContent(final Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content, fragment)
                .commit();
    }

    /**
     * this method for initialize data request to api
     */
    private void initialize() {
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat")
        DateFormat dfTime = new SimpleDateFormat("hh:mm:ss");
        final String date = df.format(new Date());
        final String time = dfTime.format(new Date());

        RequestPSIDate.Request request = new RequestPSIDate.Request(date);
        PSIApiImplementation.getInstance(context).getPsiByDates(request, new Callback<RequestPSIDate.Response>() {
            @Override
            public void onSuccess(int code, RequestPSIDate.Response body) {
                PSIDatabaseImplementation.savePsiDate(body);
                getPSIByDateTimes(date, time);
            }

            @Override
            public void onFailed(int code, String message) {
                showAlertDialogMessage(R.string.default_error, message);
            }
        });
    }

    private void getPSIByDateTimes(String date, String time) {
        try {
            RequestPSIDate.Request request = new RequestPSIDate.Request(date + "T" + time);
            PSIApiImplementation.getInstance(context).getPsiByDateTimes(request, new Callback<RequestPSIDate.Response>() {
                @Override
                public void onSuccess(int code, RequestPSIDate.Response body) {
                    PSIDatabaseImplementation.savePsiDateTime(body);
                }

                @Override
                public void onFailed(int code, String message) {
                    showAlertDialogMessage(R.string.default_error, message);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showAlertDialogMessage(R.string.default_error, getString(R.string.error_unknown));
        }
    }

    private void showAlertDialogMessage(@StringRes int title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(R.string.positive_button_retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initialize();
                    }
                })
                .setNegativeButton(R.string.negative_button_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

}
