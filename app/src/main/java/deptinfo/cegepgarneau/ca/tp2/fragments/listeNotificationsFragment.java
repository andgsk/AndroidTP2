package deptinfo.cegepgarneau.ca.tp2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deptinfo.cegepgarneau.ca.tp2.R;
import deptinfo.cegepgarneau.ca.tp2.activities.MainActivity;
import deptinfo.cegepgarneau.ca.tp2.adapters.TabsPagerAdapter;

/**
 * Created by Renaud-Charles on 22/02/2016.
 */
public class listeNotificationsFragment extends Fragment {

    // Variables
    private TabLayout tabLayout;
    private ViewPager viewPager;

    // Constructeur
    public listeNotificationsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications,container,false);

        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mToolbar.setTitle(R.string.titre_pistes);

        //Initialisation du viewPager et configuration du ViewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //Initialisation du TabLayout
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        //Association du TabLayout au ViewPager.
        tabLayout.setupWithViewPager(viewPager);

        return  view;
    }

    private void setupViewPager(ViewPager viewPager) {

        TabsPagerAdapter adapter = new TabsPagerAdapter(getActivity(),getChildFragmentManager());

        String[] tabs = getResources().getStringArray(R.array.tabs);

        //Ajout des fragments
        adapter.addFragment(new ListeBlocFragment(), tabs[0]);
        adapter.addFragment(new ListeVoieFragment(), tabs[1]);
        viewPager.setAdapter(adapter);
    }

}
