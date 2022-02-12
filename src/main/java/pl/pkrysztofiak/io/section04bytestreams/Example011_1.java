package pl.pkrysztofiak.io.section04bytestreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Example011_1 {

    public static void main(String[] args) throws IOException {
        var in = new FileInputStream("section04/example011/in.txt");
        var out = new FileOutputStream("section04/example011/copy1.txt");
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        in.close();
        out.close();
    }
}