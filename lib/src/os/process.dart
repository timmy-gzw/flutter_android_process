import 'dart:async' show Future;
import 'dart:io';
import 'package:flutter/services.dart';

abstract class Process {
  static const MethodChannel _channel =
      MethodChannel('flutter_android_process/Process');

  static Future<bool> get is64Bit async {
    assert(Platform.isAndroid);
    return await _channel.invokeMethod('is64Bit');
  }
}
