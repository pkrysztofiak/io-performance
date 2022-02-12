package pl.pkrysztofiak.io.section03file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Example001 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        File file1 = new File("section03/file1.txt");

        File directory = new File("section03");
        File file2 = new File(directory, "file2.txt");

        File file3 = new File("section03", "file3.txt");

        URI uri = new URI("file:///C:/personal/file4.txt");
        File file4 = new File(uri);
    }
}