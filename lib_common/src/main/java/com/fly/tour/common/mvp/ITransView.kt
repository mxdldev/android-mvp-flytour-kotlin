package com.fly.tour.common.mvp

/**
 * Description: <ITransView><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/2/26<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</ITransView> */
interface ITransView {
    //显示背景透明小菊花View,例如删除操作
    fun showTransLoadingView()

    //隐藏背景透明小菊花View
    fun hideTransLoadingView()
}
