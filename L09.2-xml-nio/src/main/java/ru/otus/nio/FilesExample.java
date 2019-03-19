package ru.otus.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author sergey
 * created on 25.09.18.
 */
public class FilesExample {
    public static void main(String[] args) throws IOException {
        new FilesExample().go();
    }

    private void go() throws IOException {
        Path path = Paths.get("L09.2-xml-nio/src/main/resources/share.xml");
        boolean pathExists = Files.exists(path);
        System.out.println(pathExists);
        Path pathNE = Paths.get("L09.2-xml-nio/src/main/resources/NE.xml");
        boolean pathNotExists = Files.exists(pathNE);
        System.out.println(pathNotExists);

        Files.createDirectory(Paths.get("L09.2-xml-nio/tmp"));

        Path source = Paths.get("L09.2-xml-nio/src/main/resources/share.xml");
        Path destination = Paths.get("L09.2-xml-nio/tmp/share.xml");

        Files.copy(source, destination);


        long size = Files.size(path);
        System.out.println("file size: " + size);


        System.out.println("contentAll:");
        Files.lines(path).forEach(System.out::println);

        System.out.println("filtered:");
        Files.lines(path)
                .filter(line -> line.contains("share"))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        String testString = "Test-Test-Data-String";
        Files.write(Paths.get("L09.2-xml-nio/tmp/newFile.txt"), testString.getBytes());
    }
}
