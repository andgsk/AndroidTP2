package deptinfo.cegepgarneau.ca.tp2.activities;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.fragments.InscriptionFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.InscriptionSuiteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.LoginFragment;

// L'activite login est utilise seulement pour le login
// avant d'entrer dans l'app.
public class LoginActivity extends AppCompatActivity {

    // Variables
    public android.support.v4.app.Fragment fragmentActu;
    public android.support.v4.app.FragmentTransaction fragmentTransaction;
    public FragmentManager fragmentManager = this.getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialisation du fragment
        // Creation du main fragment (news)
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            fragmentActu = getSupportFragmentManager().getFragment(savedInstanceState, "fragmentActu");
        }
        else {
            LoginFragment loginFragment = new LoginFragment();
            OpenFragment(loginFragment, true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (fragmentActu != null) {
            getSupportFragmentManager().putFragment(outState, "fragmentActu", fragmentActu);
        }
    }

    //Lorsque l'on clique connexion, on ferme lactivite et on
    //entre dans l'app.
    public void onConnexionClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        this.finish(); // Plus besoin de cette activite.
        startActivity(intent);
    }

    //Lorsque l'on clique inscription, on change de fragment.
    public void onInscriptionClick(View view){
        InscriptionFragment inscriptionFragment = new InscriptionFragment();
        OpenFragment(inscriptionFragment);
    }

    public void onInscriptionContinuerClick(View view){
        InscriptionSuiteFragment inscriptionSuiteFragment = new InscriptionSuiteFragment();
        OpenFragment(inscriptionSuiteFragment);
    }

    public void onInscriptionTerminerClick(View view){
        //On enleve les inscriptions du back stack.
        fragmentManager.popBackStackImmediate(null, fragmentManager.POP_BACK_STACK_INCLUSIVE);

        //Initialisation du fragment
        LoginFragment loginFragment = new LoginFragment();
        OpenFragment(loginFragment, true);
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

        super.onBackPressed();
    }

    public void onAnnuler(View view){
        onBackPressed();
    }

    // Fonction qui ouvre un fragment, shouldSkipBack est true si on skip le back stack.
    public void OpenFragment(android.support.v4.app.Fragment fragment, boolean shouldSkipBack) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        if (!shouldSkipBack)
            fragmentTransaction.addToBackStack(fragment.toString());

        fragmentTransaction.commit();

        fragmentActu = fragment;
    }

    public void OpenFragment(android.support.v4.app.Fragment fragment){
        OpenFragment(fragment, false);
    }
}
