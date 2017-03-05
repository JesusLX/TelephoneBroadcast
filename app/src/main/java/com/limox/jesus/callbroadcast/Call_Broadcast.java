package com.limox.jesus.callbroadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.CallLog;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telecom.Call;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

/**
 * Created by usuario on 15/02/17.
 */

public class Call_Broadcast extends BroadcastReceiver{

    private static final int CALLNOTIFICATION = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Intent intent1 = new Intent(context,Call_Activity.class);
                intent1.putExtra("number",number);
                PendingIntent pendingIntent = PendingIntent.getActivity(context,CALLNOTIFICATION,intent1,PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setContentTitle("Hola caracola");
                builder.setContentText("New call from "+number);
                builder.setSmallIcon(R.mipmap.ic_launcher);


                builder.setVibrate(new long[]{1000, 10000, 100});
                builder.setDefaults(Notification.DEFAULT_VIBRATE);
                builder.setDefaults(Notification.DEFAULT_LIGHTS);
                builder.setAutoCancel(true);
                builder.setContentIntent(pendingIntent);
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(CALLNOTIFICATION,builder.build());
            }
        }

    }
}
