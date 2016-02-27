package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.R;

/**
 * Created by Andrey on 2016-02-27.
 */
public class ajoutReussiteFragment extends Fragment {
    public ajoutReussiteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addreussite,container,false);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mToolbar.setTitle(R.string.titre_profil);
        return view;
    }
}
