package com.fly.tour.common.mvp

/**
 * Description: <BaseRefreshView><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/2/26<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</BaseRefreshView> */
interface BaseRefreshView<T> : BaseRefreshContract.View {
    //刷新数据
    fun refreshData(data: List<T>)

    //加载更多
    fun loadMoreData(data: List<T>)
}
