package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ApartStatusEditWindow extends AbstractWindow {

    private static AbstractWindow uniqueWindow;

    private ApartStatusEditWindow() {
        properties.fxmlFile = "/fxml/apart_status-edit.fxml";
        properties.header = "header.edit.appart.status";
        properties.style = "/styles/styles.css";
        properties.isResize = false;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ApartStatusEditWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }


}
