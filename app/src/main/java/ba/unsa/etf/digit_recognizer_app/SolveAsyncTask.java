package ba.unsa.etf.digit_recognizer_app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class SolveAsyncTask extends AsyncTask<String, Void, String> {
    private ResponseListener responseListener;
    @Override
    protected String doInBackground(String... params) {
        String jsonResult = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            Writer writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(params[1]);
            writer.close();

            connection.connect();
            Log.e("Response ",connection.getResponseMessage() + "");

            //TODO OVDJE SE TREBA PREUZETI RESPONSE U jsonResult

        } catch (Exception e) {
            Log.e(e.toString(), "Something with request");
        }

        return jsonResult;
    }

    @Override
    protected void onPostExecute(String response) {
        if(responseListener != null){
            try {
                responseListener.onResponseReceived(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }
}
