package com.fly.tour.test.view.java;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Description: <MyView><br>
 * Author:      mxdl<br>
 * Date:        2020/2/20<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MyView1 extends View {
    public MyView1(Context context) {
        this(context,null);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
