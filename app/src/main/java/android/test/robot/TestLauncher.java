package android.test.robot;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import junit.framework.Test;

public class TestLauncher extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_launcher);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.preference) {
            Intent i = new Intent(this, preference.class);
            startActivity(i);
        } else if (id == R.id.lecture) {
            Intent i = new Intent(this, Lecture.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
            Intent i = new Intent(TestLauncher.this, MainActivity.class);
            startActivity(i);
        }
    }
}
