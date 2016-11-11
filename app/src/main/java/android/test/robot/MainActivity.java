package android.test.robot;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mp = MediaPlayer.create(this, R.raw.imperial_march);
        mp.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void pageRec(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setPressed(true);
                        v.setBackgroundResource(R.drawable.recbuttonpressed);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.recbutton);
                        Intent i = new Intent(MainActivity.this, PageMic.class);
                        startActivity(i);
                        mp.stop();
                        v.setPressed(false);
                        break;
                }
                return false;
            }
        });

    }
}
