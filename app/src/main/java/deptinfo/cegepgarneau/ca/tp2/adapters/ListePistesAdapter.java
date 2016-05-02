package deptinfo.cegepgarneau.ca.tp2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;

import deptinfo.cegepgarneau.ca.tp2.classes.Piste;

import deptinfo.cegepgarneau.ca.tp2.classes.PisteDataSource;
import deptinfo.cegepgarneau.ca.tp2.fragments.pisteFragment;

/**
 * Created by Renaud-Charles on 26/02/2016.
 */
public class ListePistesAdapter extends ArrayAdapter<Piste>{
    private PisteDataSource m_pisteDb;
    private Context context = null;
    private List<Piste> m_listePiste = null;
    private int m_type = 0;


    public ListePistesAdapter(Context context, int layoutRow, int layoutLabel, List<Piste> liste, int type) {
        super(context, layoutRow, layoutLabel, liste);
        this.context = context;
        this.m_listePiste = liste;
        this.m_type = type;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = super.getView(position, convertView, parent);

        Piste piste = m_listePiste.get(position);

        TextView nomPiste = (TextView)row.findViewById(R.id.txtNomPistes);
        nomPiste.setText(piste.GetNom());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MainActivity activity = (MainActivity)context;
                activity.OpenFragment(new pisteFragment());
                TextView txt = (TextView)v.findViewById(R.id.txtNomPistes);
                String s = txt.getText().toString();
                m_pisteDb = new PisteDataSource(activity);
                m_pisteDb.open();
                Piste piste = m_pisteDb.GetPisteByName(txt.getText().toString());
                m_pisteDb.close();

                Toast.makeText(context,"Selection de " + piste.GetID() + " : " + piste.GetNom(),Toast.LENGTH_LONG).show();

            }
        });

        return (row);
    }

    // Refresh the adapter.
    public void RefreshAdapter(){
        this.m_listePiste = ((MainActivity)context).GetPistes(m_type);
        notifyDataSetChanged();
    }
}