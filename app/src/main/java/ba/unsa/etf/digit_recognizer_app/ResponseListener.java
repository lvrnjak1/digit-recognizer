package ba.unsa.etf.digit_recognizer_app;

import org.json.JSONException;

public interface ResponseListener {
    public void onResponseReceived(String response) throws JSONException;
}
