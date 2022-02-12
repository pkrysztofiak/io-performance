package pl.pkrysztofiak.io.section03file;

import java.io.File;

public class Example002 {

    public static void main(String[] args) {
        File directory = new File("section03/example002");

        String[] files = directory.list();
        for (String file : files) {
            if (new File(directory, file).isFile()) {
                System.out.println("file: " + file);
            }
        }
    }
}
