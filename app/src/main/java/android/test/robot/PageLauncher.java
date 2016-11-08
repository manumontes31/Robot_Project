package android.test.robot;

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

        RadioGroup group_radio = (RadioGroup) findViewById(R.id.group_radio);
        int checked = group_radio.getCheckedRadioButtonId();

        RadioButton button1 = (RadioButton) findViewById(R.id.radio1);
        int idB1 = button1.getId();

        RadioButton button2 = (RadioButton) findViewById(R.id.radio2);
        int idB2 = button2.getId();

        if (checked == idB1) {
            sexe = "Homme";
            button2.setEnabled(false);
        }

        if (checked == idB2) {
            sexe = "Femme";
        }


    }
}
