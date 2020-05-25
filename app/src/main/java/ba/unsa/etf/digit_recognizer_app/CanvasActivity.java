package ba.unsa.etf.digit_recognizer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.json.JSONException;
import org.json.JSONObject;

public class CanvasActivity extends AppCompatActivity implements ResponseListener, CanvasListener{

    private CanvasView canvasView;
    private ImageButton eraseBtn;
    private Button solveButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        canvasView = findViewById(R.id.drawing);
        eraseBtn = findViewById(R.id.erase_btn);
        solveButton = findViewById(R.id.solve_btn);
        backButton = findViewById(R.id.back_btn);
        setListeners();
    }

    private void setListeners(){
        eraseBtn.setOnClickListener(v -> canvasView.startNew());
        solveButton.setOnClickListener(v -> {
            Log.i("listeners", "solve clicked");
            canvasView.encodeBitmapToBase64(CanvasActivity.this);
        });
        backButton.setOnClickListener(v -> finish());
    }

    @Override
    public void onResponseReceived(String response){
        System.out.println("Response received");
        System.out.println(response);
        Log.i("response", response);
        canvasView.startNew();
        try {
            showAlertDialog(new JSONObject(response).getInt("solution"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showAlertDialog(int solution) {
        Log.i("popup", "popup");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You have entered the number");
        builder.setMessage(solution + "!");
        builder.setPositiveButton("Draw again", (dialog, which) -> {
            canvasView.startNew();
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onCanvasGenerated(String base64) {
        new ServerUtils().solve(base64, CanvasActivity.this);
    }
}
