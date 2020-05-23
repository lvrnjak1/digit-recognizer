package ba.unsa.etf.digit_recognizer_app;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerUtils {

    private final String SOLVE_ENDPOINT = "http://digit-recognizer.eastus.cloudapp.azure.com/solve";

    public void solve(String imageB64, ResponseListener responseListener) {
        SolveAsyncTask solveAsyncTask = new SolveAsyncTask();
        solveAsyncTask.setListener(responseListener); //ovo treba uraditi sa konstruktorom
        solveAsyncTask.execute(SOLVE_ENDPOINT, createJson(imageB64).toString());
    }

    private static JSONObject createJson(String image){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("image", image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
