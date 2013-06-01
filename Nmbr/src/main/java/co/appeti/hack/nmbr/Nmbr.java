package co.appeti.hack.nmbr;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.baseapp.eyeem.androidsdk.api.EyeEmAPI;
import com.baseapp.eyeem.androidsdk.api.EyeemConnect;
import com.baseapp.eyeem.androidsdk.models.EyeemPhoto;
import com.baseapp.eyeem.androidsdk.query.EyeemPhotosQuery;

import java.util.ArrayList;
import java.util.HashMap;

public class Nmbr extends EyeemConnect {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This is my private API key. Don't misuse it, please! :)
        System.err.println("Querying EyeEm");
        api = new EyeEmAPI("NB0G4tTwmpQ3Ve0r6BDwxpiIdIIEhjYp");
        DownloadPhotoTask dpt = new DownloadPhotoTask(api);
        dpt.execute(13559994);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nmbr, menu);
        return true;
    }
    
}
