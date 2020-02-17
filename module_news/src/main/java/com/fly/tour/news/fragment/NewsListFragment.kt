package com.fly.tour.news.fragment

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fly.tour.common.base.BaseAdapter
import com.fly.tour.common.base.BaseAdapter.OnItemClickListener
import com.fly.tour.common.base.BaseFragment
import com.fly.tour.common.base.BaseRefreshFragment
import com.fly.tour.common.event.KeyCode
import com.fly.tour.common.util.ToastUtil
import com.fly.tour.db.entity.NewsDetail
import com.fly.tour.db.entity.NewsType
import com.fly.tour.news.NewsDetailActivity
import com.fly.tour.news.R
import com.fly.tour.news.adapter.NewsListAdapter
import com.fly.tour.news.contract.NewsListContract
import com.fly.tour.news.model.NewsListModel
import com.fly.tour.news.presenter.NewsListPresenter

/**
 * Description: <NewsListFragment><br>
 * Author:      mxdl<br>
 * Date:        2020/2/16<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
/**
 * Description: <NewsListFragment><br>
 * Author:      mxdl<br>
 * Date:        2020/2/16<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
class NewsListFragment :
    BaseRefreshFragment<NewsListModel, NewsListContract.View<NewsDetail>, NewsListPresenter, NewsDetail>(),
    NewsListContract.View<NewsDetail> {
    private var mNewsType: NewsType? = null
    private var mNewsListAdapter: NewsListAdapter? = null
    private var mRecViewNewsDetail: RecyclerView? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mNewsType = arguments?.getParcelable(KeyCode.News.NEWS_TYPE)
    }

    override fun onBindLayout(): Int {
        return R.layout.fragment_news_list
    }

    override fun initView(view: View) {
        mRecViewNewsDetail = view.findViewById(R.id.recview_news_list)
        mRecViewNewsDetail?.layoutManager = LinearLayoutManager(context)
        mNewsListAdapter = NewsListAdapter(context!!)
        mRecViewNewsDetail?.adapter = mNewsListAdapter
    }

    override fun enableLazyData(): Boolean {
        return true
    }

    override fun initData() {
        mPresenter?.newsType = mNewsType!!.id
        autoLoadData()
    }

    override fun initListener() {
        mNewsListAdapter?.setItemClickListener(object : OnItemClickListener<NewsDetail> {
            override fun onItemClick(e: NewsDetail, position: Int) {
                NewsDetailActivity.startNewsDetailActivity(mActivity,e.id)
            }
        })
    }

    override fun onBindRreshLayout(): Int {
        return R.id.refview_news_list
    }

    override fun initPresenter(): NewsListPresenter {
        return NewsListPresenter(mActivity)
    }

    override fun refreshData(data: List<NewsDetail>) {
        mNewsListAdapter?.refresh(data)
    }

    override fun loadMoreData(data: List<NewsDetail>) {
        mNewsListAdapter?.addAll(data)
    }

    override fun onRefreshEvent() {
        mPresenter?.refreshData()
    }

    override fun onLoadMoreEvent() {
        mPresenter?.loadMoreData()
    }

    override fun onAutoLoadEvent() {
        mPresenter?.refreshData()
    }

    override val toolbarTitle: String
        get() = ""

    companion object {
        fun newInstance(newsType: NewsType): NewsListFragment {
            var newsListFragment = NewsListFragment()
            var bundle = Bundle()
            bundle.putParcelable(KeyCode.News.NEWS_TYPE, newsType)
            newsListFragment.arguments = bundle
            return newsListFragment
        }
    }

}