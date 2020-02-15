package com.fly.tour.common.mvp

import androidx.annotation.DrawableRes

/**
 * Description: <INoDataView><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/3/11<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</INoDataView> */
interface INoDataView {
    //显示无数据View
    fun showNoDataView()

    //隐藏无数据View
    fun hideNoDataView()

    //显示指定资源的无数据View
    fun showNoDataView(@DrawableRes resid: Int)
}
