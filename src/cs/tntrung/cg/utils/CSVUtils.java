package cs.tntrung.cg.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static <T> void write(String path, List<T> items) {
//        Path data = Paths.get ( "data" );
//        if (!Files.exists(data)) {
//            try {
//                Files.createDirectory(data);
////                System.out.print("Create path = " + file + " successfully!");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.print("");
//        }

        try (PrintWriter printWriter = new PrintWriter(path)) {
            for (T item : items) {
                printWriter.println(item.toString());
            }
            printWriter.flush();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(path + " invalid");
        }
    }

    public static List<String> read(String path) {
        List<String> lines = new ArrayList<> ();
        try {
            File file = new File (path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty())
                lines.add(line);
//            file.close ();
//            br.close ();
        } catch (IOException e) {
            throw new IllegalArgumentException (path + " invalid");
        }
        return lines;
    }
}
