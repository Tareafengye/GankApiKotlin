package com.ys.gankapikotlin.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ys.gankapikotlin.R;


/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/22
 * Descriptions: LoweLinearLayout
 */
public class LoweLinearLayout extends LinearLayout {

    private float mRatio;

    public LoweLinearLayout(Context context) {
        this(context, null);
    }

    public LoweLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoweLinearLayout(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    public void setmRatio(float ratio){
        this.mRatio=ratio;
    }
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.LoweImageView);
        mRatio = typedArray.getFloat(R.styleable.LoweImageView_ratio, 0);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize;
        if (widthMode == MeasureSpec.EXACTLY && mRatio > 0) {
            heightSize = (int) (widthSize / mRatio + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,
                    MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
