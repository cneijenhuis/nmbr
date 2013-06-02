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
    protected class BitmapWithFile {
        public final Bitmap bitmap;
        public final String filename;

        private BitmapWithFile(Bitmap bitmap, String filename) {
            this.bitmap = bitmap;
            this.filename = filename;
        }
    }
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

    public abstract void setHourBitmap(BitmapWithFile bm);

    public void chooseRandomHourScreen(int min) {
        BitmapWithFile bm = getRandomBitmap(min, true);
        setHourBitmap(bm);
    }

    public abstract void setMinuteBitmap(BitmapWithFile bm);

    public void chooseRandomMinuteScreen(int min) {
        BitmapWithFile bm = getRandomBitmap(min, false);
        setMinuteBitmap(bm);
    }

    private BitmapWithFile getRandomBitmap(int min, boolean saveAsHour) {
        File nmbrDir = new File(rootdir, "nmbr" + min);

        File[] files = nmbrDir.listFiles();
        if (files == null || files.length <= 0) return new BitmapWithFile(null, "");
        File f = random(files, saveAsHour);
        // Make sure we are not displaying the same file twice, if possible
        if (!saveAsHour && files.length >= 2 && f.equals(hourFile)) {
            f = files[0];
            if (f.equals(hourFile)) f = files[1];
        }
        if (saveAsHour) hourFile = f;
        return new BitmapWithFile(BitmapFactory.decodeFile(f.getPath()), f.getName().replace(".jpg", ""));
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
