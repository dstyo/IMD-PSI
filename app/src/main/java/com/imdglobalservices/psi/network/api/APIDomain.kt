package com.imdglobalservices.psi.network.api

import com.imdglobalservices.psi.utils.PSIPreference
import com.imdglobalservices.psi.utils.PSIRequestConstant

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
object APIDomain {
    var PRODUCTION_DOMAIN = "https://api.data.gov.sg/"
    var DEVELOPMENT_DOMAIN = "https://api.data.gov.sg/"
    var STAGING_DOMAIN = "https://api.data.gov.sg/"
    var VERSION = "v1/"
    var ENVIRONMENT = "environment/"

    const val PSI = "psi"

    /**
     * this method use for get name of domain
     *
     * @return domain
     */
    val domain: String
        get() = when {
            PSIPreference.getPref(PSIPreference.PreferenceName.ENV).compareTo(PSIRequestConstant.API_DEVELOPMENT, ignoreCase = true) == 0 -> DEVELOPMENT_DOMAIN
            PSIPreference.getPref(PSIPreference.PreferenceName.ENV).compareTo(PSIRequestConstant.API_STAGING, ignoreCase = true) == 0 -> STAGING_DOMAIN
            else -> PRODUCTION_DOMAIN
        }

    /**
     * this method use for get base url
     *
     * @return API Base URL
     */
    val apiBaseURL: String
        get() = domain + VERSION + ENVIRONMENT
}
