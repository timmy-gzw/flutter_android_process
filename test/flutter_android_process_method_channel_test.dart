import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_android_process/flutter_android_process_method_channel.dart';

void main() {
  MethodChannelFlutterAndroidProcess platform = MethodChannelFlutterAndroidProcess();
  const MethodChannel channel = MethodChannel('flutter_android_process');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
