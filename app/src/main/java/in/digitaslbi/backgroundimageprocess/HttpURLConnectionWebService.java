package in.digitaslbi.backgroundimageprocess;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Lokesh on 06-09-2015.
 */
public class HttpURLConnectionWebService {

    public HttpURLConnectionWebService() {

    }

    /* Tag is only for marking which class is calling this method*/
    public String getMovieJSON(String ur) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
         /* Take an URL Object*/
        try {

            URL url = new URL(ur);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            if (stringBuffer.length() == 0) {
                return null;
            }

            return stringBuffer.toString();
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();

            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
}
