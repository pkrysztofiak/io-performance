package pl.pkrysztofiak.io.section16extra;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example113 {


    public static void main(String[] args) throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);
        Instant start = Instant.now();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(() -> {
            try {
                System.out.println(Files.walk(Path.of("D:\\locations\\mis60chazon\\GZIK^DANUTA\\390319_785\\16509F5A\\BB9686FD")).mapToLong(path -> read(path)).sum());
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                System.out.println(Files.walk(Path.of("D:\\locations\\mis60chazon\\GZIK^DANUTA\\390319_785\\16509F5A\\BB8716D8")).mapToLong(path -> read(path)).sum());
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                System.out.println(Files.walk(Path.of("D:\\locations\\mis60chazon\\GZIK^DANUTA\\390319_785\\16509F5A\\BB402FF7")).mapToLong(path -> read(path)).sum());
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                System.out.println(Files.walk(Path.of("D:\\locations\\mis60chazon\\GZIK^DANUTA\\390319_785\\16509F5A\\BB6E24F7")).mapToLong(path -> read(path)).sum());
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        latch.await();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toSeconds());


    }

    private static long read(Path path) {
        if (!Files.isRegularFile(path)) {
            return 0;
        }
        byte[] buffer = new byte[8192];
        long tally = 0;
        try (var in = Files.newInputStream(path)) {
            int count;
            while ((count = in.read(buffer)) != -1) {
                tally += count;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tally;
    }
}