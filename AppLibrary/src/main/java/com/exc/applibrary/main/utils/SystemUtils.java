package com.exc.applibrary.main.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class SystemUtils {
    /**
     * 获取图片、应用名、包名
     */
    public String getAppPackage(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);

        PackageManager pManager = context.getPackageManager();
        List<PackageInfo> appList = getAllApps(context);
        String strPackagename = "";
        String strname = "";

        for (int i = 0; i < appList.size(); i++) {
            PackageInfo pinfo = appList.get(i);
            strPackagename = pinfo.applicationInfo.packageName;
            Drawable strIcon = pManager.getApplicationIcon(pinfo.applicationInfo);
            strname = pManager.getApplicationLabel(pinfo.applicationInfo).toString();
            if (strname.equals("天气通")) {
                break;
            }

        }

        return strname;

    }



    public static List<PackageInfo> getAllApps(Context context) {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = context.getPackageManager();
        //获取手机内所有应用
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            //判断是否为非系统预装的应用程序
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) > 0) {
                // customs applications
                apps.add(pak);
            }
        }
        return apps;
    }


}
