package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deptinfo.cegepgarneau.ca.tp2.R;

/**
 * Created by Andrey on 2016-02-26.
 */
public class pisteFragment extends Fragment {

    public pisteFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_piste,container,false);
        return  view;
    }
}