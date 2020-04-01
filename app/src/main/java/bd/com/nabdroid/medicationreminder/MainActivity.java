package bd.com.nabdroid.medicationreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView sundayTV, mondayTV, tuesdayTV, wednesdayTV, thursdayTV, fridayTV, saturdayTV;
    private TextView alarmOneTV, alarmTwoTV, alarmThreeTV;
    private Long morningTimeLong, eveningTimeLong, nightTimeLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getDataFromSharedPreferences();
        setMorningAlarm(1);
        setEveningAlarm(2);
        setNightAlarm(3);

        changeDateColors();
    }


    private void init() {
        sundayTV = findViewById(R.id.sundayTV);
        mondayTV = findViewById(R.id.mondayTV);
        tuesdayTV = findViewById(R.id.tuesdayTV);
        wednesdayTV = findViewById(R.id.wednesdayTV);
        thursdayTV = findViewById(R.id.thursdayTV);
        fridayTV = findViewById(R.id.fridayTV);
        saturdayTV = findViewById(R.id.saturdayTV);
        alarmOneTV = findViewById(R.id.morningAlarmButton);
        alarmTwoTV = findViewById(R.id.eveningAlarmButton);
        alarmThreeTV = findViewById(R.id.nightAlarmButton);
    }


    private void getDataFromSharedPreferences() {
        SharedPreferences morningSharedPreferences = getSharedPreferences("morningAlarm", Context.MODE_PRIVATE);
        morningTimeLong = morningSharedPreferences.getLong("morningTimeKey", 545454545);

        SharedPreferences eveningSharedPreferences = getSharedPreferences("eveningAlarm", Context.MODE_PRIVATE);
        eveningTimeLong = eveningSharedPreferences.getLong("eveningTimeKey", 545454545);


        SharedPreferences nightSharedPreferences = getSharedPreferences("nightAlarm", Context.MODE_PRIVATE);
        nightTimeLong = nightSharedPreferences.getLong("nightTimeKey", 545454545);

    }


    public void setMorningAlarm(int id) {
        Calendar calendar = Calendar.getInstance();
        if(morningTimeLong!=545454545){
            calendar.setTimeInMillis(morningTimeLong);
            String alarmTimeString;
            alarmTimeString = "" + DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()) + "";
            alarmOneTV.setText(alarmTimeString);
            AlarmManager alarmManagerMorning = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent morningIntent = new Intent(this, AlertReceiver.class);
            morningIntent.putExtra("id", 10);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, morningIntent, 0);
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1);
            }
            alarmManagerMorning.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        else{
            alarmOneTV.setText("Pick time from setting");
        }
    }


    public void setEveningAlarm(int id) {
        Calendar calendar = Calendar.getInstance();
        if (eveningTimeLong!=545454545){
            calendar.setTimeInMillis(eveningTimeLong);
            String alarmTimeString;
            alarmTimeString = "" + DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()) + "";
            alarmTwoTV.setText(alarmTimeString);
            AlarmManager alarmManagerEvening = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent eveningIntent = new Intent(this, AlertReceiver.class);
            eveningIntent.putExtra("id", 20);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, eveningIntent, 0);
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1);
            }
            alarmManagerEvening.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        else{
            alarmTwoTV.setText("Pick time from setting");
        }
    }


    public void setNightAlarm(int id) {
        Calendar calendar = Calendar.getInstance();
        if (nightTimeLong!=545454545){
            calendar.setTimeInMillis(nightTimeLong);
            String alarmTimeString;
            alarmTimeString = "" + DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()) + "";
            alarmThreeTV.setText(alarmTimeString);
            AlarmManager alarmManagerNight = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent nightIntent = new Intent(this, AlertReceiver.class);
            nightIntent.putExtra("id", 30);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, nightIntent, 0);
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1);
            }
            alarmManagerNight.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        else {
            alarmThreeTV.setText("Pick time form setting");
        }
    }


    private void changeDateColors() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY:
                sundayTV.setBackgroundColor(Color.DKGRAY);
                break;

            case Calendar.MONDAY:
                sundayTV.setBackgroundColor(Color.DKGRAY);
                mondayTV.setBackgroundColor(Color.GRAY);
                break;

            case Calendar.TUESDAY:
                sundayTV.setBackgroundColor(Color.DKGRAY);
                mondayTV.setBackgroundColor(Color.DKGRAY);
                tuesdayTV.setBackgroundColor(Color.GRAY);
                break;

            case Calendar.WEDNESDAY:
                sundayTV.setBackgroundColor(Color.DKGRAY);
                mondayTV.setBackgroundColor(Color.DKGRAY);
                tuesdayTV.setBackgroundColor(Color.DKGRAY);
                wednesdayTV.setBackgroundColor(Color.GRAY);
                break;

            case Calendar.THURSDAY:
                sundayTV.setBackgroundColor(Color.DKGRAY);
                mondayTV.setBackgroundColor(Color.DKGRAY);
                tuesdayTV.setBackgroundColor(Color.DKGRAY);
                wednesdayTV.setBackgroundColor(Color.DKGRAY);
                thursdayTV.setBackgroundColor(Color.GRAY);
                break;

            case Calendar.FRIDAY:
                sundayTV.setBackgroundColor(Color.DKGRAY);
                mondayTV.setBackgroundColor(Color.DKGRAY);
                tuesdayTV.setBackgroundColor(Color.DKGRAY);
                wednesdayTV.setBackgroundColor(Color.DKGRAY);
                thursdayTV.setBackgroundColor(Color.DKGRAY);
                fridayTV.setBackgroundColor(Color.GRAY);
                break;

            case Calendar.SATURDAY:
                sundayTV.setBackgroundColor(Color.DKGRAY);
                mondayTV.setBackgroundColor(Color.DKGRAY);
                tuesdayTV.setBackgroundColor(Color.DKGRAY);
                wednesdayTV.setBackgroundColor(Color.DKGRAY);
                thursdayTV.setBackgroundColor(Color.DKGRAY);
                fridayTV.setBackgroundColor(Color.DKGRAY);
                saturdayTV.setBackgroundColor(Color.GRAY);
                break;
        }
    }


    public void openSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
