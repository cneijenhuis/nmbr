package co.appeti.hack.nmbr;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.graphics.Bitmap;
import android.os.CountDownTimer;

/**
 * Created by whateverhuis on 6/1/13.
 */
public class NmbrWidgetProvider extends AppWidgetProvider {
    private TimeUpdaterWidget timeUpdater;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        TimeUpdaterWidget.onUpdate(context, appWidgetManager);

        Intent intent = new Intent(context, UpdateService.class);
        intent.setAction(UpdateService.START_SERVICE);
        context.startService(intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
