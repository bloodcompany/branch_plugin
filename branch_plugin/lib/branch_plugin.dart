import 'dart:async';

import 'package:flutter/services.dart';

class BranchPlugin {
  static const MethodChannel _channel = const MethodChannel('branch_plugin');
  static const _eventChannel = const EventChannel('branch_plugin/event');

  static Stream<String> mainStream;

  static Future<String> get initSession async {
    final String version = await _channel.invokeMethod('initSession');
    return version;
  }

  static void reinitSession() async {
    await _channel.invokeMethod('reinitSession');
  }

  static Stream<String> deepLinkStreamListener() {
    if (mainStream == null) {
      mainStream = _eventChannel.receiveBroadcastStream().cast<String>();
    }

    return mainStream;
  }
}
