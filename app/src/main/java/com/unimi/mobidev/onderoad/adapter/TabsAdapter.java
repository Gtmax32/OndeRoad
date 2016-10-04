package com.unimi.mobidev.onderoad.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.unimi.mobidev.onderoad.fragment.FavoritesFragment;
import com.unimi.mobidev.onderoad.fragment.MainFragment;
import com.unimi.mobidev.onderoad.fragment.SettingsFragment;
import com.unimi.mobidev.onderoad.fragment.SpotFragment;


public class TabsAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    private MainFragment mf = new MainFragment();
    private FavoritesFragment ff = new FavoritesFragment();
    private SpotFragment spf = new SpotFragment();
    private SettingsFragment sf = new SettingsFragment();

    public TabsAdapter(FragmentManager fm, int number){
        super(fm);

        numberOfTabs = number;
    }

    public Fragment getItem(int pos){

        switch(pos){
            case 0:
                return mf;
            case 1:
                return ff;
            case 2:
                return spf;
            case 3:
                return sf;
            default:
                return null;
        }
    }

    public int getCount() {
        return numberOfTabs;
    }

    public MainFragment getMain(){
        return mf;
    }

    public FavoritesFragment getFavorites(){
        return ff;
    }

    public SpotFragment getSpot(){
        return spf;
    }

    public SettingsFragment getSettings(){
        return sf;
    }
}
