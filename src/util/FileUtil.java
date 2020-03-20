package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.Weather;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * @return
     */
    public static ObservableList<Weather> initData() {
        ObservableList<Weather> list = FXCollections.observableArrayList();
        File data = new File("data");
        if (data.exists() && !data.isFile()) {
            File[] files = data.listFiles();
            for (File file : files) {
                ObservableList<Weather> weathers = readFile(file);
                if (weathers != null) {
                    list.addAll(weathers);
                }
            }
        }

        return list;
    }

    /**
     * @param file
     * @return
     */
    private static ObservableList<Weather> readFile(File file) {
        try {
            //create weather list
            ObservableList<Weather> list = FXCollections.observableArrayList();

            //get file name from file
            String name = FileUtil.getFileName(file);

            //create file reader
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length == 6) {
                    count++;
                    String id = name + "-" + count;
                    Weather weather = new Weather(
                            id,
                            name,
                            split[0],
                            split[1],
                            split[2],
                            split[3],
                            split[4],
                            split[5]);
                    list.add(weather);
                }
            }

            reader.close();
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param file
     * @return
     */
    private static String getFileName(File file) {
        if (file.exists() && file.isFile()) {
            String name = file.getName();
            if (name.endsWith(".csv")) {
                int length = name.length();
                name = name.substring(0, length - 4);
                return name;
            }
        }
        return "";
    }
}
