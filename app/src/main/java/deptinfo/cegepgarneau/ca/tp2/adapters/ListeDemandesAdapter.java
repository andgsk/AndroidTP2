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
public class ListeDemandesAdapter extends ArrayAdapter<String>{

    private String[] listItems = null;
    private Context context = null;

    public ListeDemandesAdapter(Context context, int layoutRow, int layoutLabel, String[] arrItems) {
        super(context, layoutRow, layoutLabel, arrItems);
        this.context = context;
        this.listItems = arrItems;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = super.getView(position, convertView, parent);

        row.findViewById(R.id.txtTitreNotifications).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v instanceof TextView) {
                    TextView txtNom = (TextView)v.findViewById(R.id.txtTitreNotifications);

                    MainActivity activity = (MainActivity) context;
                    activity.OpenFragment(new ProfilFragment());
                    Toast.makeText(context, "Profil de " + txtNom.getText(), Toast.LENGTH_LONG).show();
                }

            }
        });

        return (row);
    }

}