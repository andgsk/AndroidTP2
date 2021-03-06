package deptinfo.cegepgarneau.ca.tp2.activities;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.classes.Demande;
import deptinfo.cegepgarneau.ca.tp2.classes.DemandeDataSource;
import deptinfo.cegepgarneau.ca.tp2.classes.Piste;
import deptinfo.cegepgarneau.ca.tp2.classes.PisteDataSource;
import deptinfo.cegepgarneau.ca.tp2.classes.Utilisateur;
import deptinfo.cegepgarneau.ca.tp2.classes.UtilisateurDataSource;
import deptinfo.cegepgarneau.ca.tp2.fragments.ClassementsFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.DemandesFragments;
import deptinfo.cegepgarneau.ca.tp2.fragments.ListesPistesFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.NotificationsFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.NouvellesFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.ProfilFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.SettingsFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modAdresseFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modProfilFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modLoginMdpFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.ajoutReussiteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.modPisteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.critiquerFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.pisteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.addPisteFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variables
    private PisteDataSource m_pisteDb;
    private DemandeDataSource m_demandeDb;

    public Utilisateur m_user;
    public Piste m_pisteActu;
    public Toolbar mToolbar;
    public DrawerLayout mDrawerLayout;
    public NavigationView mNavigationView;
    public android.support.v4.app.FragmentTransaction fragmentTransaction;
    public android.support.v4.app.FragmentManager fragmentManager;
    public boolean showMenu = false;
    public Fragment fragmentActu;
    public String score = "19";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_pisteDb = new PisteDataSource(this);
        m_demandeDb = new DemandeDataSource(this);

        // On va cherche l'utilisateur principale. S'il n'existe pas, on retourne au login.
        Intent intent = getIntent();
        m_user = (Utilisateur) intent.getSerializableExtra("Utilisateur");
        if (m_user == null){
            Intent loginIntent = new Intent(this, LoginActivity.class);
            this.finish(); // Plus besoin de cette activite.
            startActivity(loginIntent);
            return;
        }

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
            NotificationsFragment notificationFragment = new NotificationsFragment();

            // On ouvre la nouvelle, en skippant le backstack. Parce que c'est le premier.
            OpenFragment(notificationFragment, true);
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
                AjouterDemande();
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
            case R.id.modifier_adresse:
                fragment = new modAdresseFragment();
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

        boolean shouldSwitch = false;
        Fragment fragment = new NouvellesFragment();

        switch(id)
        {
            case R.id.nav_notifications:
                fragment = new NotificationsFragment();
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
            case R.id.nav_ajouter_piste:
                fragment = new addPisteFragment();
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
            case R.id.btnAjouterPiste:
                Piste piste = AjouterUnePiste();
                if (piste != null) {
                    m_pisteActu = piste;
                    fragment = new ListesPistesFragment();
                    shouldChange = true;
                }
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
            case R.id.btnSaveAdresse:
                fragment = new ProfilFragment();
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

    public int AjouterDemande(){
        int newId;
        Demande dema = null;

        String username = this.m_user.GetUsername();
        String niveau = score;

        m_demandeDb.open();


        newId = (int) m_demandeDb.InsertDemande(new Demande(username, niveau));
        m_demandeDb.close();

        Toast.makeText(this, 0 + " : demande ajoutée avec success.", Toast.LENGTH_LONG).show();

        return newId;
    }

    // Fonction qui ajoute une piste dans le system.
    public Piste AjouterUnePiste(){
        Piste piste = null;

        Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
        Spinner spinnerDiff = (Spinner) findViewById(R.id.spinnerDiff);

        String nomPiste = ((EditText)findViewById(R.id.txtNomPiste)).getText().toString();
        String description = ((EditText)findViewById(R.id.txtDescription)).getText().toString();
        String difficulte = addPisteFragment.arrDiff[spinnerDiff.getSelectedItemPosition()];
        String type = addPisteFragment.arrType[spinnerType.getSelectedItemPosition()];

        if (nomPiste.isEmpty())
            Toast.makeText(this, "Veuillez entrer un nom de piste.", Toast.LENGTH_LONG).show();
        else if (description.isEmpty())
            Toast.makeText(this, "Veuillez entrer une description.", Toast.LENGTH_LONG).show();
        else{
            m_pisteDb.open();
            int typePiste = Piste.TYPE_BLOC;
            if (type.equals("Voie"))
                typePiste = Piste.TYPE_VOIE;

            piste = m_pisteDb.Insert(new Piste(nomPiste,typePiste,m_user.GetUsername(),description,difficulte));
            m_pisteDb.close();

            Toast.makeText(this, type + " : Piste ajoute avec success.", Toast.LENGTH_LONG).show();
        }

        return piste;
    }

    // Return une liste de pistes.
    public List<Piste> GetPistes(int type){
        List<Piste> list;
        m_pisteDb.open();
        list = m_pisteDb.GetAllPistesType(type);
        m_pisteDb.close();
        return list;
    }

    public List<Demande> GetDemandesActives(){
        List<Demande> list;
        m_demandeDb.open();
        list = m_demandeDb.GetAllDemandesType(0);
        m_demandeDb.close();
        return list;
    }

}
