package ba.unsa.etf.digit_recognizer_app;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    Button backButton;
    TextView aboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        backButton = findViewById(R.id.btn_back);
        aboutText = findViewById(R.id.about_text);


        backButton.setOnClickListener(v -> finish());
        setAboutText();
    }

    private void setAboutText() {
        aboutText.setText(Html.fromHtml(getString(R.string.about), Html.FROM_HTML_MODE_COMPACT));
    }
}
