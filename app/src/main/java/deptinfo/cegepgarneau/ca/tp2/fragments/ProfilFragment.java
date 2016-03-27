package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.classes.Utilisateur;

/**
 * Created by Renaud-Charles on 22/02/2016.
 */
public class ProfilFragment extends Fragment {

    // Variables
    private Utilisateur m_user;

    // Constructeur
    public ProfilFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil,container,false);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mToolbar.setTitle(R.string.titre_profil);

        m_user = mainActivity.m_user;
        RemplirInfoUtilisateur(view, m_user);

        return  view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            //Restore the fragment's state here
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
    }

    // Rempli les infos d'un user.
    public void RemplirInfoUtilisateur(View view, Utilisateur user){
        TextView txtPrenomNom = (TextView) view.findViewById(R.id.txtPrenomNom);
        TextView txtAdresse = (TextView) view.findViewById(R.id.txtAdresse);
        TextView txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        TextView txtNoTelephone = (TextView) view.findViewById(R.id.txtNoTelephone);
        TextView txtType = (TextView) view.findViewById(R.id.txtStatus);

        txtPrenomNom.setText(user.GetPrenom() + " " + user.GetNom());
        txtAdresse.setText(user.GetAdresse());
        txtEmail.setText(user.GetEmail());
        txtNoTelephone.setText(user.GetNoTelephone());
        if (user.GetTypeCompte() == Utilisateur.TYPE_OUVREUR)
            txtType.setText("Ouvreur");
        else
            txtType.setText("Grimpeur");
    }

}
