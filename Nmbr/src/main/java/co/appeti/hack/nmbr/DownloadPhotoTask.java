package co.appeti.hack.nmbr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import com.baseapp.eyeem.androidsdk.api.EyeEmAPI;
import com.baseapp.eyeem.androidsdk.models.EyeemPhoto;
import com.baseapp.eyeem.androidsdk.query.EyeemPhotosQuery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by whateverhuis on 6/1/13.
 */
public class DownloadPhotoTask extends AsyncTask<Integer, Void, File> {
    private EyeEmAPI api;
    private ImageView view;

    public DownloadPhotoTask(EyeEmAPI api, ImageView view) {
        this.api = api;
        this.view = view;
    }

    @Override
    protected File doInBackground(Integer... photoId) {
        Integer id = photoId[0];

        EyeemPhotosQuery photosQuery = new EyeemPhotosQuery();
        photosQuery.firstId = id;
        //photosQuery.specificEndpoint = EyeemPhotosQuery.EESpecificPhotosEndpoint.EEspecificPhotosEndpointBgImages;
        try {
            HashMap<String, Object> map = api.getRequest(photosQuery.transformQuery());
            EyeemPhoto photo = (EyeemPhoto) map.get("photo");
            System.err.println(photo.photoUrl);
            HttpURLConnection conn = (HttpURLConnection) (new URL(photo.photoUrl)).openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            File rootdir = new File(Environment.getExternalStorageDirectory(), "nmbr");
            File dir49 = new File(rootdir, "n49");
            dir49.mkdirs();
            File f = new File(dir49, photo.filename + ".jpg");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);

            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[2048];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesRead);
            }
            fos.close();

            return f;
        }
        catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(File f) {
        Bitmap bm = BitmapFactory.decodeFile(f.getPath());
        view.setImageBitmap(bm);
    }
}
