package com.isn.jetlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();

        /*
            Quand aucun titre n'est spécifié, l'action bar prend le nom du l'app
            On supprime donc le nom afin de mettre le hack en place plus tard
         */
        actionbar.setTitle("");

        // bouton menu à gauche le l'action bar
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_action_menu_jgreen);

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

        // gestion du menu de navigation
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);
                drawerLayout.closeDrawers();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

            if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }

            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}