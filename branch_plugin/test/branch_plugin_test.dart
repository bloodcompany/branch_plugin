import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:branch_plugin/branch_plugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('branch_plugin');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('initSession', () async {
    expect(await BranchPlugin.initSession, '42');
  });
}
