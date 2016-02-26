package deptinfo.cegepgarneau.ca.tp2.activities;

import android.support.design.widget.NavigationView;
<<<<<<< HEAD
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
=======
import android.support.v4.app.Fragment;
>>>>>>> 027125340514c24854a334f76e3c9ba758b3d22c
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import deptinfo.cegepgarneau.ca.tp2.R;
<<<<<<< HEAD
import deptinfo.cegepgarneau.ca.tp2.adapters.TabsPagerAdapter;
import deptinfo.cegepgarneau.ca.tp2.fragments.classBlockFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.classVoieFragment;
=======
import deptinfo.cegepgarneau.ca.tp2.fragments.ClassementsFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.NouvellesFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.PistesFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.ProfilFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.SettingsFragment;
>>>>>>> 027125340514c24854a334f76e3c9ba758b3d22c

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variables
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
<<<<<<< HEAD
    public android.support.v4.app.FragmentTransaction fragmentTransaction;
    private TabLayout tabLayout;
    private ViewPager viewPager;
=======
    private android.support.v4.app.FragmentTransaction fragmentTransaction;

>>>>>>> 027125340514c24854a334f76e3c9ba758b3d22c

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


        //Initialisation du viewPager et configuration du ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //Initialisation du TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //Association du TabLayout au ViewPager.
        tabLayout.setupWithViewPager(viewPager);

        //Gestionnaire d'événements sur la sélection d'un onglet.
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getApplicationContext(),"Selection de " + tab.getText(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Toast.makeText(getApplicationContext(),"Désélection de " + tab.getText(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(getApplicationContext(),"Resélection de " + tab.getText(),Toast.LENGTH_LONG).show();
            }
        });

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

    private void setupViewPager(ViewPager viewPager) {

        //instanciation du FragmentPageAdapter pour la gestion
        //des fragments lors du changement d'onglet.
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());

        //On obtient le titre des onglets à parir du string-array
        //dans le fichier res/values/strings.xml
        String[] tabs = getResources().getStringArray(R.array.tabs);

        //Ajout des fragments
        adapter.addFragment(new classBlockFragment(), tabs[0]);
        adapter.addFragment(new classVoieFragment(), tabs[1]);
        viewPager.setAdapter(adapter);
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
                fragment = new PistesFragment();
                shouldSwitch = true;
                break;
            case R.id.nav_profil:
                fragment = new ProfilFragment();
                shouldSwitch = true;
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                shouldSwitch = true;
                break;
            default:
                break;
        }

        if (shouldSwitch) {
            //Remplacement du contenu du FrameLayout par le fragment
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack("page " + id);
            fragmentTransaction.commit();

            mDrawerLayout.closeDrawers();
        }

        return true;
    }

}
