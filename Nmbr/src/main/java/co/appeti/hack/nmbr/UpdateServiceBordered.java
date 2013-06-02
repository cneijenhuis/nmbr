package co.appeti.hack.nmbr;

import android.appwidget.AppWidgetManager;

/**
 * Created by whateverhuis on 6/2/13.
 */
public class UpdateServiceBordered extends UpdateService {

    protected void updateView() {
        TimerUpdaterWidgetBorder.onUpdate(this, AppWidgetManager.getInstance(this));
    }
}
