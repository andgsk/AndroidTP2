package deptinfo.cegepgarneau.ca.tp2.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.fragments.ClassementsFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.DemandesFragments;
import deptinfo.cegepgarneau.ca.tp2.fragments.NouvellesFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.PistesFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.ProfilFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.SettingsFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modProfilFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modLoginMdpFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.ajoutReussiteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modPisteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.critiquerFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variables
    public Toolbar mToolbar;
    public DrawerLayout mDrawerLayout;
    public NavigationView mNavigationView;
    public android.support.v4.app.FragmentTransaction fragmentTransaction;
    public boolean showMenu = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Affichage du drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
        mToolbar, R.string.txt_nav_header, R.string.txt_nav_header);

        mDrawerLayout.setDrawerListener(toggle);

        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        // Creation du main fragment (news)
        NouvellesFragment nouvellesFragment = new NouvellesFragment();

        //Remplacement du contenu du FrameLayout par le fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, nouvellesFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return showMenu;
    }

    public void inflateMenu(){

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Fonction qui gere.
        int id = item.getItemId();

        Toast.makeText(this, item.getTitle() + " selected!", Toast.LENGTH_LONG).show();

        // Hide le menu.
        showMenu = false;

        boolean shouldSwitch = false;
        Fragment fragment = new NouvellesFragment();

        switch(id)
        {
            case R.id.nav_news:
                fragment = new NouvellesFragment();
                shouldSwitch = true;
                break;
            case R.id.nav_classements:
                fragment = new ClassementsFragment();
                shouldSwitch = true;
                break;
            case R.id.nav_pistes:
                fragment = new PistesFragment();
                shouldSwitch = true;
                break;
            case R.id.nav_profil:
                showMenu = true;
                fragment = new modProfilFragment();
                shouldSwitch = true;
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                shouldSwitch = true;
                break;
            case R.id.nav_demandes:
                fragment = new DemandesFragments();
                shouldSwitch = true;
                break;
            default:
                break;
        }

        if (shouldSwitch) {
            OpenFragment(fragment);
        }

        // Refresh le menu.
        invalidateOptionsMenu();

        return true;
    }

    public void onClick(View view)
    {
        Boolean shouldChange= false;
        Fragment fragment = new NouvellesFragment();

        switch (view.getId()) {
            case R.id.modProfil:
                shouldChange = true;
                fragment = new modLoginMdpFragment();
                break;
            case R.id.btnSaveProfil:
            shouldChange = true;
            fragment = new ProfilFragment();
            break;
            case R.id.btnAddReussite:
                shouldChange = true;
                fragment = new ajoutReussiteFragment();
                break;
            case R.id.btnCritiquer:
                shouldChange = true;
                fragment = new critiquerFragment();
                break;
            case R.id.btnMod:
                shouldChange = true;
                fragment = new modPisteFragment();
                break;
        }
        if (shouldChange == true) {
            OpenFragment(fragment);
        }
    }

    public void OpenFragment(Fragment fragment){    //Remplacement du contenu du FrameLayout par le fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();

        mDrawerLayout.closeDrawers();
    }

}
