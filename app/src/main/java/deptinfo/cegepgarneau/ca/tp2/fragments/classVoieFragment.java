package deptinfo.cegepgarneau.ca.tp2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.adapters.ListeClassementMembresAdapter;

/**
 * Created by Andrey on 2016-02-26.
 */
public class classVoieFragment extends ListFragment {

    private  String[] arrChoix ={"Ross Reid", "Tamara Robbins", "Tasha Riley",
            "Stella Sanders", "Natasha Powell", "Emily Howard", "Courtney Paul"};

    public classVoieFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Création du fragment à partir du layout
        View v = inflater.inflate(R.layout.fragment_classvoie,container,false);

        this.setListAdapter(new ListeClassementMembresAdapter(this.getContext(), R.layout.liste_item_membres, R.id.txtNomMembre, this.arrChoix));
        return v;
    }
}
