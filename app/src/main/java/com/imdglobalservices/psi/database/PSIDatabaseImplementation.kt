package com.imdglobalservices.psi.database

import com.google.gson.reflect.TypeToken
import com.imdglobalservices.psi.models.PSIDate
import com.imdglobalservices.psi.network.base.RequestPSIDate

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
object PSIDatabaseImplementation {

    /**
     * this method used for get data psi by date
     *
     * @return local data psi
     */
    val psiDate: PSIDate?
        get() = PSIQuery.getDataLocalPSI(PSIQuery.Type.PSI_DATE, object : TypeToken<PSIDate>() {}.type)

    /**
     * this method used get data psi by date and times
     *
     * @return local data psi
     */
    val psiDateTime: PSIDate?
        get() = PSIQuery.getDataLocalPSI(PSIQuery.Type.PSI_DATETIME, object : TypeToken<PSIDate>() {}.type)

    /**
     * this method used for save psi by date
     *
     * @param psiByDate
     * @return insert or update data PSI
     */
    fun savePsiDate(psiByDate: RequestPSIDate.Response?): Boolean {
        return PSIQuery.saveDataLocalPSI(PSIQuery.Type.PSI_DATE, psiByDate)
    }

    /**
     * this method used for save psi by date and times
     *
     * @param psiByDateTime
     * @return insert or update data PSI
     */
    fun savePsiDateTime(psiByDateTime: RequestPSIDate.Response?): Boolean {
        return PSIQuery.saveDataLocalPSI(PSIQuery.Type.PSI_DATETIME, psiByDateTime)
    }

}
