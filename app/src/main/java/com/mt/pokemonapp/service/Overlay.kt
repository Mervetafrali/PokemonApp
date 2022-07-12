package com.mt.pokemonapp.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import com.mt.pokemonapp.R


class Overlay:Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val mParams: WindowManager.LayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSPARENT)

        mParams.gravity= Gravity.CENTER

        val testView = LayoutInflater.from(applicationContext).inflate(R.layout.overlay, null)
        val wm = applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.addView(testView, mParams)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


}