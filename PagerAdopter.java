package com.example.usman.virtualclinic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by usman on 4/1/2018.
 */

public class PagerAdopter extends FragmentStatePagerAdapter {

    int mnoofTabs;

    public PagerAdopter(FragmentManager fm, int NoofTabs)
    {
        super(fm);
        this.mnoofTabs=NoofTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                ClinicprofileFragment tab1= new ClinicprofileFragment();
                return tab1;
            case 1:
                AddingNewPatientFragment tab2= new AddingNewPatientFragment();
                return tab2;
            case 2:
                SelectDocFragment tab3 = new SelectDocFragment();
                return  tab3;
            case 3:
                ResponceOfDocFragment tab4 = new ResponceOfDocFragment();
                return  tab4;
            default:
                return null;

        }


    }

    @Override
    public int getCount() {
        return mnoofTabs;
    }
}

