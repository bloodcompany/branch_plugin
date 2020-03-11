import 'dart:async';

import 'package:flutter/services.dart';

class BranchPlugin {
  static const MethodChannel _channel = const MethodChannel('branch_plugin');

  static Future<String> get initSession async {
    final String version = await _channel.invokeMethod('initSession');
    return version;
  }

  static void reinitSession() {}
}
