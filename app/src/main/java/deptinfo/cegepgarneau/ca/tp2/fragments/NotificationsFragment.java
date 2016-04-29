package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.adapters.ListeNotificationsAdapter;

/**
 * Created by Renaud-Charles on 22/02/2016.
 */
public class NotificationsFragment extends ListFragment {

    // News!
    private  String[] arrChoix ={"Nouvelle Piste", "Nouvelle Piste", "Demande de Partenaire",
            "Nouvelle Piste", "Demande de Partenaire",
            "Nouvelle Piste", "Demande de Partenaire",
            "Demande de Partenaire", "Demande de Partenaire",
            "Nouvelle Piste", "Nouvelle Piste", "Nouvelle Piste"};

    // Constructeur
    public NotificationsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications,container,false);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mToolbar.setTitle(R.string.titre_notifications);

        this.setListAdapter(new ListeNotificationsAdapter(this.getContext(), R.layout.liste_item_notifications, R.id.txtNomPiste, this.arrChoix));
        return  view;
    }

}
