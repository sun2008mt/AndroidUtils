package com.sun.commonutils

/**
 * Created by Administrator on 2017/12/28.
 */

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.telephony.TelephonyManager
import android.widget.Toast

/**
 * desc ：读取手机必要信息(需要READ_PHONE_STATE权限)
 *
 * author : Marc SUN
 * email : 710641245@qq.com
 * creation date: 2017/12/28 15:45
 */

//使用object关键字代替class关键字可以声明一个单例对象（不能声明构造函数）,lazy-init
object PhoneInfoUtils {

    // 获取电话号码
    /*
    * 手机号码不是所有的都能获取,只是有一部分可以拿到;
    * 这个是由于移动运营商没有把手机号码的数据写入到sim卡中;
    * SIM卡只有唯一的编号，供网络与设备识别那就是IMSI号码
    * */
    @SuppressLint("MissingPermission")
    fun getNativePhoneNumber(activity: AppCompatActivity): String? {
        if (hasPermissions(activity)) {
            val tm = activity.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm.line1Number
        } else {
            Toast.makeText(activity, "未获取相关权限", Toast.LENGTH_SHORT).show()
            return null
        }
    }


    // 获取手机服务商信息
    @SuppressLint("MissingPermission")
    fun getProviderName(activity: AppCompatActivity): String? {
        if (hasPermissions(activity)) {
            val tm = activity.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            var providerName = "N/A"

            /*
            *         获取国际移动用户识别码（IMSI: International Mobile SubscriberIdentification Number，存储在IMSI中）
            *         IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信
            * */
            val IMSI = tm.subscriberId

            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                providerName = "中国移动"
            } else if (IMSI.startsWith("46001")) {
                providerName = "中国移动"
            } else if (IMSI.startsWith("46003")) {
                providerName = "中国移动"
            }

            return providerName
        } else {
            Toast.makeText(activity, "未获取相关权限", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    //获取手机信息
    @SuppressLint("MissingPermission")
    fun getPhoneInfo(activity: AppCompatActivity): String? {
        if (hasPermissions(activity)) {
            //访问与手机通讯相关的信息的管理器
            val tm = activity.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val sb = StringBuilder()

            sb.append("\nDeviceId(IMEI) = " + tm.deviceId)                       // IMEI: International Mobile Equipment Identity,国际移动设备身份码
            sb.append("\nDeviceSoftwareVersion = " + tm.deviceSoftwareVersion)
            sb.append("\nLine1Number = " + tm.line1Number)
            sb.append("\nNetworkCountryIso = " + tm.networkCountryIso)
            sb.append("\nNetworkOperator = " + tm.networkOperator)              // 移动运营商编号
            sb.append("\nNetworkOperatorName = " + tm.networkOperatorName)      // 移动运营商名称
            sb.append("\nNetworkType = " + tm.networkType)
            sb.append("\nPhoneType = " + tm.phoneType)
            sb.append("\nSimCountryIso = " + tm.simCountryIso)
            sb.append("\nSimOperator = " + tm.simOperator)
            sb.append("\nSimOperatorName = " + tm.simOperatorName)
            sb.append("\nSimSerialNumber = " + tm.simSerialNumber)
            sb.append("\nSimState = " + tm.simState)
            sb.append("\nSubscriberId(IMSI) = " + tm.subscriberId)
            sb.append("\nVoiceMailNumber = " + tm.voiceMailNumber)

            return sb.toString()
        } else {
            Toast.makeText(activity, "未获取相关权限", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    private fun hasPermissions(activity: Activity): Boolean {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
    }
}
