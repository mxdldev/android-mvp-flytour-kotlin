package com.fly.tour.common.base

import android.os.Bundle

import com.fly.tour.common.mvp.BaseModel
import com.fly.tour.common.mvp.BasePresenter

/**
 * Description: <BaseMvpActivity><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/1/16<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</BaseMvpActivity> */
abstract class BaseMvpActivity<M : BaseModel, V, P : BasePresenter<M, V>> : BaseActivity() {
    protected var mPresenter: P? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = initPresenter()
        if (mPresenter != null) {
            mPresenter!!.attach(this as V)
            mPresenter!!.injectLifecycle(this)
        }
        super.onCreate(savedInstanceState)

    }

    abstract fun initPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detach()
        }
    }
}
