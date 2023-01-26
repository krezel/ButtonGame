package com.example.buttongame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {
    private var speed = 500
    private var duration = 20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val btnSave = findViewById<Button>(R.id.btn_save)
        val sbDuration = findViewById<SeekBar>(R.id.SB_duration)
        val sbSpeed = findViewById<SeekBar>(R.id.SB_speed)
        val tvDuration = findViewById<TextView>(R.id.tv_duration)
        val tvSpeed = findViewById<TextView>(R.id.tv_speed)
        setSeek(sbDuration,tvDuration)
        setSeek(sbSpeed,tvSpeed)
        btnSave.setOnClickListener {
            Intent(this,MainActivity::class.java).also {
                it.putExtra("EXTRA_SPEED",speed)
                it.putExtra("EXTRA_DURATION",duration)
                startActivity(it)
            }
        }
    }
        private fun setSeek(seekBar:SeekBar,tv:TextView){
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ){
                    if(progress>=300){
                        tv.text = "Speed: ${progress.toString()}"
                        speed = progress
                    }
                    else{
                        tv.text = "Duration: ${progress.toString()}"
                        duration = progress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            })
        }
}