package com.unidadcoronaria.prestaciones.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.activity.MainActivity;
import com.unidadcoronaria.prestaciones.app.activity.MedicalServiceDetailActivity;
import com.unidadcoronaria.prestaciones.app.fragment.ListMedicalServicePendingFragment;

import java.util.Random;


/**
 * Created by Agustin.Bala
 */
public class NotificationHelper {


    public static void showNotification(Context context, String type, String id) {


        String text;
        if(type.equals("MSJ")){
            text = "Recibiste un nuevo mensaje.";
        } else {
            if (type.equals("PRC")) {
                text = "Una prestación fue actualizada.";
            } else {
                text = "Se te asignó una nueva prestación.";
            }
        }
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_icon)
                .setContentTitle(context.getString(R.string.app_name))
                .setAutoCancel(true).setContentText(text);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        Intent resultIntent;
        if(type.equals("MSJ")){
            resultIntent= new Intent(context, MainActivity.class);
            resultIntent.putExtra(Constants.NOTIFICATION_TYPE,  "MSJ");
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            stackBuilder.addParentStack(MainActivity.class);
            resultIntent.setType( id);
            stackBuilder.addNextIntent(resultIntent);
        } else {
            resultIntent = new Intent(context, MedicalServiceDetailActivity.class);
            resultIntent.putExtra(Constants.MEDICAL_SERVICE_KEY,  id);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            resultIntent.setType( id);
            stackBuilder.addParentStack(MedicalServiceDetailActivity.class);
            stackBuilder.addNextIntent(resultIntent);
        }

        builder.setContentIntent(stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;

        mNotificationManager.notify(Integer.parseInt(id), notification);
    }

    public static void dismissNotification(Context context, int id){
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(id);
    }
}