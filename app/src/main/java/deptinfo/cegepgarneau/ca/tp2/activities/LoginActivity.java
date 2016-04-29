package deptinfo.cegepgarneau.ca.tp2.activities;

import android.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.classes.ConnexionInfo;
import deptinfo.cegepgarneau.ca.tp2.classes.DataSource;
import deptinfo.cegepgarneau.ca.tp2.classes.JsonParser;
import deptinfo.cegepgarneau.ca.tp2.classes.Utilisateur;
import deptinfo.cegepgarneau.ca.tp2.classes.UtilisateurDataSource;
import deptinfo.cegepgarneau.ca.tp2.fragments.InscriptionFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.InscriptionSuiteFragment;
import deptinfo.cegepgarneau.ca.tp2.fragments.LoginFragment;

// L'activite login est utilise seulement pour le login
// avant d'entrer dans l'app.
public class LoginActivity extends AppCompatActivity {

    // Variables
    private DataSource m_ds;
    private Utilisateur m_user = null;
    private final static int CONNECTION_TIMEOUT = 5000;
    private HttpURLConnection mHttpURLConnection = null;

    public UtilisateurDataSource m_utilisateurDataSource;
    public android.support.v4.app.Fragment fragmentActu;
    public android.support.v4.app.FragmentTransaction fragmentTransaction;
    public FragmentManager fragmentManager = this.getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        m_utilisateurDataSource = new UtilisateurDataSource(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialisation du fragment
        // Creation du main fragment (news)
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            fragmentActu = getSupportFragmentManager().getFragment(savedInstanceState, "fragmentActu");
        } else {
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
    public void onConnexionClick(View view) {

        EditText loginView = (EditText) findViewById(R.id.login);
        EditText mdpView = (EditText) findViewById(R.id.mdp);

        // Utilisateurs par defaut
        CreateDefaultUsers();

        m_utilisateurDataSource.open();
        m_user = m_utilisateurDataSource.GetUtilisateur(loginView.getText().toString(), mdpView.getText().toString());
        Toast.makeText(this, loginView.getText().toString() + " : " + mdpView.getText().toString(), Toast.LENGTH_LONG).show();
        m_utilisateurDataSource.close();

        if (m_user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Utilisateur", m_user);
            this.finish(); // Plus besoin de cette activite.
            startActivity(intent);
        } else {
            Toast.makeText(this, "Mauvais utilisateur/mot de passe.", Toast.LENGTH_LONG).show();
        }
    }

    //Lorsque l'on clique inscription, on change de fragment.
    public void onInscriptionClick(View view) {
        InscriptionFragment inscriptionFragment = new InscriptionFragment();
        OpenFragment(inscriptionFragment);
    }

    // Inscription principale.
    public void onInscriptionContinuerClick(View view) {
        String txtUsername = ((EditText) findViewById(R.id.txtUsername)).getText().toString();
        String txtMDP = ((EditText) findViewById(R.id.txtMDP)).getText().toString();
        String txtMDPConf = ((EditText) findViewById(R.id.txtMDPConf)).getText().toString();

        if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtMDP) || TextUtils.isEmpty(txtMDPConf)) {
            Toast.makeText(this, "Informations manquantes", Toast.LENGTH_LONG).show();
        } else if (!txtMDP.equals(txtMDPConf)) {
            Toast.makeText(this, "Mot de passe incorrect", Toast.LENGTH_LONG).show();
        } else {
            boolean estCreer = CreerNouvelleUtilisateur(txtUsername, txtMDP, Utilisateur.TYPE_GRIMPEUR);
            if (!estCreer) {
                Toast.makeText(this, "Impossible de creer l'utilisateur", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Utilisateur creer avec succes", Toast.LENGTH_LONG).show();
                InscriptionSuiteFragment inscriptionSuiteFragment = new InscriptionSuiteFragment();
                OpenFragment(inscriptionSuiteFragment);
            }
        }
    }

    public void onInscriptionTerminerClick(View view) {
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

        if (nbBackFragments > 0) {
            fragmentManager = getSupportFragmentManager();
            String nomDernierFragment = fragmentManager.getBackStackEntryAt(nbBackFragments - 1).getName();
            fragmentActu = fragmentManager.findFragmentByTag(nomDernierFragment);
        }

        super.onBackPressed();
    }

    public void onAnnuler(View view) {
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

    public void OpenFragment(android.support.v4.app.Fragment fragment) {
        OpenFragment(fragment, false);
    }

    // Fonction utilise pour creer des utilisateurs aleatoire.
    public void CreateDefaultUsers() {
        m_utilisateurDataSource.open();
        m_utilisateurDataSource.InsertUser(new Utilisateur("Andre", "Andre", Utilisateur.TYPE_GRIMPEUR));
        m_utilisateurDataSource.InsertUser(new Utilisateur("Marc", "Marc", Utilisateur.TYPE_GRIMPEUR));
        m_utilisateurDataSource.InsertUser(new Utilisateur("Marie", "Marie", Utilisateur.TYPE_GRIMPEUR));
        m_utilisateurDataSource.InsertUser(new Utilisateur("Simone", "Simone", Utilisateur.TYPE_GRIMPEUR));
        m_utilisateurDataSource.InsertUser(new Utilisateur("Eve", "Eve", Utilisateur.TYPE_OUVREUR));
        m_utilisateurDataSource.InsertUser(new Utilisateur("1", "1", Utilisateur.TYPE_OUVREUR));
        m_utilisateurDataSource.close();
    }

    ;

    public boolean CreerNouvelleUtilisateur(String username, String password, int type) {
        Utilisateur user = new Utilisateur(username, password, type);
        m_utilisateurDataSource.open();
        int id = m_utilisateurDataSource.InsertUser(user);
        m_utilisateurDataSource.close();

        if (id != Utilisateur.ID_UNDEFINED)
            return true;
        else
            return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        new GetAllUsers().execute((Void) null);
    }

    private class GetAllUsers extends AsyncTask<Void, Void, ArrayList<Utilisateur>> {

        Exception mException;

        /**
         * Méthode exécutée SYNChrone avant l'exécution de la tâche asynchrone.
         */
        @Override
        protected void onPreExecute() {
            ShowLoading(true);
        }


        @Override
        protected ArrayList<Utilisateur> doInBackground(Void... params) {
            ArrayList<Utilisateur> listeUser = null;

            try {

                URL url = new URL("http", ConnexionInfo.WEB_SERVICE_URL, ConnexionInfo.PORT, "/user/all");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                String body = readStream(httpURLConnection.getInputStream());
                Log.i("TAG", "Reçu (GET) : " + body);
                listeUser = JsonParser.deserializeUserArray(body);

            } catch (Exception e) {
                mException = e;
            } finally {
                if (mHttpURLConnection != null) {
                    mHttpURLConnection.disconnect();
                }
            }
            return listeUser;
        }

        private String readStream(InputStream in) {

            StringBuilder sb = new StringBuilder();

            try {
                //Lecture du flux de données
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                mException = e;
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(ArrayList<Utilisateur> users) {

            ShowLoading(false);

            if (mException == null && users != null) {
                for (Utilisateur user : users) {
                    Toast.makeText(LoginActivity.this, user.GetUsername(), Toast.LENGTH_LONG).show();
                }
            } else {
                Log.e("TAG", "Erreur lors de la récupération des utilisateurs (GET)", mException);
            }
        }


    }

    // Show le spinner ou pas.
    public void ShowLoading(boolean show){
        ProgressBar pg = (ProgressBar) findViewById(R.id.progress);
        if (show)
            pg.setVisibility(View.VISIBLE);
        else
            pg.setVisibility(View.GONE);
    }

}

