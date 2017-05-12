package com.example.jaker.wildlife2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private boolean ignoreBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HomeFragment fragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, "HOME").commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        //MenuItem edit = (MenuItem) toolbar.findViewById(R.id.action_edit);



        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!ignoreBack)
            {
                super.onBackPressed();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem edit = (MenuItem) menu.findItem(R.id.action_edit);
        if (edit != null)
        {
            edit.setVisible(false);
        }
        MenuItem delete = (MenuItem) menu.findItem(R.id.action_delete);
        if (delete != null)
        {
            delete.setVisible(false);
        }
        MenuItem back = (MenuItem) menu.findItem(R.id.action_back);
        if (back != null)
        {
            back.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getSupportFragmentManager().findFragmentByTag("HOME") != null)
        {
            if (getSupportFragmentManager().findFragmentByTag("HOME").isVisible())
            {
                TextView helpTV1 = (TextView) findViewById(R.id.help1TV);
                TextView helpTV2 = (TextView) findViewById(R.id.help2TV);
                int orientation = newConfig.orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE && helpTV1 != null)
                {
                    helpTV1.setVisibility(View.GONE);
                    helpTV2.setVisibility(View.GONE);
                }
                else if (orientation == Configuration.ORIENTATION_PORTRAIT && helpTV1 != null)
                {
                    helpTV1.setVisibility(View.VISIBLE);
                    helpTV2.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return false;

        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_edit) {
            //return true;
        //}

        //return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        toolbar.setTitle(getString(R.string.app_name));
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        switch(id)
        {
            case R.id.nav_home:

                Fragment hf = new HomeFragment();
                if (!ignoreBack)
                {
                    fm.beginTransaction().replace(R.id.fragment_container, hf, "HOME").addToBackStack("TAG").commit();
                }
                else
                    {
                        fm.beginTransaction().replace(R.id.fragment_container, hf, "HOME").commit();
                    }
                ignoreBack = false;
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                hideItems();
                break;
            case R.id.nav_learn:

                Fragment lf = new LearnFragment();
                if (!ignoreBack)
                {
                    fm.beginTransaction().replace(R.id.fragment_container, lf, "LEARN").addToBackStack("TAG").commit();
                }
                else
                    {
                        fm.beginTransaction().replace(R.id.fragment_container, lf, "LEARN").commit();
                    }
                ignoreBack = false;
                Toast.makeText(this, "Learn", Toast.LENGTH_SHORT).show();
                hideItems();
                break;
            case R.id.nav_wlfeed:

                Fragment wff = new WildlifeFeedFragment();
                if (!ignoreBack)
                {
                    fm.beginTransaction().replace(R.id.fragment_container, wff).addToBackStack("TAG").commit();
                }
                else
                    {
                        fm.beginTransaction().replace(R.id.fragment_container, wff).commit();
                    }
                ignoreBack = false;
                Toast.makeText(this, "Wildlife Feed", Toast.LENGTH_SHORT).show();
                hideItems();
                break;
            case R.id.nav_quiz:
                ignoreBack = true;
                Fragment qf = new QuizFragment();

                fm.beginTransaction().replace(R.id.fragment_container, qf).commit();
                Toast.makeText(this, "Quiz", Toast.LENGTH_SHORT).show();
                hideItems();
                break;
            case R.id.nav_video:

                Fragment vf = new VideoFragment();
                if (!ignoreBack)
                {
                    fm.beginTransaction().replace(R.id.fragment_container, vf).addToBackStack("TAG").commit();
                }
                else
                    {
                        fm.beginTransaction().replace(R.id.fragment_container, vf).commit();
                    }
                ignoreBack = false;
                Toast.makeText(this, "Videos", Toast.LENGTH_SHORT).show();
                hideItems();
                break;
            case R.id.nav_doc:


                Fragment df = new DocumenterFragment();
                if (!ignoreBack)
                {
                    fm.beginTransaction().replace(R.id.fragment_container, df).addToBackStack("TAG").commit();
                }
                else
                {
                    fm.beginTransaction().replace(R.id.fragment_container, df).commit();
                }
                ignoreBack = false;
                Toast.makeText(this, "Documenter", Toast.LENGTH_SHORT).show();

                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideItems()
    {
        MenuItem edit = (MenuItem) toolbar.getMenu().findItem(R.id.action_edit);
        if (edit != null)
        {
            edit.setVisible(false);
        }
        MenuItem back = (MenuItem) toolbar.getMenu().findItem(R.id.action_back);
        if (back != null)
        {
            back.setVisible(false);
        }
        MenuItem delete = (MenuItem) toolbar.getMenu().findItem(R.id.action_delete);
        if (delete != null)
        {
            delete.setVisible(false);
        }
    }
}
