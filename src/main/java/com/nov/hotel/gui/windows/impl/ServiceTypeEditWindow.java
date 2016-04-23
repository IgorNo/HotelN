package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ServiceTypeEditWindow extends AbstractWindow{

    private static AbstractWindow uniqueWindow;

    private ServiceTypeEditWindow() {
        properties.fxmlFile = "/fxml/service_type-edit.fxml";
        properties.header = "header.edit.service.type";
        properties.style = "/styles/styles.css";
        properties.isResize = false;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ServiceTypeEditWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }


}
