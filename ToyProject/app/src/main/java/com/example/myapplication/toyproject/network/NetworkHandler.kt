package com.example.myapplication.toyproject.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkHandler {
    companion object {
        fun isNetworkAvailable(context: Context?): Boolean {
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network = connectivityManager?.activeNetwork ?: return false
                //현재 네트워크 상태 가져오기.
                val activeNetwork =
                    connectivityManager?.getNetworkCapabilities(network) ?: return false

                return when {
                    activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } else {
                val networkInfo = connectivityManager?.activeNetworkInfo ?: return false
                return networkInfo.isConnected
            }
        }
    }

}