package com.fly.tour.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.fly.tour.common.util.ToastUtil
import com.fly.tour.test.view.MyView2
import com.fly.tour.test.view.Person1
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    val TAG = "MYTAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //testOnClick()
        //testThread()
        //test1
        Log.e(TAG, "主线程id：${mainLooper.thread.id}")
        val job = GlobalScope.launch {
            delay(6000)
            Log.e(TAG, "协程执行结束 -- 线程id：${Thread.currentThread().id}")
        }
        Log.e(TAG, "主线程执行结束")
    }
    private fun testThread() = runBlocking {
        repeat(8) {
            Log.e("TAG", "协程执行$it 线程id：${Thread.currentThread().id}")
            delay(1000)
        }
    }
    val test1 = runBlocking {
        repeat(8) {
            Log.e("TAG", "协程执行$it 线程id：${Thread.currentThread().id}")
            delay(1000)
        }
    }

    private fun testOnClick() {
        btn.setOnClickListener { v -> ToastUtil.showToast("ok") }
        btn.setOnClickListener() { v -> ToastUtil.showToast("ok") }
        btn.setOnClickListener({ v -> ToastUtil.showToast("ok") })
        btn.setOnClickListener { }
        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

}
