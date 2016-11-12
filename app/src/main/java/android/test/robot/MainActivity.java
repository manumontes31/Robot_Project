package android.test.robot;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    MediaPlayer mp,mp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mp = MediaPlayer.create(this, R.raw.imperial_march);
        mp3 = MediaPlayer.create(this,R.raw.chewbacca);
        mp.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View v = findViewById(R.id.premier);
        v.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(final View v, MotionEvent event) {
                        switch(event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                v.setBackgroundResource(R.drawable.test2);
                                break;
                            case MotionEvent.ACTION_UP:
                                mp.stop();
                                mp3.start();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable(){
                                    @Override
                                    public void run(){
                                        v.setBackgroundResource(R.drawable.test);
                                    }
                                }, 3000);
                                Intent i = new Intent(MainActivity.this, PageMic.class);
                                startActivity(i);
                                break;
                        }
                        return false;
                    }
                }
        );
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

}
