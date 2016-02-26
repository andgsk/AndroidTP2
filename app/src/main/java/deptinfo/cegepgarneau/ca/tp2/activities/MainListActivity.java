package deptinfo.cegepgarneau.ca.tp2.activities;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import deptinfo.cegepgarneau.ca.tp2.R;

/**
 * Created by Andrey on 2016-02-26.
 */
public class MainListActivity extends ListActivity {

    private TextView lblPistes;
    private String[] arrPistes = {"Blalalal", "ou", "ne",  "pas", "Blalalalalalalala", "telle", "est", "la", "lalalb"};


    protected void onListItemClick(ListView l, View v, int position, long id) {
        //lblPistes.setText(arrPistes[position]);
        //ou
        String value =  l.getItemAtPosition(position).toString();
        lblPistes.setText(value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrPistes));

        lblPistes = (TextView) this.findViewById(R.id.lblPistes);

    }

}
