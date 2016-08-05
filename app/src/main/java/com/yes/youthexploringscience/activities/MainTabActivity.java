package com.yes.youthexploringscience.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.yes.youthexploringscience.R;
import com.yes.youthexploringscience.fragments.CalendarFragment;
import com.yes.youthexploringscience.fragments.ContactsFragment;
import com.yes.youthexploringscience.fragments.LinksFragment;
import com.yes.youthexploringscience.tabs.ViewPagerAdapter;


public class MainTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter((getSupportFragmentManager()));
        adapter.addFragment(new LinksFragment(), "Links");
        adapter.addFragment(new CalendarFragment(), "Calendar");
        adapter.addFragment(new ContactsFragment(), "Contacts");
        viewPager.setAdapter(adapter);
    }
}
