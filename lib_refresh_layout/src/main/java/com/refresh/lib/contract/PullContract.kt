package com.refresh.lib.contract

/**
 * Description: <下拉刷新的协议><br></br>
 * Author:      mxdl<br></br>
 * Date:        2019/2/25<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</下拉刷新的协议> */
interface PullContract {
    /**
     * 手指上滑下滑的回调
     * @param enable
     */
    fun onPullEnable(enable: Boolean)

    /**
     * 手指松开的回调
     */
    fun onRefresh()

}
