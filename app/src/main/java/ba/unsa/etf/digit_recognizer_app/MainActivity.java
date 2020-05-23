package ba.unsa.etf.digit_recognizer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements ResponseListener{

    private CanvasView canvasView;
    private ImageButton eraseBtn;
    private Button solveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        canvasView = findViewById(R.id.drawing);
        eraseBtn = findViewById(R.id.erase_btn);
        solveButton = findViewById(R.id.solve_btn);
        setListeners();
    }

    private void setListeners(){
        eraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.startNew();
            }
        });

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new ServerUtils().solve(canvasView.encodeBitmapToBase64(), MainActivity.this);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onResponseReceived(String response){
//        int solution = 0;
//        if(response != null) {
//            try {
//                solution = new JSONObject(response).getInt("solution");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            System.out.println(solution);
//        }
        System.out.println("Response received");
    }
}
