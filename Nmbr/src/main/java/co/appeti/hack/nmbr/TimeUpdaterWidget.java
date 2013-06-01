package co.appeti.hack.nmbr;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Chronometer;
import android.widget.RemoteViews;

/**
 * Created by whateverhuis on 6/1/13.
 */
public class TimeUpdaterWidget extends TimeUpdater {
    RemoteViews views;

    public TimeUpdaterWidget(){
        super();
        views = new RemoteViews("co.appeti.hack.nmbr", R.layout.activity_main);
    }

    @Override
    public void setHourBitmap(Bitmap bm) {
        views.setImageViewBitmap(R.id.hour, bm);
    }

    @Override
    public void setMinuteBitmap(Bitmap bm) {
        views.setImageViewBitmap(R.id.minute, bm);
    }

    public RemoteViews getViews() {
        return views;
    }

    public static void onUpdate(Context c, AppWidgetManager appWidgetManager) {
        System.out.println("on update");

        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(c, NmbrWidgetProvider.class));

        TimeUpdaterWidget timeUpdater = new TimeUpdaterWidget();
        timeUpdater.update();
        for (int i = 0; i < appWidgetIds.length; i++) {
            appWidgetManager.updateAppWidget(appWidgetIds[i], timeUpdater.getViews());
        }
    }
}
