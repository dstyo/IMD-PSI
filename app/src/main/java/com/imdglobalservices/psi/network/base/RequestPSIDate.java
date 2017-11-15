package com.imdglobalservices.psi.network.base;

import com.imdglobalservices.psi.models.PSIDate;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public class RequestPSIDate {
    public static class Request {
        public String date;

        public Request(String date) {
            this.date = date;
        }
    }

    public static class Response extends PSIDate {
    }
}
