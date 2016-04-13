package com.nov.hotel.gui.windows.impl;

public class MainWindow extends AbstractWindow {

    private static AbstractWindow uniqueWindow;

    private MainWindow() {
        properties.fxmlFile = "/fxml/main.fxml";
        properties.header = "header.main";
        properties.style = "/styles/styles.css";
        properties.isResize = true;
        properties.minWidth = 900;
        properties.minHeight = 600;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new MainWindow();

        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }


}
