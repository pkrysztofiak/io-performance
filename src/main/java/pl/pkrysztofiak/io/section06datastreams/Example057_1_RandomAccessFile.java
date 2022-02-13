package pl.pkrysztofiak.io.section06datastreams;

import java.io.*;

public class Example057_1_RandomAccessFile {

    public static void main(String[] args) throws IOException {

        File file = new File("section06/example057/file.bin");
        var out = new DataOutputStream(new FileOutputStream(file));

        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7};
        for (int i = 0; i < numbers.length; i++) {
            out.writeInt(numbers[i]);
        }


        out.close();

        var in = new RandomAccessFile("section06/example057/file.bin", "rw");
        System.out.println("file pointer: " + in.getFilePointer());
        in.seek(4);
        in.writeInt(256);
        in.seek(0);

        try {
            while (true) {
                System.out.println(in.readInt());
            }
        } catch (EOFException e) {

        }

        in.close();
    }
}