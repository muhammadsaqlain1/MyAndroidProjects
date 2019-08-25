package com.example.usman.virtualclinic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by usman on 4/1/2018.
 */

public class PagerAdopterDoctor extends FragmentStatePagerAdapter {

    int mnoofTabs;

    public PagerAdopterDoctor(FragmentManager fm, int NoofTabs)
    {
        super(fm);
        this.mnoofTabs=NoofTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                DocProfileFragment tab1= new DocProfileFragment();
                return tab1;
            case 1:
                RequestToDoctorFragment tab2= new RequestToDoctorFragment();
                return tab2;

            default:
                return null;

        }


    }

    @Override
    public int getCount() {
        return mnoofTabs;
    }
}

