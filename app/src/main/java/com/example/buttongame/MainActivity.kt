package com.example.buttongame

import android.graphics.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Time
import kotlin.time.Duration.Companion.seconds

class MainActivity : AppCompatActivity() {
        object Point {
            var points = 0
        }
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
        val stopwatch = object : CountDownTimer(20000,1000){
            override fun onTick(remaining: Long) {
                ClickedButton().btnSwitch(btnList.random(), tvPoints)
                btnStart.isClickable = false
            }

            override fun onFinish() {
                timer.stop()
                btnStart.isClickable = true
            }
        }
        btnStart.setOnClickListener {
            setTimer(timer)
            stopwatch.start()
            Point.points = 0
            tvPoints.text = Point.points.toString()
        }
    }
    class ClickedButton {
        private var isPoint = true
        @RequiresApi(Build.VERSION_CODES.Q)
        fun btnSwitch(btn: ImageButton, tvpoints:TextView){
            btn.background.colorFilter = BlendModeColorFilter(Color.parseColor("#fc0328"),BlendMode.COLOR)
            isPoint = true
            GlobalScope.launch {
            delay(700L)
                btn.background.clearColorFilter()
                isPoint = false
            }
            GlobalScope.launch {
                btn.setOnClickListener {
                    if(isPoint)
                        Point.points++
                    tvpoints.text = Point.points.toString()
                }
            }
        }
    }
    private fun setTimer(clock:Chronometer){
            clock.base = SystemClock.elapsedRealtime() + 20000
            clock.start()
    }
}