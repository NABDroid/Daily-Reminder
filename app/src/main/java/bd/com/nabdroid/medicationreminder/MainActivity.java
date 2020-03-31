package bd.com.nabdroid.medicationreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private CardView sundayCV, mondayCV, tuesdayCV, wednesdayCV, thursdayCV, fridayCV, saturdayCV;
    private TextView sundayTV, mondayTV, tuesdayTV, wednesdayTV, thursdayTV, fridayTV, saturdayTV;
    private Button morningAlarmButton, eveingAlarmButton, nightAlarmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setMorningAlarm(1);
        setEveningAlarm(2);
        setNightAlarm(3);

        changeDateColors();


    }



    private void init() {

        sundayCV = findViewById(R.id.sundayCV);
        mondayCV = findViewById(R.id.mondayCV);
        tuesdayCV = findViewById(R.id.tuesdayCV);
        wednesdayCV = findViewById(R.id.wednesdayCV);
        thursdayCV = findViewById(R.id.thursdayCV);
        fridayCV = findViewById(R.id.fridayCV);
        saturdayCV = findViewById(R.id.saturdayCV);

        sundayTV = findViewById(R.id.sundayTV);
        mondayTV = findViewById(R.id.mondayTV);
        tuesdayTV = findViewById(R.id.tuesdayTV);
        wednesdayTV = findViewById(R.id.wednesdayTV);
        thursdayTV = findViewById(R.id.thursdayTV);
        fridayTV = findViewById(R.id.fridayTV);
        saturdayTV = findViewById(R.id.saturdayTV);

        morningAlarmButton = findViewById(R.id.morningAlarmButton);
        eveingAlarmButton = findViewById(R.id.eveningAlarmButton);
        nightAlarmButton = findViewById(R.id.nightAlarmButton);

    }

    private void pickTimeForAlarm(int buttonID) {
        final int buttonIDLocal = buttonID;
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minuit) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minuit);
                calendar.set(Calendar.SECOND, 0);

                String string;
                string= ""+DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime())+"";

                switch (buttonIDLocal) {
                    case R.id.morningAlarmButton:
                        string ="Morning: "+string;
                        morningAlarmButton.setText(string);
                        break;

                    case R.id.eveningAlarmButton:
                        string ="Evening: "+string;
                        eveingAlarmButton.setText(string);
                        break;

                    case R.id.nightAlarmButton:
                        string ="Night: "+string;
                        nightAlarmButton.setText(string);

                        break;
                }


            }
        };

        Calendar mTime = Calendar.getInstance();
        int hour = mTime.get(Calendar.HOUR_OF_DAY);
        int minute = mTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, false);
        timePickerDialog.show();


    }

    private void setMorningAlarm(int id) {
        Calendar calendarM = Calendar.getInstance();
        calendarM.set(Calendar.HOUR_OF_DAY, 9);
        calendarM.set(Calendar.MINUTE, 10);
        calendarM.set(Calendar.SECOND, 0);

        String alarmTimeString;
        alarmTimeString= ""+DateFormat.getTimeInstance(DateFormat.SHORT).format(calendarM.getTime())+"";
        morningAlarmButton.setText(alarmTimeString);

        AlarmManager alarmManagerMorning = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent morningIntent = new Intent(this, AlertReceiver.class);
        morningIntent.putExtra("id", 10);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, morningIntent, 0);

        if (calendarM.before(Calendar.getInstance())) {
            calendarM.add(Calendar.DATE, 1);
        }
        alarmManagerMorning.setExact(AlarmManager.RTC_WAKEUP, calendarM.getTimeInMillis(), pendingIntent);

    }

    private void setEveningAlarm(int id) {
        Calendar calendarE = Calendar.getInstance();
        calendarE.set(Calendar.HOUR_OF_DAY, 9);
        calendarE.set(Calendar.MINUTE, 15);
        calendarE.set(Calendar.SECOND, 0);

        String alarmTimeString;
        alarmTimeString= ""+DateFormat.getTimeInstance(DateFormat.SHORT).format(calendarE.getTime())+"";
        eveingAlarmButton.setText(alarmTimeString);


        AlarmManager alarmManagerEvening = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent eveningIntent = new Intent(this, AlertReceiver.class);
        eveningIntent.putExtra("id", 20);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, eveningIntent, 0);

        if (calendarE.before(Calendar.getInstance())) {
            calendarE.add(Calendar.DATE, 1);
        }
        alarmManagerEvening.setExact(AlarmManager.RTC_WAKEUP, calendarE.getTimeInMillis(), pendingIntent);

    }

    private void setNightAlarm(int id) {
        Calendar calendarN = Calendar.getInstance();
        calendarN.set(Calendar.HOUR_OF_DAY, 9);
        calendarN.set(Calendar.MINUTE, 20);
        calendarN.set(Calendar.SECOND, 0);

        String alarmTimeString;
        alarmTimeString= ""+DateFormat.getTimeInstance(DateFormat.SHORT).format(calendarN.getTime())+"";
        nightAlarmButton.setText(alarmTimeString);

        AlarmManager alarmManagerNight = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent nightIntent =  new Intent(this, AlertReceiver.class);
        nightIntent.putExtra("id", 30);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, nightIntent, 0);

        if (calendarN.before(Calendar.getInstance())) {
            calendarN.add(Calendar.DATE, 1);
        }

        alarmManagerNight.setExact(AlarmManager.RTC_WAKEUP, calendarN.getTimeInMillis(), pendingIntent);

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


}
