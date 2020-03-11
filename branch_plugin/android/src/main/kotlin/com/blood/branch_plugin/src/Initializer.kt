package com.blood.branch_plugin.src

import android.content.Intent
import android.util.Log
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.BranchUtil
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.MethodChannel.Result
import org.json.JSONObject
import com.blood.branch_plugin.INTENT_EXTRA_DATA

const val TAG = "BranchPlugin"

fun setUpBranchIo(registrar: PluginRegistry.Registrar, deepLinkStreamHandler: DeepLinkStreamHandler?, result: Result) {
    Log.d(TAG, "INIT BRANCH SETUP")

    Branch.getInstance().initSession({ referringParams: JSONObject?, error: BranchError? ->
        if (error == null) {
            Log.i(TAG, referringParams.toString())
            // Retrieve deeplink keys from 'referringParams' and evaluate the values to determine where to route the user
            // Check '+clicked_branch_link' before deciding whether to use your Branch routing logic

            result.success("Branch successfully initialized")

            val params = referringParams?.toString()
            val intent = Intent()
            intent.putExtra(INTENT_EXTRA_DATA, params)
            intent.putExtra("branch_force_new_session", true)
            deepLinkStreamHandler!!.handleIntent(registrar.activity(), intent)
        } else {
            Log.e(TAG, error.message)
        }
    }, registrar.activity().intent.data, registrar.activity())
}

fun reinitBranchSession(registrar: PluginRegistry.Registrar, deepLinkStreamHandler: DeepLinkStreamHandler?, result: Result) {
    Log.d(TAG, "REINIT BRANCH SETUP")

    Branch.getInstance().reInitSession(registrar.activity(), { referringParams: JSONObject?, error: BranchError? ->
        if (error == null) {
            Log.i(TAG, referringParams.toString())
            // Retrieve deeplink keys from 'referringParams' and evaluate the values to determine where to route the user
            // Check '+clicked_branch_link' before deciding whether to use your Branch routing logic

            result.success("Branch successfully initialized")

            val params = referringParams?.toString()
            val intent = Intent()
            intent.putExtra(INTENT_EXTRA_DATA, params)
            intent.putExtra("branch_force_new_session", true)
            deepLinkStreamHandler!!.handleIntent(registrar.activity(), intent)
        } else {
            Log.e(TAG, error.message)
        }
    })
}

//E/BranchPlugin: Warning. Session initialization already happened. To force a new session, set intent extra, "branch_force_new_session", to true.