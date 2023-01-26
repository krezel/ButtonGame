package com.example.buttongame

import android.content.Intent
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MainActivity : AppCompatActivity() {
        object Point {
            var points = 0
        }
    var speed:Int = 500
    var duration:Int = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart: Button = findViewById(R.id.btn_save)
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
            Intent(this,SettingsActivity::class.java).also {
                speed = intent.getIntExtra("EXTRA_SPEED", 500)
                duration = intent.getIntExtra("EXTRA_DURATION", 20)
            }
            val stopwatch = object : CountDownTimer((duration *1000).toLong(),1000){
                override fun onTick(remaining: Long) {
                    ClickedButton().btnSwitch(btnList.random(), tvPoints,speed)
                    btnStart.isClickable = false
                }

                override fun onFinish() {
                    timer.stop()
                    btnStart.isClickable = true
                }
            }
            setTimer(timer)
            stopwatch.start()
            Point.points = 0
            tvPoints.text = Point.points.toString()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miSettings ->
                Intent(this,SettingsActivity::class.java).also {
                    startActivity(it)
                }
        }
        return true
    }
    class ClickedButton {
        private var isPoint = true
        fun btnSwitch(btn: ImageButton, tvpoints:TextView,speed:Int?){
            btn.background.colorFilter = BlendModeColorFilter(Color.parseColor("#fc0328"),BlendMode.COLOR)
            isPoint = true
            GlobalScope.launch {
                if (speed != null) {
                    delay(speed.toLong())
                }
                btn.background.clearColorFilter()
                isPoint = false
            }
                btn.setOnClickListener {
                    if(isPoint)
                        Point.points++
                    tvpoints.text = Point.points.toString()
                }
        }
    }
    private fun setTimer(clock:Chronometer){
            clock.base = SystemClock.elapsedRealtime() + duration*1000
            clock.start()
    }
}