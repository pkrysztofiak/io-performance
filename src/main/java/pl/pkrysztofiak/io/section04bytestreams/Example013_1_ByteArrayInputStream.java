package pl.pkrysztofiak.io.section04bytestreams;

import java.io.ByteArrayInputStream;

public class Example013_1_ByteArrayInputStream {

    public static void main(String[] args) {
        byte[] bytes = "Hello, world!".getBytes();

        var in = new ByteArrayInputStream(bytes);
        int b;
        while ((b = in.read()) != -1) {
            System.out.println(b);
        }
    }
}
