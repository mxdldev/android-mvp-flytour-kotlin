package com.fly.tour.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.fly.tour.common.R
import com.fly.tour.common.event.common.BaseFragmentEvent
import com.fly.tour.common.mvp.BaseView
import com.fly.tour.common.util.NetUtil
import com.fly.tour.common.util.log.KLog
import com.fly.tour.common.view.LoadingInitView
import com.fly.tour.common.view.LoadingTransView
import com.fly.tour.common.view.NetErrorView
import com.fly.tour.common.view.NoDataView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Description: <BaseFragment><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/1/15<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</BaseFragment> */
abstract class BaseFragment : androidx.fragment.app.Fragment(), BaseView {
    protected lateinit var mActivity: RxAppCompatActivity
    protected lateinit var mView: View
    protected var mTxtTitle: TextView? = null
    protected var mToolbar: Toolbar? = null

    protected var mNetErrorView: NetErrorView? = null
    protected var mNoDataView: NoDataView? = null
    protected var mLoadingInitView: LoadingInitView? = null
    protected var mLoadingTransView: LoadingTransView? = null

    private var mViewStubToolbar: ViewStub? = null
    private var mViewStubContent: ViewStub? = null
    private var mViewStubInitLoading: ViewStub? = null
    private var mViewStubTransLoading: ViewStub? = null
    private var mViewStubNoData: ViewStub? = null
    private var mViewStubError: ViewStub? = null
    private var isViewCreated = false
    private var isViewVisable = false

    abstract val toolbarTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = (activity as RxAppCompatActivity?)!!
        ARouter.getInstance().inject(this)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_root, container, false)
        initCommonView(mView)
        initView(mView)
        initListener()
        return mView
    }

    open fun initCommonView(view: View) {
        mViewStubToolbar = view.findViewById(R.id.view_stub_toolbar)
        mViewStubContent = view.findViewById(R.id.view_stub_content)
        mViewStubContent = view.findViewById(R.id.view_stub_content)
        mViewStubInitLoading = view.findViewById(R.id.view_stub_init_loading)
        mViewStubTransLoading = view.findViewById(R.id.view_stub_trans_loading)
        mViewStubNoData = view.findViewById(R.id.view_stub_nodata)
        mViewStubError = view.findViewById(R.id.view_stub_error)

        if (enableToolbar()) {
            mViewStubToolbar!!.layoutResource = onBindToolbarLayout()
            val viewTooBbar = mViewStubToolbar!!.inflate()
            initTooBar(viewTooBbar)
        }
        mViewStubContent!!.layoutResource = onBindLayout()
        mViewStubContent!!.inflate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        //如果启用了懒加载就进行懒加载，否则就进行预加载
        if (enableLazyData()) {
            lazyLoad()
        } else {
            initData()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isViewVisable = isVisibleToUser
        //如果启用了懒加载就进行懒加载，
        if (enableLazyData() && isViewVisable) {
            lazyLoad()
        }
    }

    private fun lazyLoad() {
        //这里进行双重标记判断,必须确保onCreateView加载完毕且页面可见,才加载数据
        KLog.v("MYTAG", "lazyLoad start...")
        KLog.v("MYTAG", "isViewCreated:$isViewCreated")
        KLog.v("MYTAG", "isViewVisable$isViewVisable")
        if (isViewCreated && isViewVisable) {
            initData()
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false
            isViewVisable = false
        }
    }

    //默认不启用懒加载
    open fun enableLazyData(): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    protected fun initTooBar(view: View) {
        mToolbar = view.findViewById(R.id.toolbar_root)
        mTxtTitle = view.findViewById(R.id.toolbar_title)
        if (mToolbar != null) {
            mActivity.setSupportActionBar(mToolbar)
            mActivity.supportActionBar!!.setDisplayShowTitleEnabled(false)
            mToolbar!!.setNavigationOnClickListener { mActivity.onBackPressed() }
        }
        if (mTxtTitle != null) {
            mTxtTitle!!.text = toolbarTitle
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun <T> onEvent(event: BaseFragmentEvent<T>) {
    }

    abstract fun onBindLayout(): Int

    abstract fun initView(view: View)

    override fun initView() {}

    abstract override fun initData()

    override fun initListener() {}

    override fun finishActivity() {
        mActivity.finish()
    }

    fun enableToolbar(): Boolean {
        return false
    }

    fun onBindToolbarLayout(): Int {
        return R.layout.common_toolbar
    }

    override fun showInitLoadView() {
        showInitLoadView(true)
    }

    override fun hideInitLoadView() {
        showInitLoadView(false)
    }

    override fun showTransLoadingView() {
        showTransLoadingView(true)
    }

    override fun hideTransLoadingView() {
        showTransLoadingView(false)
    }

    override fun showNoDataView() {
        showNoDataView(true)
    }

    override fun showNoDataView(resid: Int) {
        showNoDataView(true, resid)
    }

    override fun hideNoDataView() {
        showNoDataView(false)
    }

    override fun showNetWorkErrView() {
        showNetWorkErrView(true)
    }

    override fun hideNetWorkErrView() {
        showNetWorkErrView(false)
    }

    private fun showInitLoadView(show: Boolean) {
        if (mLoadingInitView == null) {
            val view = mViewStubInitLoading!!.inflate()
            mLoadingInitView = view.findViewById(R.id.view_init_loading)
        }
        mLoadingInitView!!.visibility = if (show) View.VISIBLE else View.GONE
        mLoadingInitView!!.loading(show)
    }


    private fun showNetWorkErrView(show: Boolean) {
        if (mNetErrorView == null) {
            val view = mViewStubError!!.inflate()
            mNetErrorView = view.findViewById(R.id.view_net_error)
            mNetErrorView!!.setOnClickListener(View.OnClickListener {
                if (!NetUtil.checkNetToast()) {
                    return@OnClickListener
                }
                hideNetWorkErrView()
                initData()
            })
        }
        mNetErrorView!!.visibility = if (show) View.VISIBLE else View.GONE
    }


    private fun showNoDataView(show: Boolean) {
        if (mNoDataView == null) {
            val view = mViewStubNoData!!.inflate()
            mNoDataView = view.findViewById(R.id.view_no_data)
        }
        mNoDataView!!.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showNoDataView(show: Boolean, resid: Int) {
        showNoDataView(show)
        if (show) {
            mNoDataView!!.setNoDataView(resid)
        }
    }

    private fun showTransLoadingView(show: Boolean) {
        if (mLoadingTransView == null) {
            val view = mViewStubTransLoading!!.inflate()
            mLoadingTransView = view.findViewById(R.id.view_trans_loading)
        }
        mLoadingTransView!!.visibility = if (show) View.VISIBLE else View.GONE
        mLoadingTransView!!.loading(show)
    }

    companion object {
        protected val TAG = BaseFragment::class.java!!.getSimpleName()
    }

}
