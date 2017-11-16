package com.imdglobalservices.psi.network.base

import com.imdglobalservices.psi.models.PSIDate

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
class RequestPSIDate {
    class Request(var date: String)

    class Response : PSIDate()
}
