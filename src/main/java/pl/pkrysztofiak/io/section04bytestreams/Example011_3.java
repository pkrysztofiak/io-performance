package pl.pkrysztofiak.io.section04bytestreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Example011_3 {

    public static void main(String[] args) throws IOException {
        String filepath = "section04/example011/in.txt";
        File file = new File(filepath);
        byte[] buffer = new byte[1024];

        var in = new FileInputStream(filepath);
        var out = new FileOutputStream("section04/example011/copy3.txt");

        int tally;
        while ((tally = in.read(buffer)) != -1) {
            out.write(buffer, 0, tally);
        }
        in.close();
        out.close();
    }
}
