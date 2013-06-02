package co.appeti.hack.nmbr;

import android.content.Context;

/**
 * Created by whateverhuis on 6/2/13.
 */
public class TimerUpdaterWidgetBorder extends TimeUpdaterWidget {
    public static int getLayoutId() {
        System.out.println("Get layout id");
        return R.layout.hourandminuteimages_withborder;
    }

    public TimerUpdaterWidgetBorder(Context c) {
        super(c);
    }
}
