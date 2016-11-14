package android.test.robot;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import static android.media.MediaPlayer.create;

public class Lecture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);
    }

    public void launchMusic(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        MediaPlayer mpC = create(this, R.raw.chewbacca);
        MediaPlayer mpIM = create(this,R.raw.imperial_march);
        MediaPlayer mpM = create(this, R.raw.maitre);
        MediaPlayer mpP = create(this, R.raw.pere);

        switch(view.getId()) {
            case R.id.radioChewbacca:
                if (checked && (mpIM.isPlaying() || mpM.isPlaying() || mpP.isPlaying())) {
                    mpIM.stop();
                    mpM.stop();
                    mpP.stop();
                } else
                    mpC.start();
                break;
            case R.id.radioIMarch:
                if (checked && (mpC.isPlaying() || mpM.isPlaying() || mpP.isPlaying())) {
                    mpM.stop();
                    mpP.stop();
                    mpC.stop();
                } else
                    mpIM.start();
                break;
            case R.id.radioMaitre:
                if (checked && (mpC.isPlaying() || mpIM.isPlaying() || mpP.isPlaying())) {
                    mpP.stop();
                    mpC.stop();
                    mpIM.stop();
                } else
                    mpM.start();

                break;
            case R.id.radioPere:
                if (checked && (mpC.isPlaying() || mpIM.isPlaying() || mpC.isPlaying())) {
                    mpC.stop();
                    mpIM.stop();
                    mpM.stop();
                } else
                    mpP.start();
                break;
        }
    }
}
