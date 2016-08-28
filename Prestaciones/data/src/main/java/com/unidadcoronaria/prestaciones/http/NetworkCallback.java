package com.unidadcoronaria.prestaciones.http;

import android.util.Log;
import com.koushikdutta.async.future.FutureCallback;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class NetworkCallback<T> implements FutureCallback<T> {

    private static final String TAG = NetworkCallback.class.getSimpleName();

    private SuccessFailureCallback<T> callback;

    public NetworkCallback(SuccessFailureCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onCompleted(Exception e, T result) {
        if (result != null) {
            try {
                callback.onSuccess(result);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        } else {
            callback.onFailure(e.getMessage());
            Log.e(TAG, String.format("Error in request: %s ", e.getMessage()));
        }
    }
}
