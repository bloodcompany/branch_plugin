package com.blood.branch_plugin.src

import io.flutter.app.FlutterApplication
import io.branch.referral.Branch
import android.util.Log

// THIS IS THE APPLICATION CLASS FOR THE APP...
class ApplicationClass : FlutterApplication() {
    override fun onCreate() {
        super.onCreate()

        // Branch logging for debugging
        Branch.enableLogging()

        Log.d("INSIDE APPLICATION CLASS", "BRANCH PLUGIN CALLBACK")

        // Branch object initialization
        Branch.getAutoInstance(this)
    }
}