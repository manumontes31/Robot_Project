package android.test.robot;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Lecture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

    }

    public void launchMusic(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        MediaPlayer mp;

        switch(view.getId()) {
            case R.id.radioChewbacca:
                if (checked) {
                    mp = MediaPlayer.create(this, R.raw.chewbacca);
                    mp.start();
                }
                break;
            case R.id.radioIMarch:
                if (checked) {
                    mp = MediaPlayer.create(this,R.raw.imperial_march);
                    mp.start();
                }
                break;
            case R.id.radioMaitre:
                if (checked) {
                    mp = MediaPlayer.create(this, R.raw.maitre);
                    mp.start();
                }
                break;
            case R.id.radioPere:
                if (checked) {
                    mp = MediaPlayer.create(this, R.raw.pere);
                    mp.start();
                }
                break;
        }
    }
}
