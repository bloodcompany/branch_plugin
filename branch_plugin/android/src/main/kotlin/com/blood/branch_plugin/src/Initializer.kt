package com.blood.branch_plugin.src

import android.content.Intent
import android.util.Log
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.BranchUtil
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.MethodChannel.Result
import org.json.JSONObject

const val TAG = "BranchPlugin"

fun setUpBranchIo(registrar: PluginRegistry.Registrar, result: Result) {
    Log.d(TAG, "INIT BRANCH SETUP")

    Branch.getInstance().initSession(branchListener, registrar.activity().intent.data, registrar.activity())
}

//E/BranchPlugin: Warning. Session initialization already happened. To force a new session, set intent extra, "branch_force_new_session", to true.
object branchListener : Branch.BranchReferralInitListener {
    override fun onInitFinished(referringParams: JSONObject?, error: BranchError?) {
        if (error == null) {
            Log.i(TAG, referringParams.toString())
            // Retrieve deeplink keys from 'referringParams' and evaluate the values to determine where to route the user
            // Check '+clicked_branch_link' before deciding whether to use your Branch routing logic
        } else {
            Log.e(TAG, error.message)
        }
    }
}

fun reinitBranchSession() {
    Log.d(TAG, "REINIT BRANCH SETUP")
}