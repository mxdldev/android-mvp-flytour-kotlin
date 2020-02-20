package com.fly.tour.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fly.tour.test.view.MyView2
import com.fly.tour.test.view.Person1
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            //view.test()

        }
    }
}
