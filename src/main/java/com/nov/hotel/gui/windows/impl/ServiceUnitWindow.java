package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ServiceUnitWindow extends AbstractWindow{
    private static AbstractWindow uniqueWindow;

    private ServiceUnitWindow() {
        properties.fxmlFile = "/fxml/service_unit.fxml";
        properties.header = "header.service.unit";
        properties.style = "/styles/styles.css";
        properties.isResize = true;
        properties.minWidth = 715;
        properties.minHeight = 432;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ServiceUnitWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }

}
