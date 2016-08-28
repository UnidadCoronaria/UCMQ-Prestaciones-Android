package com.unidadcoronaria.prestaciones.http;

import android.content.Context;
import android.util.Log;
import com.globallogic.recepcionvirtual.data.CommonsUtil;
import com.globallogic.recepcionvirtual.data.entity.GuestEntity;
import com.globallogic.recepcionvirtual.data.entity.RegistrationEntity;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpPost;
import com.koushikdutta.ion.Ion;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class NetworkClient {

    private final static String HOST = "http://172.17.201.152/vreceptionist/";

    private static final String TAG = NetworkClient.class.getSimpleName();

    public static void registerUser(final SuccessFailureCallback<RegistrationEntity> callBack, Context context, String mail, String registrationId) {

        String url = HOST.concat("user/").concat(mail).concat("/mobile/register");
        JsonObject json = new JsonObject();
        json.addProperty("mail", mail);
        json.addProperty("deviceId", CommonsUtil.getWifiMacAddress(context));
        json.addProperty("registrationId", registrationId);
        json.addProperty("applicationVersion", CommonsUtil.getAppVersion(context).toString());
        Log.i(TAG, "Json: " + json.toString());
        Ion.with(context).load(AsyncHttpPost.METHOD, url).setJsonObjectBody(json).as(new TypeToken<RegistrationEntity>(){}).setCallback(new NetworkCallback(callBack));

    }

    public static void getGuests(final SuccessFailureCallback<List<GuestEntity>> callback, Context context){
        String url= HOST.concat("api/guests-today");
        Ion.with(context).load(AsyncHttpGet.METHOD, url).as(new TypeToken<List<GuestEntity>>(){}).setCallback(new NetworkCallback(callback));
    }

    public static void markAsAttended(final SuccessFailureCallback<Void> callBack, Context context, String attender, Long guestId) {
        String url = HOST.concat("attention").concat("/").concat(guestId.toString());
        JsonObject json = new JsonObject();
        json.addProperty("emailAttender", attender);
        Log.i(TAG, "Json: " + json.toString());
        Ion.with(context).load(AsyncHttpPost.METHOD, url).setJsonObjectBody(json).as(new TypeToken<Void>(){}).setCallback(new NetworkCallback(callBack));
    }

    public static void logout(final SuccessFailureCallback<Void> callBack, Context context, String mail) {

    }
}
