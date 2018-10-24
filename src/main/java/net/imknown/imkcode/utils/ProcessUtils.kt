package net.imknown.imkcode.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager.GET_SERVICES
import android.os.Process

object ProcessUtils {
    // @JvmField
    // val TAG: String = /* ProcessUtils. */ javaClass.simpleName

    @JvmStatic
    fun isDefaultProcess(context: Context): Boolean {
        // val packageName = context.packageName
        val processName = getCurrentProcessName(context)
        val defaultProcessName =
            context.packageManager.getPackageInfo(context.packageName, GET_SERVICES).applicationInfo.processName

        return processName == defaultProcessName
    }

    @JvmStatic
    fun getCurrentProcessName(context: Context): String {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        activityManager.runningAppProcesses.forEach {
            if (Process.myPid() == it.pid) {
                return it.processName
            }
        }

        return ""
    }
}
