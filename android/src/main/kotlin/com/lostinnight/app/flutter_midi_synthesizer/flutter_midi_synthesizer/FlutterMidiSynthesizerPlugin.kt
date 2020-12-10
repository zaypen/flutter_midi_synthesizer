package com.lostinnight.app.flutter_midi_synthesizer.flutter_midi_synthesizer

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

import org.billthefarmer.mididriver.MidiDriver

/** FlutterMidiSynthesizerPlugin */
class FlutterMidiSynthesizerPlugin : FlutterPlugin, MethodCallHandler {
    private lateinit var channel: MethodChannel
    private lateinit var midi: MidiDriver

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_midi_synthesizer")
        channel.setMethodCallHandler(this)
        midi = MidiDriver()
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "getPlatformVersion" -> {
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
            }
            "start" -> {
                midi.start()
            }
            "stop" -> {
                midi.stop()
            }
            "setVolume" -> {
                val vol = call.arguments<Int>()
                midi.setVolume(vol)
            }
            "write" -> {
                val data = call.arguments<ByteArray>()
                midi.write(data)
            }
            "configs" -> {
                val data = midi.config()
                result.success(data)
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
