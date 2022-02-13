package pl.pkrysztofiak.benchmark.section04bytestreams;

import org.openjdk.jmh.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
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

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
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
}
