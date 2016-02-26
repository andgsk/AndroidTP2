package deptinfo.cegepgarneau.ca.tp2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;

import deptinfo.cegepgarneau.ca.tp2.R;

/**
 * Created by Andrey on 2016-02-26.
 */
public class classVoieFragment extends Fragment {
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
        return v;
    }
}
