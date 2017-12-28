package com.sun.commonutils

/**
 * Created by Administrator on 2017/12/28.
 */

class ImageUtils
    /*
    * android 调用应用图片资源时，会优先选择当前手机屏幕dpi对应的的文件夹（如drawable-ldpi, drawable-mdpi, drawable-hdpi, drawable-xhdpi, drawable-xxhdpi等）。
    *
    * 如果对应dpi文件夹下没有需要的资源，则在最近的高dpi文件夹下进行查找，如当前dpi是hdpi，而drawable-hdpi下面没有找到需要的资源，则首先在drawable-xhdpi中查找，如果没有，则继续在drawable-xxhdpi文件夹下进行查找。
    *
    * 依此类推，如果在高dpi的文件夹下也找不到，则会到最近的低dpi文件夹下进行查找，先在drawable-mdpi下进行查找，如果没找到，则继续在drawable-ldpi下进行查找。
    *
    * 没有特别的需要，在支持最高dpi文件夹下存放一套资源，如果在所支持的所有API level运行正常，则只需要维护这一套资源即可，无须为应用的多个dpi生成多套资源文件。
    * */
