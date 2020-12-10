import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_midi_synthesizer/flutter_midi_synthesizer.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_midi_synthesizer');

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
    expect(await MidiSynthesizer.platformVersion, '42');
  });
}
