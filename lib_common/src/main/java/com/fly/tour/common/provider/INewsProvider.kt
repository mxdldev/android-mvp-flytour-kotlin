package com.fly.tour.common.provider

import androidx.fragment.app.Fragment

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Description: <INewsProvider><br></br>
 * Author:      mxdl<br></br>
 * Date:        2019/5/23<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</INewsProvider> */
interface INewsProvider : IProvider {
    val mainNewsFragment: Fragment
}
