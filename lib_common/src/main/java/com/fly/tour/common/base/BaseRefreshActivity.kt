package com.fly.tour.common.base

import com.fly.tour.common.mvp.BaseModel
import com.fly.tour.common.mvp.BaseRefreshPresenter
import com.fly.tour.common.mvp.BaseRefreshView
import com.refresh.lib.BaseRefreshLayout
import com.refresh.lib.DaisyRefreshLayout

/**
 * Description: <下拉刷新></下拉刷新>、上拉加载更多的Activity><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/2/26<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
 */
abstract class BaseRefreshActivity<M : BaseModel, V : BaseRefreshView<T>, P : BaseRefreshPresenter<M, V, T>, T> : BaseMvpActivity<M, V, P>(), BaseRefreshView<T> {
    protected lateinit var mRefreshLayout: DaisyRefreshLayout

    override fun initCommonView() {
        super.initCommonView()
        initRefreshView()
    }

    fun initRefreshView() {
        mRefreshLayout = findViewById(onBindRreshLayout())
        // 下拉刷新
        mRefreshLayout.setOnRefreshListener(object : BaseRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                onRefreshEvent()
            }
        })
        // 上拉加载
        mRefreshLayout.setOnLoadMoreListener(object : BaseRefreshLayout.OnLoadMoreListener {
            override fun onLoadMore() {
                onLoadMoreEvent()
            }
        })
        // 自动加载
        mRefreshLayout.setOnAutoLoadListener(object : BaseRefreshLayout.OnAutoLoadListener {
            override fun onAutoLoad() {
                onAutoLoadEvent()
            }
        })
    }

    protected abstract fun onBindRreshLayout(): Int

    override fun enableRefresh(b: Boolean) {
        mRefreshLayout.setEnableRefresh(b)
    }

    override fun enableLoadMore(b: Boolean) {
        mRefreshLayout.setEnableLoadMore(b)
    }

    override fun stopRefresh() {
        mRefreshLayout.isRefreshing = false
    }

    override fun stopLoadMore() {
        mRefreshLayout.setLoadMore(false)
    }

    override fun autoLoadData() {
        mRefreshLayout.autoRefresh()
    }
}
