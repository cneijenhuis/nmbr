package co.appeti.hack.nmbr;

import android.graphics.Bitmap;
import android.widget.Chronometer;
import android.widget.ImageView;

import java.util.Calendar;

/**
 * Created by whateverhuis on 6/1/13.
 */
public class TimeUpdaterRef extends TimeUpdater {
    private Chronometer chrono;
    private ImageView hourView;
    private ImageView minuteView;

    public TimeUpdaterRef(Chronometer chrono, ImageView hourView, ImageView inuteView) {
        super();
        this.chrono = chrono;
        this.hourView = hourView;
        this.minuteView = inuteView;
    }

    public void setHourBitmap(BitmapWithFile bm) {
        hourView.setImageBitmap(bm.bitmap);
    }

    public void setMinuteBitmap(BitmapWithFile bm) {
        minuteView.setImageBitmap(bm.bitmap);
    }

    public void startTimer() {
        chrono.start();
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                update(chrono.getContext());
            }
        });
    }

    @Override
    protected String getSharedPrefName() {
        return SHARED_PREF + "ref";
    }
}
