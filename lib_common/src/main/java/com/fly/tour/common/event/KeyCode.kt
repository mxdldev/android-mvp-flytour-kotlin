package com.fly.tour.common.event

/**
 * Description: <RequestCode><br></br>
 * Author:      mxdl<br></br>
 * Date:        2019/5/27<br></br>
 * Version:     V1.0.0<br></br>
 * Update:     <br></br>
</RequestCode> */
interface KeyCode {
    interface Main

    interface News {
        companion object {
            val NEWS_TYPE = "newstype"
            val NEWS_ID = "newsid"
        }
    }

    interface Find

    interface Me
}
