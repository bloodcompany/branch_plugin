package com.blood.branch_plugin.src

import android.content.Intent
import android.util.Log
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.BranchUtil
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.MethodChannel.Result
import org.json.JSONObject

fun setUpBranchIo(registrar: PluginRegistry.Registrar, result: Result) {
//    init(registrar)
    Branch.getInstance().initSession({ referringParams: JSONObject?, error: BranchError? ->
//        Log.d(DEBUG_NAME, "BRANCH CALLBACK")
//        if (error == null) {
//            result.success("BRANCH IO INITIALIZED")
//            val params = referringParams?.toString()
//            val intent = Intent()
//            intent.putExtra(INTENT_EXTRA_DATA, params)
//            deepLinkStreamHandler!!.handleIntent(registrar.activity(), intent)
//        } else {
//            result.error("1", "BRANCH IO INITIALIZATION ERROR ${error.message}", null)
//        }
    }, registrar.activity().intent.data, registrar.activity())

}