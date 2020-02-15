package com.refresh.lib

import android.content.Context
import android.util.AttributeSet

/**
 * Description: <小菊花样式的刷新控件><br></br>
 * Author:      mxdl<br></br>
 * Date:        2019/2/25<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</小菊花样式的刷新控件> */
class DaisyRefreshLayout(context: Context, attrs: AttributeSet) :
    BaseRefreshLayout(context, attrs) {
    private val mDaisyHeaderView: DaisyHeaderView?
    private val mDaisyFooterView: DaisyFooterView
    override var isRefreshing: Boolean
        get() = super.isRefreshing
        set(refreshing) {
            mDaisyHeaderView!!.setRefreshing(refreshing)
            super.isRefreshing = refreshing
        }

    init {
        mDaisyHeaderView = DaisyHeaderView(context)
        mDaisyFooterView = DaisyFooterView(context)
        setHeaderView(mDaisyHeaderView)
        setFooterView(mDaisyFooterView)
        setOnPullRefreshListener(object : SuperSwipeRefreshLayout.OnPullRefreshListener {
            override fun onRefresh() {
                mDaisyHeaderView.onRefresh()
                mOnRefreshListener.onRefresh()
            }

            override fun onPullDistance(distance: Int) {

            }

            override fun onPullEnable(enable: Boolean) {
                mDaisyHeaderView.onPullEnable(enable)
            }
        })
        setOnPushLoadMoreListener(object : SuperSwipeRefreshLayout.OnPushLoadMoreListener {
            override fun onLoadMore() {
                mDaisyFooterView.onLoadMore()
                mOnLoadMoreListener.onLoadMore()
            }

            override fun onPushDistance(distance: Int) {

            }

            override fun onPushEnable(enable: Boolean) {
                mDaisyFooterView.onPushEnable(enable)
            }
        })
    }

    override fun showRefresh() {
        mDaisyHeaderView?.onRefresh()
    }

    override fun setLoadMore(loadMore: Boolean) {
        mDaisyFooterView.setLoadMore(loadMore)
        super.setLoadMore(loadMore)
    }
}
