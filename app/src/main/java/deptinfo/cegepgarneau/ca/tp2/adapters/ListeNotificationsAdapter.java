package deptinfo.cegepgarneau.ca.tp2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Renaud-Charles on 26/02/2016.
 */
public class ListeNotificationsAdapter extends ArrayAdapter<String>{

    private String[] listItems = null;
    private Context context = null;

    public ListeNotificationsAdapter(Context context, int layoutRow, int layoutLabel, String[] arrItems) {
        super(context, layoutRow, layoutLabel, arrItems);
        this.context = context;
        this.listItems = arrItems;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = super.getView(position, convertView, parent);

        return (row);
    }

}