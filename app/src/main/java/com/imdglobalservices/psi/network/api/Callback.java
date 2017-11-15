package com.imdglobalservices.psi.network.api;

import com.google.gson.Gson;
import com.imdglobalservices.psi.PSIApplication;
import com.imdglobalservices.psi.R;
import com.imdglobalservices.psi.network.base.BaseResponse;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public abstract class Callback<T extends BaseResponse> implements retrofit2.Callback<T> {

    private int FAILED_CODE = 600;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            isSuccessful(response);
        } else {
            isFailed(response);
        }
    }

    private void isSuccessful(Response<T> response) {
        if (response.body() != null) {
            if (response.body().getMessage() == null) {
                onSuccess(response.code(), response.body());
            } else {
                onFailed(response.code(), response.body().getMessage());
            }
        } else {
            onFailed(response.code(), PSIApplication.context.getString(R.string.error_received));
        }
    }

    private void isFailed(Response<T> response) {
        Gson gson = new Gson();
        try {
            BaseResponse body = gson.fromJson(response.errorBody().string(), BaseResponse.class);
            if (body != null) {
                onFailed(response.code(), body.getMessage());
            } else {
                onFailed(response.code(), PSIApplication.context.getString(R.string.error_unknown));
            }
        } catch (Exception e) {
            onFailed(response.code(), PSIApplication.context.getString(R.string.error_failed));
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailed(FAILED_CODE, t.getMessage());
    }

    public abstract void onSuccess(int code, T body);

    public abstract void onFailed(int code, String message);

}
