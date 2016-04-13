package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ApartTypeWindow extends AbstractWindow {

    private static AbstractWindow uniqueWindow;

    private ApartTypeWindow() {
        properties.fxmlFile = "/fxml/apart_type.fxml";
        properties.header = "header.apart.type";
        properties.style = "/styles/styles.css";
        properties.isResize = true;
        properties.minWidth = 715;
        properties.minHeight = 432;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ApartTypeWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }
}
