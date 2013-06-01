package co.appeti.hack.nmbr;

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
    private File rootdir;

    public TimeUpdater() {
        this.rootdir = new File(Environment.getExternalStorageDirectory(), "nmbr");
    }

    protected File random(File[] imgIds) {
        Random r = new Random();
        return imgIds[r.nextInt(imgIds.length)];
    }

    public abstract void setHourBitmap(Bitmap bm);

    public void chooseRandomHourScreen(int min) {
        Bitmap bm = getRandomBitmap(min);
        setHourBitmap(bm);
    }

    public abstract void setMinuteBitmap(Bitmap bm);

    public void chooseRandomMinuteScreen(int min) {
        Bitmap bm = getRandomBitmap(min);
        setMinuteBitmap(bm);
    }

    private Bitmap getRandomBitmap(int min) {
        File nmbrDir = new File(rootdir, "nmbr" + min);

        File[] files = nmbrDir.listFiles();
        if (files == null || files.length <= 0) return null;
        File f = random(files);
        return BitmapFactory.decodeFile(f.getPath());
    }

    public void update() {
        Calendar c = Calendar.getInstance();
        int min = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);
        chooseRandomHourScreen(min);
        chooseRandomMinuteScreen(sec);
    }
}
