package com.unimi.mobidev.onderoad.other;

import android.content.Context;
import android.util.AttributeSet;
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

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementValue();
            }
        });
    }

    private void decrementValue(){
        int currentValue = Integer.valueOf(priceTextView.getText().toString());

        if (currentValue > minValue)
            priceTextView.setText(String.valueOf(currentValue - 1));
    }

    private void incrementValue(){
        int currentValue = Integer.valueOf(priceTextView.getText().toString());

        if (currentValue < maxValue)
            priceTextView.setText(String.valueOf(currentValue + 1));
    }
}
