package co.appeti.hack.nmbr;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;

/**
 * Created by whateverhuis on 6/2/13.
 */
public class NmbrWidgetProviderBordered extends NmbrWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        TimerUpdaterWidgetBorder.onUpdate(context, appWidgetManager);

        Intent intent = new Intent(context, UpdateServiceBordered.class);
        intent.setAction(UpdateService.START_SERVICE);
        context.startService(intent);
    }

}
