package co.appeti.hack.nmbr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.Menu;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.baseapp.eyeem.androidsdk.api.EyeEmAPI;
import com.baseapp.eyeem.androidsdk.api.EyeemConnect;
import com.baseapp.eyeem.androidsdk.models.EyeemPhoto;
import com.baseapp.eyeem.androidsdk.query.EyeemPhotosQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class Nmbr extends EyeemConnect {

    private Chronometer chrono;
    private File rootdir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootdir = new File(Environment.getExternalStorageDirectory(), "nmbr");
        // This is my private API key. Don't misuse it, please! :)
        api = new EyeEmAPI("NB0G4tTwmpQ3Ve0r6BDwxpiIdIIEhjYp");

        for (int i = 0; i < 60; i++) {
            DownloadPhotoTask dpt = new DownloadPhotoTask(api, (ImageView)findViewById(R.id.hour));
            dpt.execute(i);
        }
        //dpt.execute(2);
        //DownloadPhotoTask dpt2 = new DownloadPhotoTask(api, (ImageView)findViewById(R.id.minute));
        //dpt2.execute(8);

        chrono = (Chronometer) findViewById(R.id.chronometer);
        startTimer();
    }

    protected File random(File[] imgIds) {
        Random r = new Random();
        return imgIds[r.nextInt(imgIds.length)];
    }

    public void chooseRandomHourScreen(int min) {
        Bitmap bm = getRandomBitmap(min);
        ((ImageView)findViewById(R.id.hour)).setImageBitmap(bm);
    }

    public void chooseRandomMinuteScreen(int min) {
        Bitmap bm = getRandomBitmap(min);
         ((ImageView)findViewById(R.id.minute)).setImageBitmap(bm);
    }

    private Bitmap getRandomBitmap(int min) {
        File nmbrDir = new File(rootdir, "nmbr" + min);

        File[] files = nmbrDir.listFiles();
        if (files == null || files.length <= 0) return null;
        File f = random(files);
        return BitmapFactory.decodeFile(f.getPath());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nmbr, menu);
        return true;
    }

    public void startTimer() {
        chrono.start();
        System.out.println("start timer");
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                System.out.println("timer tick");
                Calendar c = Calendar.getInstance();
                int min = c.get(Calendar.MINUTE);
                int sec = c.get(Calendar.SECOND);
                chooseRandomHourScreen(min);
                chooseRandomMinuteScreen(sec);
            }
        });
    }
    
}
