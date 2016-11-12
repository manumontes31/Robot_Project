package android.test.robot;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Launcher extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_main);

        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();
    }


    private void addDrawerItems() {
        String[] osArray = { "Préférence" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
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
            Intent i = new Intent(Launcher.this, MainActivity.class);
            startActivity(i);
            // finish();
        }
    }
}
