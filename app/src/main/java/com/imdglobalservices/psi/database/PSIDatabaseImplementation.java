package com.imdglobalservices.psi.database;

import com.google.gson.reflect.TypeToken;
import com.imdglobalservices.psi.models.PSIDate;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class PSIDatabaseImplementation {

    /**
     * this method used for save psi by date
     *
     * @param psiByDate
     * @return insert or update data PSI
     */
    public static boolean savePsiDate(PSIDate psiByDate) {
        return PSIQuery.saveDataLocalPSI(PSIQuery.Type.PSI_DATE, psiByDate);
    }

    /**
     * this method used for save psi by date and times
     *
     * @param psiByDateTime
     * @return insert or update data PSI
     */
    public static boolean savePsiDateTime(PSIDate psiByDateTime) {
        return PSIQuery.saveDataLocalPSI(PSIQuery.Type.PSI_DATETIME, psiByDateTime);
    }

    /**
     * this method used for get data psi by date
     *
     * @return local data psi
     */
    public static PSIDate getPsiDate() {
        return PSIQuery.getDataLocalPSI(PSIQuery.Type.PSI_DATE, new TypeToken<PSIDate>() {
        }.getType());
    }

    /**
     * this method used get data psi by date and times
     *
     * @return local data psi
     */
    public static PSIDate getPsiDateTime() {
        return PSIQuery.getDataLocalPSI(PSIQuery.Type.PSI_DATETIME, new TypeToken<PSIDate>() {
        }.getType());
    }

    /**
     * this method used for clear all cache
     *
     */
    public static void clearAllCache() {
        PSIQuery.clearAllCache();
    }

    /**
     * this method used for clear some cache
     *
     * @param type name of cache
     */
    public static void clearCache(String type) {
        PSIQuery.clearCache(type);
    }
}
