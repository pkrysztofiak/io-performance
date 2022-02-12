package pl.pkrysztofiak.benchmark.section03file;


import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@OutputTimeUnit(TimeUnit.SECONDS)
//@BenchmarkMode(Mode.AverageTime)
//@Warmup(iterations = 2)
//@Measurement(iterations = 2)
//@Fork(2)
public class Benchmark03File {

//    @Benchmark
//    public void benchmark() {
//
//    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public String[] list() {
        File directory = new File("D:/locations/mis60chazon/390319_785/16509F5A/BB402FF7");
        return directory.list();
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public Stream<Path> filesList() throws IOException {
        return Files.list(Path.of("D:/locations/mis60chazon/390319_785/16509F5A/BB402FF7"));
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public Stream<Path> filesList2() throws IOException {
        return Files.list(Paths.get("D:/locations/mis60chazon/390319_785/16509F5A/BB402FF7"));
    }
}
