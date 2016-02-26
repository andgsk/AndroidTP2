package deptinfo.cegepgarneau.ca.tp2.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class TabsPagerAdapter extends FragmentPagerAdapter {

    Context ctxt = null;

    //Permet de conserver la liste des fragments et leurs titres
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public TabsPagerAdapter(Context ctxt, FragmentManager fm) {
        super(fm);
        this.ctxt = ctxt;
    }

    @Override
    public Fragment getItem(int position) {

        //retourne le fragment sélectionné
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        //retourne le nombre d'onglet (fragment)
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Retourne le titre de l'onglet.
        return mFragmentTitleList.get(position);
    }
}
