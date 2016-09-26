package com.unimi.mobidev.onderoad.other;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unimi.mobidev.onderoad.R;

/**
 * Created by Giuseppe on 26/09/2016.
 */

public class PriceSelector extends LinearLayout {
    private View rootView;
    private View plusButton;
    private View minusButton;
    private TextView priceTextView;

    private int minValue = 0;
    private int maxValue = 50;

    private boolean plusButtonIsPressed = false;
    private boolean minusButtonIsPressed = false;
    private final long REPEAT_INTERVAL_MS = 100l;

    private Handler handler = new Handler();

    public PriceSelector(Context context){
        super(context);
        init(context);
    }

    public PriceSelector(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context){
        rootView = inflate(context, R.layout.plus_minus_button,this);

        priceTextView = (TextView) findViewById(R.id.priceTextView);
        priceTextView.setText("10");
        plusButton = rootView.findViewById(R.id.plusButton);
        minusButton = rootView.findViewById(R.id.minusButton);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementValue();
            }
        });
        minusButton.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View arg0) {
                        minusButtonIsPressed = true;
                        handler.post(new AutoDecrementer());
                        return false;
                    }
                }
        );
        minusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)) {
                    minusButtonIsPressed = false;
                }
                return false;
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementValue();
            }
        });
        plusButton.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View arg0) {
                        plusButtonIsPressed = true;
                        handler.post(new AutoIncrementer());
                        return false;
                    }
                }
        );

        plusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)) {
                    plusButtonIsPressed = false;
                }
                return false;
            }
        });
    }

    public int getMinValue(){
        return this.minValue;
    }

    public void setMinValue(int value){
        this.minValue = value;
    }

    public int getMaxValue(){
        return this.maxValue;
    }

    public void setMaxValue(int value){
        this.maxValue = value;
    }

    public int getValue(){
        return Integer.valueOf(priceTextView.getText().toString());
    }

    public void setValue(int value){
        if(value < minValue)
            priceTextView.setText(String.valueOf(minValue));
        else if (value > maxValue)
            priceTextView.setText(String.valueOf(maxValue));
            else
                priceTextView.setText(String.valueOf(value));
    }

    private void decrementValue(){
        int currentValue = this.getValue();

        this.setValue(currentValue - 1);
    }

    private void incrementValue(){
        int currentValue = this.getValue();

        this.setValue(currentValue + 1);
    }

    private class AutoIncrementer implements Runnable {
        @Override
        public void run() {
            if(plusButtonIsPressed){
                incrementValue();
                handler.postDelayed( new AutoIncrementer(), REPEAT_INTERVAL_MS);
            }
        }
    }
    private class AutoDecrementer implements Runnable {
        @Override
        public void run() {
            if(minusButtonIsPressed){
                decrementValue();
                handler.postDelayed(new AutoDecrementer(), REPEAT_INTERVAL_MS);
            }
        }
    }
}
