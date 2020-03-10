package com.blood.branch_plugin.src

import io.flutter.app.FlutterApplication
import io.branch.referral.Branch

// THIS IS THE APPLICATION CLASS FOR THE APP...
class ApplicationClass : FlutterApplication() {
    override fun onCreate() {
        super.onCreate()

        // Branch logging for debugging
        Branch.enableLogging()

        // Branch object initialization
        Branch.getAutoInstance(this)
    }
}