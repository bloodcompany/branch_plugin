import Flutter
import UIKit

var eventChannel: FlutterEventChannel?
let EVENT_CHANNEL = "branch_plugin/event";

public class SwiftBranchPlugin: NSObject, FlutterPlugin, FlutterStreamHandler  {

  var eventSink: FlutterEventSink?
  var initialParams : [String: Any]? = nil
  var initialError : NSError? = nil

  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "branch_plugin", binaryMessenger: registrar.messenger())
    
    let instance = SwiftBranchPlugin()
    eventChannel = FlutterEventChannel(name: EVENT_CHANNEL, binaryMessenger: registrar.messenger())
    eventChannel!.setStreamHandler(instance)
    
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  //---------------------------------------------------------------------------------------------
  // FlutterMethodChannel Interface Methods
  // --------------------------------------------------------------------------------------------
    
  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }

  //---------------------------------------------------------------------------------------------
  // FlutterStreamHandler Interface Methods
  // --------------------------------------------------------------------------------------------
    
  public func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        self.eventSink = events

        if (initialParams != nil) {
            self.eventSink!(initialParams)
            initialParams = nil
            initialError = nil
        } else if (initialError != nil) {
            
            self.eventSink!(FlutterError(code: String(initialError!.code),
            message: initialError!.localizedDescription,
            details: nil))
            
            initialParams = nil
            initialError = nil
        }

        return nil
  }

  public func onCancel(withArguments arguments: Any?) -> FlutterError? {
        eventSink = nil
        initialParams = nil
        initialError = nil

        return nil
  }
}
