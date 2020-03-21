package model;

import javafx.collections.ObservableList;

public class Station {
    private int id;
    private String name;
    private ObservableList<Weather> list;

    public Station(int id, String name, ObservableList<Weather> list) {
        this.id = id;
        this.name = name;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Weather> getList() {
        return list;
    }

    public void setList(ObservableList<Weather> list) {
        this.list = list;
    }
}
