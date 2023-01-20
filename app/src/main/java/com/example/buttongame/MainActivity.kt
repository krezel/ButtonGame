package com.example.buttongame

import android.graphics.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.sql.Time
import kotlin.time.Duration.Companion.seconds

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart: Button = findViewById(R.id.btn_start)
        val btn1: ImageButton = findViewById(R.id.btn_1)
        val btn2 = findViewById<ImageButton>(R.id.btn_2)
        val btn3 = findViewById<ImageButton>(R.id.btn_3)
        val btn4 = findViewById<ImageButton>(R.id.btn_4)
        val btn5 = findViewById<ImageButton>(R.id.btn_5)
        val btn6 = findViewById<ImageButton>(R.id.btn_6)
        val btn7 = findViewById<ImageButton>(R.id.btn_7)
        val btn8 = findViewById<ImageButton>(R.id.btn_8)
        val btn9 = findViewById<ImageButton>(R.id.btn_9)
        val timer = findViewById<Chronometer>(R.id.timer)
        val tvPoints = findViewById<TextView>(R.id.tv_points)
        val btnList = listOf(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9)
        btnStart.setOnClickListener {
            ClickedButton().btnSwitch(btnList.random())
            setTimer(timer)
        }
    }
    class ClickedButton(){
        private var isPoint = true
        @RequiresApi(Build.VERSION_CODES.Q)
        fun btnSwitch(btn: ImageButton): Boolean {
            btn.background.colorFilter = BlendModeColorFilter(Color.parseColor("#fc0328"),BlendMode.COLOR)
            isPoint = true
            btn.background.clearColorFilter()
            return isPoint
        }
    }
    private fun setTimer(clock:Chronometer){
        clock.base = SystemClock.elapsedRealtime()+30000
        clock.start()
    }
}