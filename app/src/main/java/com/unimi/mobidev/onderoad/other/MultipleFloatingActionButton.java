package com.unimi.mobidev.onderoad.other;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.unimi.mobidev.onderoad.R;

/**
 * Created by Giuseppe on 19/12/2016.
 */

public class MultipleFloatingActionButton extends LinearLayout{
    private View rootview;

    private FloatingActionButton modifyFab;
    private FloatingActionButton mapsFab;
    private FloatingActionButton deleteFab;
    private FloatingActionButton mailFab;
    private FloatingActionButton shareFab;
    private FloatingActionButton mainFab;

    private boolean isFabOpen;

    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    public MultipleFloatingActionButton(Context context) {
        super(context);
    }

    public MultipleFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultipleFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context){
        rootview = inflate(context, R.layout.multiple_fab, this);

        mainFab = (FloatingActionButton) rootview.findViewById(R.id.actualTravelSetting);
        modifyFab = (FloatingActionButton) rootview.findViewById(R.id.modifyFab);
        mapsFab = (FloatingActionButton) rootview.findViewById(R.id.mapsFab);
        deleteFab = (FloatingActionButton) rootview.findViewById(R.id.deleteFab);
        shareFab = (FloatingActionButton) rootview.findViewById(R.id.shareFab);
        mailFab = (FloatingActionButton) rootview.findViewById(R.id.mailFab);

        fab_open = AnimationUtils.loadAnimation(context,R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(context, R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(context, R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(context,R.anim.rotate_forward);

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFAB();
            }
        });

        modifyFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Modify Floating Action Button!");
            }
        });

        mapsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Maps Floating Action Button!");
            }
        });

        deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Delete Floating Action Button!");
            }
        });

        shareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Share Floating Action Button!");
            }
        });

        mailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Mail Floating Action Button!");
            }
        });
    }

    public void animateFAB(){

        if(isFabOpen){

            mainFab.startAnimation(rotate_backward);
            modifyFab.startAnimation(fab_close);
            mapsFab.startAnimation(fab_close);
            deleteFab.startAnimation(fab_close);
            mailFab.startAnimation(fab_close);
            shareFab.startAnimation(fab_close);
            modifyFab.setClickable(false);
            mapsFab.setClickable(false);
            deleteFab.setClickable(false);
            mailFab.setClickable(false);
            shareFab.setClickable(false);

            isFabOpen = false;

        } else {

            mainFab.startAnimation(rotate_forward);
            modifyFab.startAnimation(fab_open);
            mapsFab.startAnimation(fab_open);
            deleteFab.startAnimation(fab_open);
            mailFab.startAnimation(fab_open);
            shareFab.startAnimation(fab_open);
            modifyFab.setClickable(true);
            mapsFab.setClickable(true);
            deleteFab.setClickable(true);
            mailFab.setClickable(true);
            shareFab.setClickable(true);

            isFabOpen = true;

        }
    }
}
