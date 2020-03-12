import Flutter
import UIKit

public class SwiftBranchPlugin: NSObject, FlutterPlugin {
  static var eventHandler: EventStreamHandler?

  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "branch_plugin", binaryMessenger: registrar.messenger())
    let instance = SwiftBranchPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)

    let eventChannel = FlutterEventChannel(name: "branch_plugin/event", binaryMessenger: registrar.messenger())
    eventHandler = EventStreamHandler()
    eventChannel.setStreamHandler(eventHandler)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
