package deptinfo.cegepgarneau.ca.tp2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.classes.Demande;
import deptinfo.cegepgarneau.ca.tp2.fragments.ProfilFragment;

/**
 * Created by Renaud-Charles on 26/02/2016.
 */
    public class ListeDemandesAdapter extends ArrayAdapter<Demande>{

    private List<Demande>  listItems = null;
    private Context context = null;

    public ListeDemandesAdapter(Context context, int layoutRow, int layoutLabel,List<Demande> liste) {
        super(context, layoutRow, layoutLabel, liste);
        this.context = context;
        this.listItems = liste;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = super.getView(position, convertView, parent);

        row.findViewById(R.id.txtNomPiste).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v instanceof TextView) {
                    TextView txtNom = (TextView)v.findViewById(R.id.txtNomPiste);

                    MainActivity activity = (MainActivity) context;
                    activity.OpenFragment(new ProfilFragment());
                    Toast.makeText(context, "Profil de " + txtNom.getText(), Toast.LENGTH_LONG).show();
                }

            }
        });//coucoucheriiiiiii

        return (row);
    }

}