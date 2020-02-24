package com.fly.tour.test.lambda

/**
 * Description: <Lambda><br>
 * Author:      mxdl<br>
 * Date:        2020/2/24<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
fun main(args: Array<String>) {
    var result = add(1,2);
    println("result:$result")
}
//1.加法
fun  add(x:Int,y:Int): Int{
    return x + y
}