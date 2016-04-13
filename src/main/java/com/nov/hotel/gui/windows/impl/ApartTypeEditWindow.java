package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ApartTypeEditWindow extends AbstractWindow {

    private static AbstractWindow uniqueWindow;

    private ApartTypeEditWindow() {
        properties.fxmlFile = "/fxml/apart_type-edit.fxml";
        properties.header = "header.edit.apart.type";
        properties.style = "/styles/styles.css";
        properties.isResize = false;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ApartTypeEditWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }


}
