package com.fly.tour.common.base

import android.os.Bundle

import com.fly.tour.common.mvp.BaseModel
import com.fly.tour.common.mvp.BasePresenter

/**
 * Description: <BaseMvpFragment><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/1/15<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</BaseMvpFragment> */
abstract class BaseMvpFragment<M : BaseModel, V, P : BasePresenter<M, V>> : BaseFragment() {
    protected var mPresenter: P? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = initPresenter()
        if (mPresenter != null) {
            mPresenter!!.attach(this as V)
            mPresenter!!.injectLifecycle(mActivity)
        }
    }

    override fun onDestroy() {
        if (mPresenter != null) {
            mPresenter!!.detach()
        }
        super.onDestroy()
    }

    abstract fun initPresenter(): P
}
