package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.adapters.ListeDemandesAdapter;

/**
 * Created by Renaud-Charles on 22/02/2016.
 */
public class DemandesFragments extends ListFragment {

    private  String[] arrChoix ={"Simon Jean", "Samuel Denis", "Lorel Tracy",
            "Vanessa Dugris", "Maurice Jolie"};

    // Constructeur
    public DemandesFragments(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demandes_partenaires,container,false);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mToolbar.setTitle(R.string.titre_demandes);

        this.setListAdapter(new ListeDemandesAdapter(this.getContext(), R.layout.list_items_demandes, R.id.txtNomMembre, this.arrChoix));
        return  view;
    }

}
