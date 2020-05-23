package ba.unsa.etf.digit_recognizer_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ResponseListener, CanvasListener{

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
        eraseBtn.setOnClickListener(v -> canvasView.startNew());
        solveButton.setOnClickListener(v -> canvasView.encodeBitmapToBase64(MainActivity.this));
    }

    @Override
    public void onResponseReceived(String response){
        System.out.println("Response received");
        System.out.println(response);
    }

    @Override
    public void onCanvasGenerated(String base64) {
        new ServerUtils().solve(base64, MainActivity.this);
    }
}
