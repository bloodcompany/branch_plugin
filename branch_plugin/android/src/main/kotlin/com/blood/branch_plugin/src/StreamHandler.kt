package com.blood.branch_plugin.src

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import io.flutter.plugin.common.EventChannel

class DeepLinkStreamHandler: EventChannel.StreamHandler {

    const val TAG = "BranchPlugin"

    private var receiver: BroadcastReceiver? = null

    fun handleIntent(context: Context, intent: Intent?) {
        if (this.receiver != null && intent != null) receiver!!.onReceive(context, intent)
    }

    override fun onCancel(p0: Any?) {
        receiver = null
    }

    override fun onListen(p0: Any?, events: EventChannel.EventSink?) {
        Log.d(TAG, "STREAM ON LISTEN")
        receiver = createReceiver(events)
    }

    private fun createReceiver(events: EventChannel.EventSink?): BroadcastReceiver {
        return object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                val dataString = intent.getStringExtra(INTENT_EXTRA_DATA)
                Log.d(TAG, "DATA FROM BRANCH $dataString")
                if (dataString == null) {
                    events!!.error(TAG, "Link is not available", null)
                } else {
                    events!!.success(dataString)
                }
            }
        }
    }

}