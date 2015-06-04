package com.ericliu.playdesignlibrary;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by liu on 3/06/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    ActionBarDrawerToggle mDrawerToggle;
    TabLayout mTabLayout;

    protected boolean hasNavigationDrawer = false;
    protected boolean hasToolbar = false;
    protected boolean hasTabLayout = false;


    /**
     * setup the values of hasNavigationDrawer, hasToolbar
     */
    protected abstract void setLayoutFeatures();


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setLayoutFeatures();
        if (hasToolbar) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mToolbar.setTitle("Material Design");
        }

        if (hasNavigationDrawer && hasToolbar) {
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
            mDrawerToggle = new ActionBarDrawerToggle(this,
                    mDrawerLayout,
                    mToolbar,
                    R.string.drawer_open,
                    R.string.drawer_close) {
                public void onDrawerClosed(View v) {
                    super.onDrawerClosed(v);
                    invalidateOptionsMenu();
                    syncState();
                }

                public void onDrawerOpened(View v) {
                    super.onDrawerOpened(v);
                    invalidateOptionsMenu();
                    syncState();
                }

            };
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }

        if (hasTabLayout) {
            mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));
        }
    }


}
