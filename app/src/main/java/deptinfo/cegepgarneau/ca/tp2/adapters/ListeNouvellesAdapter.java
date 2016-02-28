package deptinfo.cegepgarneau.ca.tp2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.fragments.ProfilFragment;

/**
 * Created by Renaud-Charles on 26/02/2016.
 */
public class ListeNouvellesAdapter extends ArrayAdapter<String>{

    private String[] listItems = null;
    private Context context = null;

    public ListeNouvellesAdapter(Context context, int layoutRow, int layoutLabel, String[] arrItems) {
        super(context, layoutRow, layoutLabel, arrItems);
        this.context = context;
        this.listItems = arrItems;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = super.getView(position, convertView, parent);

        return (row);
    }

}