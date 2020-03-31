package bd.com.nabdroid.medicationreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = 0;

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            id = bundle.getInt("id");
        }


        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(10000);
//        Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//        Ringtone ringtone = RingtoneManager.getRingtone(context, ringToneUri);
//        ringtone.play();

        if (id == 10){
            NotificationHelper morningNotificationHelper = new NotificationHelper(context);
            NotificationCompat.Builder morningNb = morningNotificationHelper.getChannel1Notification();
            morningNotificationHelper.getManager().notify(1, morningNb.build());
        }

        else if (id == 20){
            NotificationHelper eveningNotificationHelper = new NotificationHelper(context);
            NotificationCompat.Builder eveningNb = eveningNotificationHelper.getChannel2Notification();
            eveningNotificationHelper.getManager().notify(2, eveningNb.build());
        }

        else if (id == 30){
            NotificationHelper nightNotificationHelper = new NotificationHelper(context);
            NotificationCompat.Builder nightNb = nightNotificationHelper.getChannel3Notification();
            nightNotificationHelper.getManager().notify(3, nightNb.build());
        }

    }
}
