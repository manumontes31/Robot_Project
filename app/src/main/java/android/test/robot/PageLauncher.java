package android.test.robot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PageLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void saveSetting(View view) {
        String sexe;

        MediaPlayer mph = MediaPlayer.create(this,R.raw.pere);
        MediaPlayer mpf = MediaPlayer.create(this,R.raw.maitre);

        RadioGroup group_radio = (RadioGroup) findViewById(R.id.group_radio);
        int checked = group_radio.getCheckedRadioButtonId();

        RadioButton button1 = (RadioButton) findViewById(R.id.radio1);
        int idB1 = button1.getId();

        RadioButton button2 = (RadioButton) findViewById(R.id.radio2);
        int idB2 = button2.getId();

        if (checked == idB1) {
            sexe = "Homme";
            mph.start();
        }

        if (checked == idB2) {
            sexe = "Femme";
            mpf.start();
        }

        if((!mph.isPlaying()) || (!mpf.isPlaying())) {
            Intent i = new Intent(PageLauncher.this, MainActivity.class);
            startActivity(i);
           // finish();
        }
    }
}
