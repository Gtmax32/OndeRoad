package com.unimi.mobidev.onderoad.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.other.TabsAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Drawable> tabIcons;
    private ViewPager viewPager;
    private TabsAdapter adapter;
    private TextView tempText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String facebook_data = "";

        facebook_data = getIntent().getStringExtra("User_name") + getIntent().getStringExtra("User_surname");

        System.out.println("Facebook data: "  + facebook_data);

        tempText = (TextView) findViewById(R.id.tempText);
        tempText.setText(facebook_data);

        initIconList();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        TabLayout.Tab list = tabLayout.newTab(), favorites = tabLayout.newTab(),
                      spot = tabLayout.newTab(), settings = tabLayout.newTab();

        View listView = getLayoutInflater().inflate(R.layout.tab_layout,null);
        ImageView listIcon = (ImageView) listView.findViewById(R.id.imageView);
        TextView listLabel = (TextView) listView.findViewById(R.id.textView);
        listIcon.setImageResource(R.drawable.ic_list_white_24dp);
        listLabel.setText("Tutti");

        View favView = getLayoutInflater().inflate(R.layout.tab_layout,null);
        ImageView favIcon = (ImageView) favView.findViewById(R.id.imageView);
        TextView favLabel = (TextView) favView.findViewById(R.id.textView);
        favIcon.setImageResource(R.drawable.ic_grade_black_24dp);
        favLabel.setText("Favorites");

        View spotView = getLayoutInflater().inflate(R.layout.tab_layout,null);
        ImageView spotIcon = (ImageView) spotView.findViewById(R.id.imageView);
        TextView spotLabel = (TextView) spotView.findViewById(R.id.textView);
        spotIcon.setImageResource(R.drawable.ic_place_black_24dp);
        spotLabel.setText("Spot");

        View settView = getLayoutInflater().inflate(R.layout.tab_layout,null);
        ImageView settIcon = (ImageView) settView.findViewById(R.id.imageView);
        TextView settLabel = (TextView) settView.findViewById(R.id.textView);
        settIcon.setImageResource(R.drawable.ic_settings_black_24dp);
        settLabel.setText("Settings");

        list.setCustomView(listView);
        favorites.setCustomView(favView);
        spot.setCustomView(spotView);
        settings.setCustomView(settView);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));

        tabLayout.addTab(list,0);
        tabLayout.addTab(favorites,1);
        tabLayout.addTab(spot,2);
        tabLayout.addTab(settings,3);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                ImageView tabImage = (ImageView) tab.getCustomView().findViewById(R.id.imageView);
                switch(tab.getPosition()){
                    case 0:
                        tabImage.setImageDrawable(getIcon(tab.getPosition() + 1));
                        break;
                    case 1:
                        tabImage.setImageDrawable(getIcon(tab.getPosition() + 2));
                        break;
                    case 2:
                        tabImage.setImageDrawable(getIcon(tab.getPosition() + 3));
                        break;
                    case 3:
                        tabImage.setImageDrawable(getIcon(tab.getPosition() + 4));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                ImageView tabImage = (ImageView) tab.getCustomView().findViewById(R.id.imageView);
                switch(tab.getPosition()){
                    case 0:
                        tabImage.setImageDrawable(getIcon(tab.getPosition()));
                        break;
                    case 1:
                        tabImage.setImageDrawable(getIcon(tab.getPosition() + 1));
                        break;
                    case 2:
                        tabImage.setImageDrawable(getIcon(tab.getPosition() + 2));
                        break;
                    case 3:
                        tabImage.setImageDrawable(getIcon(tab.getPosition() + 3));
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void initIconList() {
        tabIcons = new ArrayList<>();

        tabIcons.add(getDrawable(R.drawable.ic_list_black_24dp));
        tabIcons.add(getDrawable(R.drawable.ic_list_white_24dp));
        tabIcons.add(getDrawable(R.drawable.ic_grade_black_24dp));
        tabIcons.add(getDrawable(R.drawable.ic_grade_white_24dp));
        tabIcons.add(getDrawable(R.drawable.ic_place_black_24dp));
        tabIcons.add(getDrawable(R.drawable.ic_place_white_24dp));
        tabIcons.add(getDrawable(R.drawable.ic_settings_black_24dp));
        tabIcons.add(getDrawable(R.drawable.ic_settings_white_24dp));
    }

    private Drawable getIcon(int index){
        return tabIcons.get(index);
    }
}

