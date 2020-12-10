
import 'dart:async';
import 'dart:typed_data';

import 'package:flutter/services.dart';

class MidiSynthesizer {
  static const MethodChannel _channel =
      const MethodChannel('flutter_midi_synthesizer');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static start() async {
    await _channel.invokeMethod('start');
  }

  static stop() async {
    await _channel.invokeMethod('stop');
  }

  static setVolume(int volume) async {
    await _channel.invokeMethod('start', volume);
  }

  static write(Uint8List data) async {
    await _channel.invokeMethod('write', data);
  }

  static Future<Uint64List> config() async {
    return await _channel.invokeListMethod('config');
  }
}
