package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;

/**
 * Created by Andrey on 2016-02-27.
 */
public class addPisteFragment extends Fragment  implements Spinner.OnItemSelectedListener {
    private  String[] arrDiff ={"5.5", "5.6", "5.7",
            "5.8", "5.9", "5.10a", "5.10b", "5.10c", "5.10d", "5.11a",
            "5.11b", "5.11c", "5.11d", "5.12a", "5.12b", "5.12c", "5.12d",
            "5.13a", "5.13b", "5.13c", "5.13d",
            "5.14a", "5.14b","5.14c", "5.14d","5.15a", "5.15b","v0", "v1", "v2",
            "v3", "v4", "v5", "v6", "v7", "v8", "v9",
            "v10", "v11", "v12", "v13", "v14", "v15"};

    public addPisteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addpiste,container,false);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mToolbar.setTitle(R.string.titre_pistes);


        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerDiff);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrDiff);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
