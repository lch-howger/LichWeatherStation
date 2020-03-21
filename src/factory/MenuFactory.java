package factory;

import constant.StringValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import util.AlertUtil;

public class MenuFactory {

    public static Menu createMenu(String s) {
        Menu menu = new Menu(s);
        if (s.equals("File")) {
            MenuItem open = createMenuItem("Open");
            menu.getItems().addAll(open);
        } else if (s.equals("Edit")) {
            MenuItem add = createMenuItem("Add");
            MenuItem delete = createMenuItem("Delete");
            menu.getItems().addAll(add, delete);
        } else if (s.equals("Help")) {
            MenuItem help = createMenuItem("Help");
            MenuItem about = createMenuItem("About");
            menu.getItems().addAll(help, about);
        }
        return menu;
    }

    public static MenuItem createMenuItem(String s) {
        MenuItem menuItem = new MenuItem(s);
        if (s.equals("Open")) {
            menuItem.setOnAction(actionEvent -> {
                AlertUtil.alert("hello world");
            });
        } else if (s.equals("Add")) {
            menuItem.setOnAction(actionEvent -> {
                AlertUtil.alert("hello world");
            });
        } else if (s.equals("Delete")) {
            menuItem.setOnAction(actionEvent -> {
                AlertUtil.alert("hello world");
            });
        } else if (s.equals("Help")) {
            menuItem.setOnAction(actionEvent -> {
                AlertUtil.alert(StringValue.MENU_HELP);
            });
        } else if (s.equals("About")) {
            menuItem.setOnAction(actionEvent -> {
                AlertUtil.alert(StringValue.MENU_ABOUT);
            });
        }
        return menuItem;
    }
}
