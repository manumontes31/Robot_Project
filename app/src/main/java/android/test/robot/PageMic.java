package android.test.robot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;

import pl.droidsonroids.gif.GifTextView;

public class PageMic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic);
        GifTextView view = (GifTextView) findViewById(R.id.micro);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        v.setBackgroundResource(R.drawable.mic);
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        v.setPressed(false);
                        v.setBackgroundResource(R.drawable.backgroundmic);
                        break;
                }
                return false;
            }
        });
    }
}