import Flutter
import UIKit

var eventChannel: FlutterEventChannel?
let EVENT_CHANNEL = "branch_plugin/event";

public class SwiftBranchPlugin: NSObject, FlutterPlugin, FlutterStreamHandler  {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "branch_plugin", binaryMessenger: registrar.messenger())
    
    let instance = SwiftBranchPlugin()
    eventChannel = FlutterEventChannel(name: EVENT_CHANNEL, binaryMessenger: registrar.messenger())
    eventChannel!.setStreamHandler(instance)
    
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }

  public func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        // TODO
  }

  public func onCancel(withArguments arguments: Any?) -> FlutterError? {
        // TODO
  }
}
