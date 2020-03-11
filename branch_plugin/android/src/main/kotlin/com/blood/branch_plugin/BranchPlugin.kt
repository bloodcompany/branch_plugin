package com.blood.branch_plugin

import com.blood.branch_plugin.src.*
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

const val INTENT_EXTRA_DATA = "DATA"

public class BranchPlugin(private var registrar: Registrar): FlutterPlugin, MethodCallHandler {
  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    val channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "branch_plugin")
    channel.setMethodCallHandler(BranchPlugin(registrar));
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  companion object {

    private var deepLinkStreamHandler: DeepLinkStreamHandler? = null

    private const val EVENT_CHANNEL: String = "branch_plugin/event"
    private lateinit var eventChannel: EventChannel

    @JvmStatic
    fun registerWith(registrar: Registrar) {

      val instance = BranchPlugin(registrar)
      val messageChannel = MethodChannel(registrar.messenger(), "branch_plugin")

      messageChannel.setMethodCallHandler(instance)
      eventChannel = EventChannel(registrar.messenger(), EVENT_CHANNEL)

      this.deepLinkStreamHandler = this.deepLinkStreamHandler ?: DeepLinkStreamHandler()
      eventChannel.setStreamHandler(this.deepLinkStreamHandler)

    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
//    if (call.method == "initSession") {
//      setUpBranchIo(registrar, result)
//
//      result.success("WooHoo ${android.os.Build.VERSION.RELEASE}")
//    } else {
//      result.notImplemented()
//    }
    when {
      call.method == "initSession" -> {
        setUpBranchIo(registrar, deepLinkStreamHandler, result)
        result.success("WooHoo ${android.os.Build.VERSION.RELEASE}")
      }

      call.method == "reinitSession" -> {
        reinitBranchSession(registrar, deepLinkStreamHandler, result)
        result.success("Inside REINIT SESSION")
      }

      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
  }
}
