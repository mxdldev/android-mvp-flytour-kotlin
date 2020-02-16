package com.fly.tour.me.fragment

import android.view.View
import com.fly.tour.common.base.BaseFragment
import com.fly.tour.me.R

/**
 * Description: <MainMeFragment><br>
 * Author:      mxdl<br>
 * Date:        2020/2/16<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
class MainMeFragment : BaseFragment() {
    override val toolbarTitle: String
        get() = ""

    override fun onBindLayout(): Int {
        return R.layout.fragment_me_main
    }

    override fun initView(view: View) {
    }

    override fun initData() {
    }

    companion object {
        fun newInstance(): MainMeFragment {
            return MainMeFragment()
        }
    }
}