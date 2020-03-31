package bd.com.nabdroid.medicationreminder;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;


public class NotificationHelper extends ContextWrapper {
    public static final String ch1id = "channel1ID";
    public static final String ch1Name = "Channel1 Name";
    public static final String ch2id = "channel2ID";
    public static final String ch2Name = "Channel2 Name";
    public static final String ch3id = "channel1ID";
    public static final String ch3Name = "Channel3 Name";


    private NotificationManager mManager;
    Context context;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
        context = base;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel1 = new NotificationChannel(ch1id, ch1Name, NotificationManager.IMPORTANCE_HIGH);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        getManager().createNotificationChannel(channel1);

        NotificationChannel channel2 = new NotificationChannel(ch2id, ch2Name, NotificationManager.IMPORTANCE_HIGH);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        getManager().createNotificationChannel(channel2);

        NotificationChannel channel3 = new NotificationChannel(ch3id, ch3Name, NotificationManager.IMPORTANCE_HIGH);
        channel3.enableLights(true);
        channel3.enableVibration(true);
        getManager().createNotificationChannel(channel3);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannel1Notification() {
        Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Intent clickOnNotificationIntent = new Intent(this, MainActivity.class);
        PendingIntent onClickpendingIntent = PendingIntent.getActivity(this, 1, clickOnNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), ch1id)
                .setContentTitle("Good morning")
                .setContentText("Take morning medication")
                .setSmallIcon(R.drawable.ic_one)
                .setAutoCancel(true)
                .setContentIntent(onClickpendingIntent)
                .setSound(ringToneUri);
    }

    public NotificationCompat.Builder getChannel2Notification() {
        Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Intent clickOnNotificationIntent = new Intent(this, MainActivity.class);
        PendingIntent onClickpendingIntent = PendingIntent.getActivity(this, 1, clickOnNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), ch2id)
                .setContentTitle("Good evening")
                .setContentText("Take evening medication")
                .setSmallIcon(R.drawable.ic_two)
                .setAutoCancel(true)
                .setContentIntent(onClickpendingIntent)
                .setSound(ringToneUri);
    }

    public NotificationCompat.Builder getChannel3Notification() {
        Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Intent clickOnNotificationIntent = new Intent(this, MainActivity.class);
        PendingIntent onClickpendingIntent = PendingIntent.getActivity(this, 1, clickOnNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), ch3id)
                .setContentTitle("Good Night")
                .setContentText("Take night medication")
                .setSmallIcon(R.drawable.ic_three)
                .setAutoCancel(true)
                .setContentIntent(onClickpendingIntent)
                .setSound(ringToneUri);
    }
}
