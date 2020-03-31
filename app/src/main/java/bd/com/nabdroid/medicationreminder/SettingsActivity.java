package bd.com.nabdroid.medicationreminder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class SettingsActivity extends AppCompatActivity {
    private TextView morningTV, eveningTV, nightTV, ringToneTV;
    private Long morningTimeLong, eveningTimeLong, nightTimeLong;
    String ringTonePathString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
        getDataFromSharedPreferences();
        setDataIntoTV();
    }


    private void init() {
        morningTV = findViewById(R.id.morningAlarmTV);
        eveningTV = findViewById(R.id.eveningAlarmTV);
        nightTV = findViewById(R.id.nightAlarmTV);
        ringToneTV = findViewById(R.id.ringToneTV);
    }
    private void getDataFromSharedPreferences() {
        SharedPreferences morningSharedPreferences = getSharedPreferences("morningAlarm", Context.MODE_PRIVATE);
        morningTimeLong = morningSharedPreferences.getLong("morningTimeKey", 545454545);

        SharedPreferences eveningSharedPreferences = getSharedPreferences("eveningAlarm", Context.MODE_PRIVATE);
        eveningTimeLong = eveningSharedPreferences.getLong("eveningTimeKey", 545454545);


        SharedPreferences nightSharedPreferences = getSharedPreferences("nightAlarm", Context.MODE_PRIVATE);
        nightTimeLong = nightSharedPreferences.getLong("nightTimeKey", 545454545);

        SharedPreferences ringTonePreferences = getSharedPreferences("ringTone", Context.MODE_PRIVATE);
        ringTonePathString = ringTonePreferences.getString("ringtonePathKey", "Default notification tone selected");

    }

    private void setDataIntoTV() {
        ringToneTV.setText(ringTonePathString);
        Calendar calendar = Calendar.getInstance();
        if(morningTimeLong!=545454545) {
            calendar.setTimeInMillis(morningTimeLong);

            String alarmTimeString;
            alarmTimeString = "Morning: " + DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()) + "";
            morningTV.setText(alarmTimeString);
        }

        if(eveningTimeLong!=545454545) {
            calendar.setTimeInMillis(eveningTimeLong);

            String alarmTimeString;
            alarmTimeString = "Evening: " + DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()) + "";
            eveningTV.setText(alarmTimeString);
        }

        if(nightTimeLong!=545454545) {
            calendar.setTimeInMillis(nightTimeLong);

            String alarmTimeString;
            alarmTimeString = "Night: " + DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()) + "";
            nightTV.setText(alarmTimeString);
        }

        else{
            Toast.makeText(this, "set alarm Time", Toast.LENGTH_LONG).show();
        }
    }

    public void pickMorningAlarmTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minuit) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minuit);
                calendar.set(Calendar.SECOND, 0);


                String alarmTimeString;
                alarmTimeString= "Alarm one: "+DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime())+"";
                morningTV.setText(alarmTimeString);

                Date date = calendar.getTime();
                Long timeMS = date.getTime();

                SharedPreferences sharedPreferences = getSharedPreferences("morningAlarm", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("morningTimeKey", timeMS );
                editor.commit();


            }
        };

        Calendar mTime = Calendar.getInstance();
        int hour = mTime.get(Calendar.HOUR_OF_DAY);
        int minute = mTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, false);
        timePickerDialog.show();
    }

    public void pickEveningAlarmTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minuit) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minuit);
                calendar.set(Calendar.SECOND, 0);

                String alarmTimeString;
                alarmTimeString= "Alarm two: "+DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime())+"";
                eveningTV.setText(alarmTimeString);

                Date date = calendar.getTime();
                Long timeMS = date.getTime();

                SharedPreferences sharedPreferences = getSharedPreferences("eveningAlarm", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("eveningTimeKey", timeMS );
                editor.commit();


            }
        };

        Calendar mTime = Calendar.getInstance();
        int hour = mTime.get(Calendar.HOUR_OF_DAY);
        int minute = mTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, false);
        timePickerDialog.show();
    }

    public void pickNightAlarmTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minuit) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minuit);
                calendar.set(Calendar.SECOND, 0);

                String alarmTimeString;
                alarmTimeString= "Alarm three: "+DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime())+"";
                nightTV.setText(alarmTimeString);

                Date date = calendar.getTime();
                Long timeMS = date.getTime();

                SharedPreferences sharedPreferences = getSharedPreferences("nightAlarm", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("nightTimeKey", timeMS );
                editor.commit();


            }
        };

        Calendar mTime = Calendar.getInstance();
        int hour = mTime.get(Calendar.HOUR_OF_DAY);
        int minute = mTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, false);
        timePickerDialog.show();
    }


    public void setRingtone(View view) {
        Intent intent_upload = new Intent();
        intent_upload.setType("audio/*");
        intent_upload.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent_upload,10);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                String string = data.getData().getPath();
                SharedPreferences sharedPreferences = getSharedPreferences("ringTone", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ringtonePathKey", string);
                editor.commit();
            }
        }
    }
}
