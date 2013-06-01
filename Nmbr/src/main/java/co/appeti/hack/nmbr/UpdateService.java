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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("SERVICE");
        // Update view now
        TimeUpdaterWidget.onUpdate(this, AppWidgetManager.getInstance(this));
        // Alarm manager calls this method again if device doesn't sleep
		Intent updateintent = new Intent(this, UpdateService.class);
		PendingIntent contentIntent = PendingIntent.getService(this, 0, updateintent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC, SystemClock.elapsedRealtime() + 1000, contentIntent);
        // Call super
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
