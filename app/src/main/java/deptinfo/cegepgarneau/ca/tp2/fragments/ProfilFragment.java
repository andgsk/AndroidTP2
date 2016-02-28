package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;

/**
 * Created by Renaud-Charles on 22/02/2016.
 */
public class ProfilFragment extends Fragment {

    // Constructeur
    public ProfilFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil,container,false);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mToolbar.setTitle(R.string.titre_profil);

        /*
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MainActivity activity = (MainActivity) context;
                activity.OpenFragment(new PistesFragment());
                Toast.makeText(context, "Invitation envoye!", Toast.LENGTH_LONG).show();

            }
        });
        */

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

}
