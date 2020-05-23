package ba.unsa.etf.digit_recognizer_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{

    Button startBtn, aboutBtn, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.btn_start);
        aboutBtn = findViewById(R.id.btn_about);
        exitButton = findViewById(R.id.btn_exit);
        setListeners();
    }

    private void setListeners() {
        startBtn.setOnClickListener(v -> loadNewActivity(MainActivity.this, CanvasActivity.class));
        aboutBtn.setOnClickListener(v -> loadNewActivity(MainActivity.this, AboutActivity.class));
        exitButton.setOnClickListener(v -> finish());
    }

    private void loadNewActivity(AppCompatActivity currentActivityContext, Class nextActivityContext){
        Intent intent = new Intent(currentActivityContext, nextActivityContext);
        currentActivityContext.startActivity(intent);
    }
}
