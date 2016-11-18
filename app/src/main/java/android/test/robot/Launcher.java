package android.test.robot;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Launcher extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String sexe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_launcher);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        sexe = pref.getString("listSexe","");

        TextView text = (TextView) findViewById(R.id.textlauncher);
        text.setText("Vous êtes connecté en tant que " + sexe);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.preference) {
            Intent i = new Intent(this, preference.class);
            startActivity(i);
            finish();
        } else if(id == R.id.apropos){
            new AlertDialog.Builder(this)
                    .setTitle(" ")
                    .setMessage("L'univers de Star Wars se déroule dans une galaxie qui se fait le théâtre d'affrontements entre les Chevaliers Jedi et les Seigneurs noirs des Sith, personnes sensibles à la Force, un champ énergétique mystérieux leur procurant des pouvoirs psychiques. Les Jedi maîtrisent le Côté lumineux de la Force, pouvoir bénéfique et défensif, pour maintenir la paix dans la galaxie. Les Sith utilisent le Côté obscur, pouvoir nuisible et destructeur, pour leurs usages personnels et pour dominer la galaxie")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(R.drawable.blackstar)
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void saveSetting(View view) {
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
