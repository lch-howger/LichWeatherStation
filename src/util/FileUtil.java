package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Station;
import model.Weather;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * @return
     */
    public static List<Station> initData() {
        List<Station> list = new ArrayList<>();
        File data = new File("data");
        if (data.exists() && !data.isFile()) {
            File[] files = data.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String name = FileUtil.getFileName(file);
                ObservableList<Weather> weathers = readFile(file);
                Station station = new Station(i + 1, name, weathers);
                list.add(station);
            }
        }

        return list;
    }

    /**
     * @param file
     * @return
     */
    private static ObservableList<Weather> readFile(File file) {
        //create weather list
        ObservableList<Weather> list = FXCollections.observableArrayList();

        try {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
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
