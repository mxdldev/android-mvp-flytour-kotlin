package com.refresh.lib

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.refresh.lib.contract.PullContract

/**
 * Description: <DaisyHeaderView><br></br>
 * Author: mxdl<br></br>
 * Date: 2019/2/25<br></br>
 * Version: V1.0.0<br></br>
 * Update: <br></br>
</DaisyHeaderView> */
class DaisyHeaderView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs), PullContract {

    private var mTxtLoading: TextView? = null
    private var mImgDaisy: ImageView? = null
    private var mRotation: ObjectAnimator? = null

    init {
        initView(context)
    }

    @SuppressLint("ObjectAnimatorBinding")
    fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.layout_daisy, this)
        mTxtLoading = findViewById(R.id.txt_loading)
        mTxtLoading!!.text = "下拉刷新"
        mImgDaisy = findViewById(R.id.img_daisy)
        mRotation = ObjectAnimator.ofFloat(mImgDaisy, "rotation", 0f, 360f).setDuration(800)
        mRotation!!.repeatCount = ValueAnimator.INFINITE
        mRotation!!.interpolator = LinearInterpolator()

    }

    override fun onPullEnable(enable: Boolean) {
        mTxtLoading!!.text = if (enable) "松开刷新" else "下拉刷新"
    }

    override fun onRefresh() {
        mTxtLoading!!.text = "正在刷新"
        mRotation!!.start()
    }

    fun setRefreshing(b: Boolean) {
        if (b) {
            mRotation!!.start()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mRotation!!.pause()
            }
        }
    }
}
