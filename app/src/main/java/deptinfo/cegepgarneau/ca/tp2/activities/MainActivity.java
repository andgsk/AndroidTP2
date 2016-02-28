package deptinfo.cegepgarneau.ca.tp2.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
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
import deptinfo.cegepgarneau.ca.tp2.fragments.ListesPistesFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.NouvellesFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.ProfilFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.SettingsFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modProfilFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modLoginMdpFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.ajoutReussiteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modPisteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.critiquerFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.pisteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.addPisteFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variables
    public Toolbar mToolbar;
    public DrawerLayout mDrawerLayout;
    public NavigationView mNavigationView;
    public android.support.v4.app.FragmentTransaction fragmentTransaction;
    public android.support.v4.app.FragmentManager fragmentManager;
    public boolean showMenu = false;
    public Fragment fragmentActu;


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
        mToolbar, R.string.app_name, R.string.app_name);

        mDrawerLayout.setDrawerListener(toggle);

        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        // Creation du main fragment (news)
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            fragmentActu = getSupportFragmentManager().getFragment(savedInstanceState, "fragmentActu");
        }
        else {
            NouvellesFragment nouvellesFragment = new NouvellesFragment();

            // On ouvre la nouvelle, en skippant le backstack. Parce que c'est le premier.
            OpenFragment(nouvellesFragment, true);
        }
    }

    // Attraper les fragments du back press.
    @Override
    public void onBackPressed() {

        int nbBackFragments = getSupportFragmentManager().getBackStackEntryCount();

        if (nbBackFragments > 0){
            fragmentManager = getSupportFragmentManager();
            String nomDernierFragment = fragmentManager.getBackStackEntryAt(nbBackFragments-1).getName();
            fragmentActu = fragmentManager.findFragmentByTag(nomDernierFragment);
        }

        // Hide le menu.
        showMenu = false;
        RefreshTopMenu();

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (fragmentActu instanceof ProfilFragment) {
            getMenuInflater().inflate(R.menu.profil_menu, menu);
            return true;
        }
        else if (fragmentActu instanceof pisteFragment) {
            getMenuInflater().inflate(R.menu.piste_menu, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Boolean shouldSwitch = false;
        Fragment fragment = new NouvellesFragment();

        switch (item.getItemId()) {
            case R.id.modif_profil:
                shouldSwitch = true;
                fragment = new modLoginMdpFragment();
                break;
            case R.id.inviter_utilisateur:
                Toast.makeText(this, "Uilisateur invité!", Toast.LENGTH_LONG).show();
                fragment = new ListesPistesFragment();
                shouldSwitch = true;
                break;
            case R.id.critiquer_piste:
                fragment = new critiquerFragment();
                shouldSwitch = true;
                break;
            case R.id.modifier_piste:
                fragment = new modPisteFragment();
                shouldSwitch = true;
                break;
            case R.id.ajouter_piste:
                fragment = new addPisteFragment();
                shouldSwitch = true;
                break;

        }

            if (shouldSwitch == true) {
                OpenFragment(fragment);
            }
return true;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (fragmentActu != null) {
            getSupportFragmentManager().putFragment(outState, "fragmentActu", fragmentActu);
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Fonction qui gere.
        int id = item.getItemId();

        Toast.makeText(this, item.getTitle() + " selected!", Toast.LENGTH_LONG).show();

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
                fragment = new ListesPistesFragment();
                shouldSwitch = true;
                break;
            case R.id.nav_profil:
                showMenu = true;
                fragment = new ProfilFragment();
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

        return true;
    }

    public void RefreshTopMenu(){
        invalidateOptionsMenu();
    }

    public void onClick(View view)
    {
        Boolean shouldChange= false;
        Fragment fragment = new NouvellesFragment();

        switch (view.getId()) {
            case R.id.btnsaveajoutmodpiste:
                fragment = new ListesPistesFragment();
                shouldChange = true;
                break;
            case R.id.finishaddreussite:
                fragment = new ListesPistesFragment();
                shouldChange = true;
                break;
            case R.id.btnFinishCritiquer:
                fragment = new ListesPistesFragment();
                shouldChange = true;
                break;
            case R.id.btnsavemodutil:
                fragment = new ProfilFragment();
                shouldChange = true;
                break;
            case R.id.btnAddPic:

                Toast.makeText(this, "Image ajoutée, lol :D joke", Toast.LENGTH_LONG).show();

                break;
            case R.id.btnsavemodpiste:
                fragment = new ListesPistesFragment();
                shouldChange = true;
                break;
        }
        if (shouldChange == true) {
            OpenFragment(fragment);
        }
    }

    // Fonction qui ouvre un fragment, shouldSkipBack est true si on skip le back stack.
    public void OpenFragment(Fragment fragment, boolean shouldSkipBack){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        if (!shouldSkipBack)
            fragmentTransaction.addToBackStack(fragment.toString());

        fragmentTransaction.commit();

        fragmentActu = fragment;
        RefreshTopMenu();

        mDrawerLayout.closeDrawers();
    }

    public void OpenFragment(Fragment fragment){
        OpenFragment(fragment, false);
    }

}
