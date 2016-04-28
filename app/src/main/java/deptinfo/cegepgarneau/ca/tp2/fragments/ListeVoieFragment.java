package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.adapters.ListePistesAdapter;
import deptinfo.cegepgarneau.ca.tp2.classes.Piste;

/**
 * Created by Andrey on 2016-02-26.
 */
public class ListeVoieFragment extends ListFragment {

    private  String[] arrChoix ={"Terreur", "Nocturne", "Tranquile", "Beau Soleil", "Flamante"};
    private List<Piste> listeVoies = null;

    public ListeVoieFragment() {
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
        ListePistesAdapter adapter = new ListePistesAdapter(this.getContext(), R.layout.liste_item_pistes, R.id.txtNomPistes, ((MainActivity)getActivity()).GetPistes(Piste.TYPE_VOIE), Piste.TYPE_VOIE);
        this.setListAdapter(adapter);
        return v;
    }
}
