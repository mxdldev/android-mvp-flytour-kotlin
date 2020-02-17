package com.fly.tour.common.mvp

/**
 * Description: <BaseView><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/3/25<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</BaseView> */
interface BaseView : ILoadView, INoDataView, ITransView, INetErrView {
    fun initListener()
    fun initData()
    fun finishActivity()
}
