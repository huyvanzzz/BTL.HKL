package org.example.dictionary;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;
import java.util.concurrent.*;

public class TextToSpeech {
    private static Synthesizer speakPlainText;
    private static ExecutorService service;

    public static void speak(String word) {
        if (word.isEmpty()) {
            return;
        }

        if (service == null) {
            service = Executors.newFixedThreadPool(3);
        }

        Future<String> future = service.submit(() -> {
            try {
                System.setProperty(
                        "freetts.voices",
                        "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory"
                );
                Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

                speakPlainText = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
                speakPlainText.allocate();
                speakPlainText.resume();
                speakPlainText.speakPlainText(word, null);
                speakPlainText.waitEngineState(Synthesizer.QUEUE_EMPTY);
                return "finished";
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        });
    }

    public static void shutDown() {
        deallocate();
        shutdownService();
    }

    public static void deallocate() {
        if (speakPlainText == null) {
            return;
        }

        try {
            speakPlainText.deallocate();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void shutdownService() {
        if (service != null) {
            service.shutdown();
            try {
                if (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                    service.shutdownNow();
                }
            } catch (InterruptedException e) {
                service.shutdownNow();
            }
        }
    }


}
