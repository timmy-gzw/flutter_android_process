package com.timmy.flutter_android_process;

import android.content.Context;
import android.os.Build;
import android.os.Process;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class ProcessHandler implements MethodChannel.MethodCallHandler {
    final protected FlutterPlugin.FlutterPluginBinding binding;

    static final String CHANNEL = "flutter_android_process/Process";

    ProcessHandler(final @NonNull FlutterPlugin.FlutterPluginBinding binding) {
        this.binding = binding;
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        assert (call != null);
        assert (result != null);
        assert (call.method != null);
        switch (call.method) {
            case "is64Bit": {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean is64Bit = Process.is64Bit();
                    result.success(is64Bit);
                } else {
                    result.success(isART64());
                }
                break;
            }
            default: {
                result.notImplemented();
            }
        }
    }

    private boolean isART64() {
        final String fileName = "art";
        try {
            final Context context = this.binding.getApplicationContext();
            ClassLoader classLoader = context.getClassLoader();
            Class<?> cls = ClassLoader.class;
            Method method = cls.getDeclaredMethod("findLibrary", String.class);
            Object object = method.invoke(classLoader, fileName);
            if (object != null) {
                return ((String) object).contains("lib64");
            }
        } catch (Exception e) {
            //如果发生异常就用方法②
            return is64bitCPU();
        }

        return false;
    }

    private boolean is64bitCPU() {
        String CPU_ABI = null;
        if (Build.VERSION.SDK_INT >= 21) {
            String[] CPU_ABIS = Build.SUPPORTED_ABIS;
            if (CPU_ABIS.length > 0) {
                CPU_ABI = CPU_ABIS[0];
            }
        } else {
            CPU_ABI = Build.CPU_ABI;
        }

        if (CPU_ABI != null && CPU_ABI.contains("arm64")) {
            return true;
        }

        return false;
    }
}
