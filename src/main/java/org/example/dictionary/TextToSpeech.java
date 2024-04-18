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

        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
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
                return "completed";
            }
        });
    }

    public static void deallocateSynthesizer() {
        if (speakPlainText == null) {
            return;
        }

        try {
            speakPlainText.deallocate();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void shutdownExecutorService() {
        if (service != null) {
            service.shutdown();
            try {
                if (!service.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                    service.shutdownNow();
                }
            } catch (InterruptedException e) {
                service.shutdownNow();
            }
        }
    }

    public static void shutDown() {
        deallocateSynthesizer();
        shutdownExecutorService();
    }
}
