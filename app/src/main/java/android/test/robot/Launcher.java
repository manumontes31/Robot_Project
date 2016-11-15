package android.test.robot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class Launcher extends AppCompatActivity
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
        } // else if ..

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void saveSetting(View view) {
        String sexe;

        MediaPlayer mph = MediaPlayer.create(this,R.raw.pere);
        MediaPlayer mpf = MediaPlayer.create(this,R.raw.maitre);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        sexe = pref.getString("listSexe","");

        if (sexe.equals("homme")) {
            mph.start();
        }

        if (sexe.equals("femme")) {
            mpf.start();
        }

        if((!mph.isPlaying()) || (!mpf.isPlaying())) {
            Intent i = new Intent(Launcher.this, MainActivity.class);
            startActivity(i);
        }
    }
}
