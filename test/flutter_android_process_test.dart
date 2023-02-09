import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_android_process/flutter_android_process.dart';
import 'package:flutter_android_process/flutter_android_process_platform_interface.dart';
import 'package:flutter_android_process/flutter_android_process_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterAndroidProcessPlatform
    with MockPlatformInterfaceMixin
    implements FlutterAndroidProcessPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterAndroidProcessPlatform initialPlatform = FlutterAndroidProcessPlatform.instance;

  test('$MethodChannelFlutterAndroidProcess is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterAndroidProcess>());
  });

  test('getPlatformVersion', () async {
    FlutterAndroidProcess flutterAndroidProcessPlugin = FlutterAndroidProcess();
    MockFlutterAndroidProcessPlatform fakePlatform = MockFlutterAndroidProcessPlatform();
    FlutterAndroidProcessPlatform.instance = fakePlatform;

    expect(await flutterAndroidProcessPlugin.getPlatformVersion(), '42');
  });
}
