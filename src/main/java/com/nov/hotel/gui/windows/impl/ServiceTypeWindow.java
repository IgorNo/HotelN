package com.nov.hotel.gui.windows.impl;


import javafx.stage.Modality;

public class ServiceTypeWindow extends AbstractWindow{
    private static AbstractWindow uniqueWindow;

    private ServiceTypeWindow() {
        properties.fxmlFile = "/fxml/service_type.fxml";
        properties.header = "header.service.type";
        properties.style = "/styles/styles.css";
        properties.isResize = true;
        properties.minWidth = 715;
        properties.minHeight = 432;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ServiceTypeWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }
}
