package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.adapters.ListeNouvellesAdapter;

/**
 * Created by Renaud-Charles on 22/02/2016.
 */
public class NouvellesFragment extends ListFragment {

    // News!
    private  String[] arrChoix ={"Nouvelles Pistes!", "C'est un crime.", "Quoi?" };

    // Constructeur
    public NouvellesFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nouveautes,container,false);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mToolbar.setTitle(R.string.titre_nouvelles);

        this.setListAdapter(new ListeNouvellesAdapter(this.getContext(), R.layout.liste_item_nouvelles, R.id.txtTitreNouvelle, this.arrChoix));
        return  view;
    }

}
