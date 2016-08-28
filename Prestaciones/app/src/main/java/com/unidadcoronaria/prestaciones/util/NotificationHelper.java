package com.unidadcoronaria.prestaciones.util;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.globallogic.recepcionvirtual.R;


/**
 * Created by Facundo Mengoni on 5/18/2015.
 * GlobalLogic | facundo.mengoni@globallogic.com
 */
public class NotificationHelper {
    private static int mNotificationId = 1;

    public static void notifyPersonFromGCM(Context aContext, String aTitle, String aMessage) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(aContext)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(aTitle)
                        .setContentText(aMessage)
                        .setAutoCancel(true);
        /*Intent resultIntent = CouponDetailActivity.generateIntent(aContext, aCode);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        aContext,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        */

        NotificationManager mNotifyMgr = (NotificationManager) aContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId++, mBuilder.build());
    }
}