package com.fly.tour.common.mvp

/**
 * Description: <ILoadView><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/2/26<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</ILoadView> */
interface ILoadView {
    //显示初始加载的View，初始进来加载数据需要显示的View
    fun showInitLoadView()

    //隐藏初始加载的View
    fun hideInitLoadView()
}
