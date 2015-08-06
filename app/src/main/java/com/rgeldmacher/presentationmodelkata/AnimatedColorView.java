package com.rgeldmacher.presentationmodelkata;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class AnimatedColorView extends View {

    private int backgroundColor = R.color.transparent;

    public AnimatedColorView(Context context) {
        super(context);
    }

    public AnimatedColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatedColorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setBackgroundColor(final int color) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), backgroundColor, color);
        colorAnimation.setDuration(1000);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                AnimatedColorView.super.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        backgroundColor = color;
    }
}
