package co.appeti.hack.nmbr;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import com.baseapp.eyeem.androidsdk.api.EyeEmAPI;
import com.baseapp.eyeem.androidsdk.api.EyeemConnect;

public class Nmbr extends EyeemConnect {

    private TimeUpdaterRef timeUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nmbr_activity);
        // This is my private API key. Don't misuse it, please! :)
        api = new EyeEmAPI("NB0G4tTwmpQ3Ve0r6BDwxpiIdIIEhjYp");

        Chronometer chrono = (Chronometer) findViewById(R.id.chronometer);
        ImageView hourView = (ImageView) findViewById(R.id.hour);
        ImageView minuteView = (ImageView) findViewById(R.id.minute);
        timeUpdater = new TimeUpdaterRef(chrono, hourView, minuteView);
        timeUpdater.useMinutesInsteadOfHours = true;
        timeUpdater.startTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nmbr, menu);
        return true;
    }

    public void showDownloadToast() {
        Toast.makeText(this, "Downloading from EyeEm...", Toast.LENGTH_LONG).show();
    }

    public void startPresetDownloads(View v) {
        showDownloadToast();
        for (int i = 0; i < 60; i++) {
            DownloadPhotoTask dpt = new DownloadPhotoTask(api, (ImageView)findViewById(R.id.hour));
            dpt.execute(i);
        }
    }

    public void fetchNewImages(View v) {
        showDownloadToast();
    }
}
