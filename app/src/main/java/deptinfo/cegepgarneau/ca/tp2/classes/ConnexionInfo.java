package deptinfo.cegepgarneau.ca.tp2.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Renaud-Charles on 2016-04-28.
 */
public final class ConnexionInfo {
    public final static String WEB_SERVICE_URL = "192.168.0.191";
    public final static int PORT = 8080;

    public static String readStream(InputStream in) throws IOException {

        StringBuilder sb = new StringBuilder();

        //Lecture du flux de données
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

        String nextLine = "";
        while ((nextLine = reader.readLine()) != null) {
            sb.append(nextLine);
        }

        return sb.toString();
    }
}
