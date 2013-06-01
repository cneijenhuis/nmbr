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
import java.util.Random;

/**
 * Created by whateverhuis on 6/1/13.
 */
public class DownloadPhotoTask extends AsyncTask<Integer, Void, File> {
        public int[] nmbrs = { R.array.d0,
R.array.d1,
R.array.d2,
R.array.d3,
R.array.d4,
R.array.d5,
R.array.d6,
R.array.d7,
R.array.d8,
R.array.d9,
R.array.d10,
R.array.d11,
R.array.d12,
R.array.d13,
R.array.d14,
R.array.d15,
R.array.d16,
R.array.d17,
R.array.d18,
R.array.d19,
R.array.d20,
R.array.d21,
R.array.d22,
R.array.d23,
R.array.d24,
R.array.d25,
R.array.d26,
R.array.d27,
R.array.d28,
R.array.d29,
R.array.d30,
R.array.d31,
R.array.d32,
R.array.d33,
R.array.d34,
R.array.d35,
R.array.d36,
R.array.d37,
R.array.d38,
R.array.d39,
R.array.d40,
R.array.d41,
R.array.d42,
R.array.d43,
R.array.d44,
R.array.d45,
R.array.d46,
R.array.d47,
R.array.d48,
R.array.d49,
R.array.d50,
R.array.d51,
R.array.d52,
R.array.d53,
R.array.d54,
R.array.d55,
R.array.d56,
R.array.d57,
R.array.d58,
R.array.d59};

    private EyeEmAPI api;
    private ImageView view;

    public DownloadPhotoTask(EyeEmAPI api, ImageView view) {
        this.api = api;
        this.view = view;
    }

    protected int randomId(int[] imgIds) {
        Random r = new Random();
        return imgIds[r.nextInt(imgIds.length)];
    }

    @Override
    protected File doInBackground(Integer... arrId) {
        Integer aid = arrId[0];
        int[] imgIds = view.getResources().getIntArray(nmbrs[aid]);
        //int id = randomId(imgIds);
        for (int id : imgIds) {
            if (id == 0) continue;

            EyeemPhotosQuery photosQuery = new EyeemPhotosQuery();
            photosQuery.firstId = id;
            try {
                HashMap<String, Object> map = api.getRequest(photosQuery.transformQuery());
                EyeemPhoto photo = (EyeemPhoto) map.get("photo");

                File rootdir = new File(Environment.getExternalStorageDirectory(), "nmbr");
                File nmbrDir = new File(rootdir, "nmbr" + aid);
                nmbrDir.mkdirs();
                File f = new File(nmbrDir, photo.filename + ".jpg");
                if (f.exists())  {
                    //System.out.println("File already loaded");
                    continue;
                }
                System.out.println("Downloading " + photo.photoId);
                f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);

                HttpURLConnection conn = (HttpURLConnection) (new URL(photo.photoUrl)).openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                InputStream is = conn.getInputStream();
                byte[] buffer = new byte[2048];
                int bytesRead = 0;
                while ((bytesRead = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, bytesRead);
                }
                fos.close();
            }
            catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(File f) {
//        if (f == null || f.isDirectory()) return;
//        Bitmap bm = BitmapFactory.decodeFile(f.getPath());
//        view.setImageBitmap(bm);
    }
}
