package co.appeti.hack.nmbr;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.Menu;
import android.widget.Chronometer;
import android.widget.ImageView;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by whateverhuis on 6/1/13.
 */
public abstract class TimeUpdater {
    public static final String SHARED_PREF = "co.appeti.nmbr.hour";
    private File rootdir;
    private File hourFile;

    public TimeUpdater() {
        this.rootdir = new File(Environment.getExternalStorageDirectory(), "nmbr");
    }

    protected File random(File[] imgIds, boolean saveAsHour) {
        long now = System.currentTimeMillis();
        Random r;
        if (saveAsHour) r = new Random(now - (now % (60 * 1000)));
        else r = new Random();
        return imgIds[r.nextInt(imgIds.length)];
    }

    public abstract void setHourBitmap(Bitmap bm);

    public void chooseRandomHourScreen(int min) {
        Bitmap bm = getRandomBitmap(min, true);
        setHourBitmap(bm);
    }

    public abstract void setMinuteBitmap(Bitmap bm);

    public void chooseRandomMinuteScreen(int min) {
        Bitmap bm = getRandomBitmap(min, false);
        setMinuteBitmap(bm);
    }

    private Bitmap getRandomBitmap(int min, boolean saveAsHour) {
        File nmbrDir = new File(rootdir, "nmbr" + min);

        File[] files = nmbrDir.listFiles();
        if (files == null || files.length <= 0) return null;
        File f = random(files, saveAsHour);
        // Make sure we are not displaying the same file twice, if possible
        if (!saveAsHour && files.length >= 2 && f.equals(hourFile)) {
            f = files[0];
            if (f.equals(hourFile)) f = files[1];
        }
        if (saveAsHour) hourFile = f;
        return BitmapFactory.decodeFile(f.getPath());
    }

    protected abstract String getSharedPrefName();

    public void update(Context context) {
        Calendar c = Calendar.getInstance();
        int min = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);
        chooseRandomHourScreen(min);
        chooseRandomMinuteScreen(sec);
    }
}
