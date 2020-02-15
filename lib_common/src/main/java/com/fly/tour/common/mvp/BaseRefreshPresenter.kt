package com.fly.tour.common.mvp

import android.content.Context

/**
 * Description: <BaseRefreshPresenter><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/2/26<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</BaseRefreshPresenter> */
abstract class BaseRefreshPresenter<M : BaseModel, V : BaseRefreshView<T>, T>(context: Context) : BasePresenter<M, V>(context), BaseRefreshContract.Presenter
