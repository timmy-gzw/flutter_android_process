import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter_android_process/android_os.dart' as android_os;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool is64Bit = false;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    android_os.Process.is64Bit.then((value) {
      setState(() {
        this.is64Bit = value;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('is64Bit: $is64Bit'),
        ),
      ),
    );
  }
}
