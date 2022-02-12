package pl.pkrysztofiak.io.section04bytestreams;

import java.io.*;

public class Example011_2 {

    public static void main(String[] args) throws IOException {
        String filepath = "section04/example011/in.txt";
        File file = new File(filepath);
        byte[] data = new byte[(int) file.length()];

        var in = new FileInputStream(filepath);
        var out = new FileOutputStream("section04/example011/copy2.txt");
        in.read(data);
        out.write(data);

        in.close();
        out.close();
    }
}
