package com.example.bosta_task.ui.features.core

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object HelperFunctions {
    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    fun noInternetConnection(context : Context, activity: Activity){

        MotionToast.darkToast(
            activity,
            duration = 13000L,
            position = MotionToast.GRAVITY_BOTTOM,
            font = ResourcesCompat.getFont(
                context,
                www.sanju.motiontoast.R.font.helvetica_regular
            ),
            style = MotionToastStyle.NO_INTERNET,
            message = "No Internet Connection , Please Restart The App ",
            title = "Sorry"
        )
    }

}