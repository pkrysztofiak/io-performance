package pl.pkrysztofiak.benchmark.section04bytestreams;

import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(1)
public class Benchmark011 {

    String filepath;
    private File file;
    private byte[] data;
    private byte[] buffer;

    static byte[] buffer2 = new byte[8192];

    @Setup
    public void setup() {
        filepath = "C:/Users/Przemek/samples/GZIK^DANUTA/390319_785/16509F5A/BB5EB75D/BB5EB7A0"; //133KB;
        file = new File(filepath);
    }

    @Setup(Level.Iteration)
    public void setupArray() {
        data = new byte[(int)file.length()];
        buffer = new byte[8192];
    }

//    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
    public byte[] readBytes() throws IOException {
        var in = new FileInputStream(filepath);
        int i = 0;
        int b;
        while ((b = in.read()) != -1) {
            data[i++] = (byte) b;
        }
        in.close();
        return data;
//        Benchmark                     Mode  Cnt  Score   Error  Units
//        Benchmark011.fileInputStream    ss    5  0,145 ± 0,008   s/op
    }

//    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
    public byte[] readBytesToArray() throws IOException {
        String path = "C:/Users/Przemek/samples/GZIK^DANUTA/390319_785/16509F5A/BB5EB75D/BB5EB7A0";
        byte[] bytes = new byte[(int) new File(path).length()];
        var in = new FileInputStream(path);
        in.read(bytes);
        return bytes;
//        Benchmark                      Mode  Cnt  Score   Error  Units
//        Benchmark011.readBytesToArray    ss       0,682          ms/op
//
//        For 35k files it will take 23.87 seconds to read all files
    }

//    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
    public byte[] readBytesToBuffer() throws IOException {
        String path = "C:/Users/Przemek/samples/GZIK^DANUTA/390319_785/16509F5A/BB5EB75D/BB5EB7A0";
        byte[] bytes = new byte[(int) new File(path).length()];
        var in = new FileInputStream(path);

        int tally;
        int total = 0;
        while ((tally = in.read(buffer)) != -1) {
            System.arraycopy(buffer, 0, bytes, total, tally);
            total += tally;
        }
        in.close();

        return bytes;
//        Benchmark                       Mode  Cnt  Score   Error  Units
//        Benchmark011.readBytesToBuffer    ss       0,815          ms/op
    }

//    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
    public int readAndReturnTotal() throws IOException {
//        byte[] buffer = new byte[8192];
        byte[] buffer = new byte[16384];
        var in = new FileInputStream("D:/locations/mis60chazon/GZIK^DANUTA/390319_785/16509F5A/BB5EB75D/BB5EB7DD");
        int count;
        int tally = 0;
        while ((count = in.read(buffer)) != -1) {
            tally += count;
        }
        in.close();
        return tally;
//        Benchmark                        Mode  Cnt  Score   Error  Units
//        Benchmark011.readAndReturnTotal    ss       5,935          ms/op
    }

//    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
    public int bufferedInputStream() throws IOException {
        var in = new BufferedInputStream(new FileInputStream("D:/locations/mis60chazon/GZIK^DANUTA/390319_785/16509F5A/BB5EB75D/BB5EB7E0"));
        int counter = 0;
        int b;
        while ((b = in.read()) != -1) {
            counter++;
        }
        in.close();
        return counter;
//        Benchmark                         Mode  Cnt  Score   Error  Units
//        Benchmark011.bufferedInputStream    ss       8,069          ms/op
    }

//    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
    public long walk() throws IOException {
        return Files.walk(Path.of("D:/locations/mis60chazon/GZIK^DANUTA")).mapToLong(path -> read(path)).sum();
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public long walk2() throws IOException {
        return Files.walk(Path.of("D:/locations/mis60chazon/GZIK^DANUTA")).mapToLong(path -> read2(path)).sum();
    }

    private static long read(Path path) {
//        System.out.println(path.getFileName());
        if (!Files.isRegularFile(path)) {
            return 0;
        }

        long tally = 0;
        try (var in = Files.newInputStream(path)) {
            int count;
            while ((count = in.read(buffer2)) != -1) {
                tally += count;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tally;
    }

    private static long read2(Path path) {
//        System.out.println(path.getFileName());
        if (!Files.isRegularFile(path)) {
            return 0;
        }
        long tally = 0;

        try (var in = new FileInputStream(path.toFile())) {
            int count;
            while ((count = in.read(buffer2)) != -1) {
                tally += count;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tally;
    }
}
