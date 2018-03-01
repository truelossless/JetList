package com.isn.jetlist;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import static android.text.TextUtils.*;

public class MainActivity extends AppCompatActivity {

    // menu de navigation
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // activation de la toolbar material design
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        // bouton menu à gauche le l'action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_menu_jgreen);

        /*
            TextView qui se situe dans l'action bar.
            Hack qui permet de centrer le texte dans la toolbar et de mettre des couleurs personnalisées
         */
        SpannableString titleJetPart = new SpannableString("Jet");
        SpannableString titleListPart = new SpannableString("List");

        titleJetPart.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.jGreen)), 0, titleJetPart.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleListPart.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.jBlack)), 0, titleListPart.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView appTitle = toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(concat(titleJetPart, titleListPart));

        // on commence par afficher la page projet si l'application vient d'être lancée
        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // on affiche le premier panneau projet
            ProjectsFragment projectsFragment = new ProjectsFragment();
            fragmentTransaction.add(R.id.content_frame, projectsFragment);
            fragmentTransaction.commit();
        }

        // gestion du menu de navigation
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        // de base on met la page projet comme active
        navView.getMenu().getItem(0).setChecked(true);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                item.setChecked(true);

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // ajoute le nouveau fragment
                switch (item.getItemId()) {
                    case R.id.nav_projects:
                        ProjectsFragment projectsFragment = new ProjectsFragment();
                        fragmentTransaction.replace(R.id.content_frame, projectsFragment);
                        fragmentTransaction.commit();
                        break;

                    case R.id.nav_docs:
                        DocumentsFragment documentsFragment = new DocumentsFragment();
                        fragmentTransaction.replace(R.id.content_frame, documentsFragment);
                        fragmentTransaction.commit();
                        break;
                }


                drawerLayout.closeDrawers();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // création du menu de l'activité
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // bouton plus de paramètres
        if (id == R.id.action_settings) {
            return true;

        // bouton hamburger
        } else if(id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}