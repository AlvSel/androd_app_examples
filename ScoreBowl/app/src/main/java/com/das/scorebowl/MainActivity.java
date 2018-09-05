package com.das.scorebowl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

//codigo de ejemplo seguido: http://guides.codepath.com/android/fragment-navigation-drawer
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView navList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Sesion.getSesion().getUserSesion(this) != null){
            mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            final ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24px);
            actionBar.setDisplayHomeAsUpEnabled(true);

            navList = (NavigationView) findViewById(R.id.left_drawer);
            setupDrawerContent(navList);

            //La primera vez se inicializa con el fragment por defecto
            //TODO: cambiar a un fragment mas interesante -> historial o clasificacion
            if(savedInstanceState==null){
                cambiarFragment(RulesFragment.class);
            }

        }else{
            finish();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Class fragmentClass = null;
        switch(menuItem.getItemId()) {
            //TODO: fragment añadir partida
            case R.id.nav_add:
                fragmentClass = EditProfileFragment.class;
                break;
            //TODO: fragment historial
            case R.id.nav_history:
                fragmentClass = EditProfileFragment.class;
                break;
            //TODO: fragment clasificacion
            case R.id.nav_classification:
                fragmentClass = EditProfileFragment.class;
                break;
            case R.id.nav_rules:
                fragmentClass = RulesFragment.class;
                break;
            case R.id.nav_edit:
                fragmentClass = EditProfileFragment.class;
                break;
            case R.id.nav_password:
                fragmentClass = ChangePassFragment.class;
                break;
            case R.id.nav_exit:
                Sesion.getSesion().cerrarSesion(getApplicationContext());
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }

        cambiarFragment(fragmentClass);
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private void cambiarFragment(Class fragmentClass){
        try{
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //si la pila de fragments está vacia -> finaliza la aplicación
        if(getSupportFragmentManager().getBackStackEntryCount()==0){
            finish();
        }
    }
}

