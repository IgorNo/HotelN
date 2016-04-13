package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ApartStatusWindow extends AbstractWindow {

    private static AbstractWindow uniqueWindow;

    private ApartStatusWindow() {
        properties.fxmlFile = "/fxml/apart_status.fxml";
        properties.header = "header.appart.status";
        properties.style = "/styles/styles.css";
        properties.isResize = true;
        properties.minWidth = 715;
        properties.minHeight = 432;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ApartStatusWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }

}
