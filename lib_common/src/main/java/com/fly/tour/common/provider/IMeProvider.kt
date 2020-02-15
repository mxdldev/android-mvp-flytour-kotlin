package com.fly.tour.common.provider

import androidx.fragment.app.Fragment

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Description: <IMeProvider><br></br>
 * Author:      mxdl<br></br>
 * Date:        2019/5/23<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</IMeProvider> */
interface IMeProvider : IProvider {
    val mainMeFragment: Fragment
}
