package co.appeti.hack.nmbr;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Created by whateverhuis on 6/1/13.
 */
public class TimeUpdaterWidget extends TimeUpdater {
    RemoteViews views;
    Context context;

    public static int getLayoutId() {
        return R.layout.hourandminuteimages_withborder;
    }

    public TimeUpdaterWidget(Context c){
        super();
        context = c;
        views = new RemoteViews("co.appeti.hack.nmbr", getLayoutId());
    }

    @Override
    public void setHourBitmap(BitmapWithFile bm) {
        int viewId = R.id.hour;
        setBitmap(bm, viewId);
    }

    private void setBitmap(BitmapWithFile bm, int viewId) {
        views.setImageViewBitmap(viewId, bm.bitmap);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.eyeem.com/p/" + bm.filename));
        PendingIntent pi = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_ONE_SHOT);
        views.setOnClickPendingIntent(viewId, pi);
    }

    @Override
    public void setMinuteBitmap(BitmapWithFile bm) {
        setBitmap(bm, R.id.minute);
    }

    public RemoteViews getViews() {
        return views;
    }

    public static void onUpdate(Context c, AppWidgetManager appWidgetManager) {
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(c, NmbrWidgetProvider.class));

        TimeUpdaterWidget timeUpdater = new TimeUpdaterWidget(c);
        timeUpdater.update(c);
        for (int i = 0; i < appWidgetIds.length; i++) {
            appWidgetManager.updateAppWidget(appWidgetIds[i], timeUpdater.getViews());
        }
    }

    @Override
    protected String getSharedPrefName() {
        return SHARED_PREF + "widget";
    }
}
