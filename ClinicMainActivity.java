package com.example.usman.virtualclinic;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClinicMainActivity extends AppCompatActivity implements ClinicprofileFragment.OnFragmentInteractionListener,AddingNewPatientFragment.OnFragmentInteractionListener,SelectDocFragment.OnFragmentInteractionListener,ResponceOfDocFragment.OnFragmentInteractionListener{

    public static String cemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_main);


        final TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Add New Patient"));
        tabLayout.addTab(tabLayout.newTab().setText("Patients"));
        tabLayout.addTab(tabLayout.newTab().setText("Responces"));

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);

        final PagerAdopter adopter = new PagerAdopter(getSupportFragmentManager(),tabLayout.getTabCount());

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
