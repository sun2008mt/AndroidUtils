package com.sun.androidutils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.sun.commonutils.PhoneInfoUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, Array<String>(1) {Manifest.permission.READ_PHONE_STATE}, 1)

                return
            }
        }

        testReadPhoneInfo(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    testReadPhoneInfo(this)
                } else {
                    Toast.makeText(this, "请打开读取手机信息相关权限", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //测试读取手机信息
    fun testReadPhoneInfo(activity: AppCompatActivity) {
        Log.e("MainActivity", PhoneInfoUtils.getNativePhoneNumber(activity))
        Log.e("MainActivity", PhoneInfoUtils.getProviderName(activity))
        Log.e("MainActivity", PhoneInfoUtils.getPhoneInfo(activity))
    }
}
