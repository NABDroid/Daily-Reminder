package bd.com.nabdroid.medicationreminder;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.RemoteViews;

import java.util.Calendar;

public class MyWidget extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            views.setOnClickPendingIntent(R.id.widgetDaysLayout, pendingIntent);
            changeDateColors(views);


            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }

    private void changeDateColors(RemoteViews view) {
        RemoteViews views = view;

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY:
                views.setInt(R.id.sundayTVWG,"setBackgroundColor", Color.GRAY);
                break;

            case Calendar.MONDAY:
                views.setInt(R.id.sundayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.mondayTVWG,"setBackgroundColor", Color.GRAY);
                break;

            case Calendar.TUESDAY:
                views.setInt(R.id.sundayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.mondayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.tuesdayTVWG,"setBackgroundColor", Color.GRAY);

                break;

            case Calendar.WEDNESDAY:
                views.setInt(R.id.sundayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.mondayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.tuesdayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.wednesdayTVWG,"setBackgroundColor",Color.GRAY);
                break;

            case Calendar.THURSDAY:
                views.setInt(R.id.sundayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.mondayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.tuesdayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.wednesdayTVWG,"setBackgroundColor",Color.DKGRAY);
                views.setInt(R.id.thursdayCV,"setBackgroundColor",Color.GRAY);
                break;

            case Calendar.FRIDAY:
                views.setInt(R.id.sundayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.mondayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.tuesdayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.wednesdayTVWG,"setBackgroundColor",Color.DKGRAY);
                views.setInt(R.id.thursdayCV,"setBackgroundColor",Color.DKGRAY);
                views.setInt(R.id.fridayTVWG,"setBackgroundColor",Color.GRAY);
                break;

            case Calendar.SATURDAY:
                views.setInt(R.id.sundayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.mondayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.tuesdayTVWG,"setBackgroundColor", Color.DKGRAY);
                views.setInt(R.id.wednesdayTVWG,"setBackgroundColor",Color.DKGRAY);
                views.setInt(R.id.thursdayCV,"setBackgroundColor",Color.DKGRAY);
                views.setInt(R.id.fridayTVWG,"setBackgroundColor",Color.DKGRAY);
                views.setInt(R.id.saturdayTVWG,"setBackgroundColor",Color.GRAY);
                break;
        }
    }


}

