package ba.unsa.etf.digit_recognizer_app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SolveAsyncTask extends AsyncTask<String, Void, String> {
    private ResponseListener responseListener;
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            String body = params[1];
            try(OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            StringBuilder response = new StringBuilder();
            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while((responseLine = br.readLine()) != null) response.append(responseLine.trim());
            }
            return response.toString();
        } catch (Exception e) {
            Log.e(e.toString(), "Exception in AsyncTask");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        if(responseListener != null){
            responseListener.onResponseReceived(response);
        }
    }

    public void setListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }
}
