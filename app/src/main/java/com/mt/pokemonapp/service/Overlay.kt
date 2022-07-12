package com.mt.pokemonapp.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.mt.pokemonapp.R


class Overlay : Service() {
    lateinit var fImage: ImageView
    lateinit var bImage: ImageView
    lateinit var text: TextView
    lateinit var backButton: Button
    lateinit var wm: WindowManager

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val mParams: WindowManager.LayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSPARENT
        )

        mParams.gravity = Gravity.CENTER
        val front = intent?.getStringExtra("front")
        val back = intent?.getStringExtra("back")
        val name = intent?.getStringExtra("name")
        val testView = LayoutInflater.from(applicationContext).inflate(R.layout.overlay, null)

        wm = applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (testView.parent != null) wm.removeViewImmediate(testView)
        wm.addView(testView, mParams)
        fImage = testView.findViewById(R.id.image_front)
        fImage.load(front) {
            crossfade(true)
            crossfade(1000)
        }
        bImage = testView.findViewById(R.id.image_back)
        bImage.load(back) {
            crossfade(true)
            crossfade(1000)
        }
        text = testView.findViewById(R.id.namePokemon)
        text.text = name

        backButton = testView.findViewById(R.id.backButton)
        backButton.setOnClickListener { mView ->
            wm.removeViewImmediate(testView)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


}