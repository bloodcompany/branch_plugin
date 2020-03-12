import Flutter
import UIKit

var eventChannel: FlutterEventChannel?
let EVENT_CHANNEL = "branch_plugin/event";

public class SwiftBranchPlugin: NSObject, FlutterPlugin, FlutterStreamHandler {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "branch_plugin", binaryMessenger: registrar.messenger())
    let instance = SwiftBranchPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)

    eventChannel = FlutterEventChannel(name: EVENT_CHANNEL, binaryMessenger: registrar.messenger())
    eventChannel!.setStreamHandler(instance)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
