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
import com.unidadcoronaria.prestaciones.app.activity.MedicalServiceDetailActivity;
import com.unidadcoronaria.prestaciones.app.fragment.ListMedicalServicePendingFragment;

import java.util.Random;


/**
 * Created by Agustin.Bala
 */
public class NotificationHelper {

    private static int mNotificationId = 1;
    public static final String NOTIFICATION_MEDICAL_SERVICE_ID_KEY =  "NOTIFICATION_MEDICAL_SERVICE_ID_KEY";

    public static void showNotification(Context context, String text, MedicalService medicalService) {

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.app_name))
                .setAutoCancel(true).setContentText(text);

        Intent resultIntent = new Intent(context, MedicalServiceDetailActivity.class);
        resultIntent.setType( String.valueOf(new Random().nextInt(50) + 1));
        resultIntent.putExtra(ListMedicalServicePendingFragment.MEDICAL_SERVICE_KEY,  medicalService);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MedicalServiceDetailActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        builder.setContentIntent(stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;

        mNotificationManager.notify(mNotificationId, notification);
    }

    public static void dismissNotification(Context context, int operation){
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(mNotificationId);
    }
}