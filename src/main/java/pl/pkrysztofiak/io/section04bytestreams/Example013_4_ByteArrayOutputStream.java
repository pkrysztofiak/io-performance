package pl.pkrysztofiak.io.section04bytestreams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Example013_4_ByteArrayOutputStream {

    public static void main(String[] args) throws IOException {

        byte[] bytes = "0123456789ABCDEFGHIJKLMNOPQRSTVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".getBytes();
        var in = new ByteArrayInputStream(bytes);
        var out = new ByteArrayOutputStream();

        byte[] buffer = new byte[16];
        int tally;
        while ((tally = in.read(buffer)) != -1) {
            out.write(buffer);
        }
        System.out.println(Arrays.toString(out.toByteArray()));
    }
}
