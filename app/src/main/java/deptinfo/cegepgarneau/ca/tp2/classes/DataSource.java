package deptinfo.cegepgarneau.ca.tp2.classes;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;

/**
 * Created by Renaud-Charles on 2016-04-28.
 */
public class DataSource {

    private final static int CONNECTION_TIMEOUT = 5000;
    private HttpURLConnection mHttpURLConnection = null;

}
