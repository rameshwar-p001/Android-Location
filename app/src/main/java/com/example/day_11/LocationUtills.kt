package com.example.day_11

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.content.ContextCompat

class LocationUtills(val context: Context) {
    fun hasLocationPermission(context: Context): Boolean{
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)== PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION)== PERMISSION_GRANTED
    }
}



//class LocationUtills(val context: Context) {
//    fun hasLocationPermission(context: Context): Boolean{
//        return ContextCompat.checkSelfPermission(context,
//                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
//    }
//}

//class LocationUtills(val context: Context) {
//    fun hasLocationPermission(context: Context): Boolean{
//        if(ContextCompat.checkSelfPermission(context,
//                Manifest.permission.ACCESS_FINE_LOCATION)== PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context,
//                Manifest.permission.ACCESS_COARSE_LOCATION)== PERMISSION_GRANTED
//        ){
//            return true
//        }
//        else{
//            return false
//        }
//    }
//}