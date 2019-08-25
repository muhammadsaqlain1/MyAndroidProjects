package com.example.usman.virtualclinic;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DoctorMainActivity extends AppCompatActivity implements DocProfileFragment.OnFragmentInteractionListener,RequestToDoctorFragment.OnFragmentInteractionListener {

    public static String demail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);


        final TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayoutDoc);
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Requests"));


        final ViewPager viewPager = (ViewPager)findViewById(R.id.pagerDoc);

        final PagerAdopterDoctor adopter = new PagerAdopterDoctor(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adopter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
