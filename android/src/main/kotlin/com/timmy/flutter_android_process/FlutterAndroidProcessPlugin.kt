package com.timmy.flutter_android_process

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FlutterAndroidProcessPlugin */
class FlutterAndroidProcessPlugin : FlutterPlugin {

    private lateinit var channel: MethodChannel
    private lateinit var processMethodChannel: MethodChannel

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        val messenger: BinaryMessenger = flutterPluginBinding.binaryMessenger
        processMethodChannel = MethodChannel(messenger, ProcessHandler.CHANNEL).apply {
            setMethodCallHandler(ProcessHandler(flutterPluginBinding))
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        processMethodChannel?.setMethodCallHandler(null)
    }
}
