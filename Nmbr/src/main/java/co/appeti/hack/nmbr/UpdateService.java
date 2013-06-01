package co.appeti.hack.nmbr;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

/**
 * Created by whateverhuis on 6/1/13.
 */
public class UpdateService extends Service {
    public static final String START_SERVICE = "nmbr.START_SERVICE";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Update view now
        TimeUpdaterWidget.onUpdate(this, AppWidgetManager.getInstance(this));

        String action = intent.getAction();
        if (action != null && action.equals(START_SERVICE)) {
            // Alarm manager calls this method again if device doesn't sleep
            Intent updateintent = new Intent(this, UpdateService.class);
            PendingIntent contentIntent = PendingIntent.getService(this, 0, updateintent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            // Cancel if another intent is running
            alarmManager.cancel(contentIntent);
            // Start repeating alarm
            int intvl = 1000;
            alarmManager.setRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime() + intvl, intvl, contentIntent);
        }
        // Call super
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
