package com.fly.tour.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fly.tour.common.util.ToastUtil
import com.fly.tour.test.view.MyView2
import com.fly.tour.test.view.Person1
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener{ v -> ToastUtil.showToast("ok") }
        btn.setOnClickListener(){ v -> ToastUtil.showToast("ok") }
        btn.setOnClickListener({ v -> ToastUtil.showToast("ok") })
        btn.setOnClickListener { }
        btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}
