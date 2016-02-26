package deptinfo.cegepgarneau.ca.tp2.activities;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.adapters.TabsPagerAdapter;
import deptinfo.cegepgarneau.ca.tp2.fragments.classBlockFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.classVoieFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variables
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    public android.support.v4.app.FragmentTransaction fragmentTransaction;
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        Toast.makeText(this, item.toString() + "Selected!", Toast.LENGTH_LONG).show();
        return false;
    }
}