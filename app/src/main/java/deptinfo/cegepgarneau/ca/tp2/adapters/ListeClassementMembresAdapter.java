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
public class ListeClassementMembresAdapter extends ArrayAdapter<String>{

    private String[] listItems = null;
    private Context context = null;

    public ListeClassementMembresAdapter(Context context, int layoutRow, int layoutLabel, String[] arrItems) {
        super(context, layoutRow, layoutLabel, arrItems);
        this.context = context;
        this.listItems = arrItems;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = super.getView(position, convertView, parent);

        TextView txtRang = (TextView)row.findViewById(R.id.txtRang);
        txtRang.setText("#" + (position + 1));

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MainActivity activity = (MainActivity)context;
                activity.OpenFragment(new ProfilFragment());
                Toast.makeText(context,"Ouverture Profil",Toast.LENGTH_LONG).show();

            }
        });

        return (row);
    }

}