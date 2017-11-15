package com.imdglobalservices.psi.network.api;

import com.imdglobalservices.psi.utils.PSIPreference;
import com.imdglobalservices.psi.utils.PSIRequestConstant;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public class APIDomain {
    public static String PRODUCTION_DOMAIN = "https://api.data.gov.sg/";
    public static String DEVELOPMENT_DOMAIN = "https://api.data.gov.sg/";
    public static String STAGING_DOMAIN = "https://api.data.gov.sg/";
    public static String VERSION = "v1/";
    public static String ENVIRONMENT = "environment/";

    public final static String PSI = "psi";

    /**
     * this method use for get name of domain
     *
     * @return domain
     */
    public static String getDomain() {
        return (PSIPreference.getPref(PSIPreference.PreferenceName.ENV).compareToIgnoreCase(PSIRequestConstant.API_DEVELOPMENT) == 0) ?
                DEVELOPMENT_DOMAIN :
                ((PSIPreference.getPref(PSIPreference.PreferenceName.ENV).compareToIgnoreCase(PSIRequestConstant.API_STAGING) == 0) ? STAGING_DOMAIN : PRODUCTION_DOMAIN);
    }

    /**
     * this method use for get base url
     *
     * @return API Base URL
     */
    public static String getApiBaseURL() {
        return getDomain() + VERSION + ENVIRONMENT;
    }
}
