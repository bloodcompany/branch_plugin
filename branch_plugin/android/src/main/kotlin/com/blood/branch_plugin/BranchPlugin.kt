package com.blood.branch_plugin

import com.blood.branch_plugin.src.*
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar


/** BranchPlugin */
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
    @JvmStatic
    fun registerWith(registrar: Registrar) {

      val instance = BranchPlugin(registrar)
      val messageChannel = MethodChannel(registrar.messenger(), "branch_plugin")

      messageChannel.setMethodCallHandler(instance)
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
        setUpBranchIo(registrar, result)
        result.success("WooHoo ${android.os.Build.VERSION.RELEASE}")
      }

      call.method == "reinitSession" -> {
//        setUpBranchIo(registrar, result)
        reinitBranchSession()
        result.success("Inside REINIT SESSION")
      }

      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
  }
}
