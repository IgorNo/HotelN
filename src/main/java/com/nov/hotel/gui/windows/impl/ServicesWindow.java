package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ServicesWindow extends AbstractWindow {

    private static AbstractWindow uniqueWindow;

    private ServicesWindow() {
        properties.fxmlFile = "/fxml/services.fxml";
        properties.header = "header.services";
        properties.style = "/styles/styles.css";
        properties.isResize = true;
        properties.minWidth = 715;
        properties.minHeight = 432;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ServicesWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }


}
