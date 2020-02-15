package com.fly.tour.common.event

/**
 * Description: <BaseEvent><br></br>
 * Author:      mxdl<br></br>
 * Date:        2018/4/4<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</BaseEvent> */
open class BaseEvent<T> {
    var code: Int = 0
    var data: T? = null

    constructor(code: Int) {
        this.code = code
    }

    constructor(code: Int, data: T) {
        this.code = code
        this.data = data
    }
}
