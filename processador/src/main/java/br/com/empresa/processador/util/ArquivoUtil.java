package br.com.empresa.processador.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class ArquivoUtil {

 
    public static Stream<String> read(String path, String fileName) throws IOException {
        Path filePath = Paths.get(path.concat("/").concat(fileName));
        return Files.lines(filePath, StandardCharsets.UTF_8).filter(line -> !line.isEmpty());
    }

    public static void write(String path, String fileName, String content) throws IOException {
        FileWriter writer = new FileWriter(path.concat(fileName));
        writer.write(content);
        writer.close();
    }


    public static boolean isValidInputLine(String line) {

        
        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        String[] fields = line.split("ç");

        
        if (fields.length != 4) {
            return false;
        }

        
        return fields[0].length() == 3;
    }

    public static void createDir(String dirPath) {
        File dir = new File(dirPath);
        dir.mkdirs();
    }
}
