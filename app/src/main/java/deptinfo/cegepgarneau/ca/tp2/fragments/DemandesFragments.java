package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.adapters.ListeDemandesAdapter;
import deptinfo.cegepgarneau.ca.tp2.classes.Demande;

/**
 * Created by Renaud-Charles on 22/02/2016.
 */
public class DemandesFragments extends ListFragment {


    // TODO AJOUTER LES USERS ICI, FAIRE CA EN REVENANT DE LA JOB.

    // Constructeur
    public DemandesFragments(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demandes_partenaires,container,false);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mToolbar.setTitle(R.string.titre_demandes);

        this.setListAdapter(new ListeDemandesAdapter(this.getContext(), R.layout.list_items_demandes, R.id.txtPrenomNom, ((MainActivity)getActivity()).GetDemandesActives()));
        return  view;
    }

}
