package pl.pkrysztofiak.io.section04bytestreams;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Example013_3_Mark {

    public static void main(String[] args) throws IOException {
        byte[] array = "ABCDEFGH".getBytes(StandardCharsets.UTF_8);

        var in = new ByteArrayInputStream(array);

        byte[] buffer = new byte[2];

        int tally;
        int counter = 0;
        while ((tally = in.read(buffer)) != -1) {
            for (int b : buffer) {
                System.out.println(b);
            }
            if (counter == 1) {
                in.mark(2);
            }

            if (counter == 2) {
                in.reset();
            }
            counter++;
        }
    }
}
