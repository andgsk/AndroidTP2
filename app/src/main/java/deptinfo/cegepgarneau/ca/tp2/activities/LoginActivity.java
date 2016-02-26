package deptinfo.cegepgarneau.ca.tp2.activities;

import android.app.Fragment;
import android.app.FragmentManager;
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
    public android.support.v4.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialisation du fragment
        LoginFragment loginFragment = new LoginFragment();

        //Remplacement du contenu du FrameLayout par le fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, loginFragment);
        fragmentTransaction.commit();
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

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, inscriptionFragment);
        fragmentTransaction.addToBackStack("inscription");
        fragmentTransaction.commit();
    }

    public void onInscriptionContinuerClick(View view){
        InscriptionSuiteFragment inscriptionSuiteFragment = new InscriptionSuiteFragment();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, inscriptionSuiteFragment);
        fragmentTransaction.addToBackStack("inscriptionSuite");
        fragmentTransaction.commit();
    }

    public void onInscriptionTerminerClick(View view){
        //Fragment manager.
        FragmentManager fragmentManager = getFragmentManager();

        //Initialisation du fragment
        LoginFragment loginFragment = new LoginFragment();

        //Remplacement du contenu du FrameLayout par le fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, loginFragment);
        fragmentTransaction.commit();

        //On enleve les inscription du back stack.
        fragmentManager.popBackStack("inscriptionSuite", 0);
        fragmentManager.popBackStack("inscription", 0);
    }
}
