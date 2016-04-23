package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ServiceEditWindow extends AbstractWindow{
    private static AbstractWindow uniqueWindow;

    private ServiceEditWindow() {
        properties.fxmlFile = "/fxml/services-edit.fxml";
        properties.header = "header.edit.service";
        properties.style = "/styles/styles.css";
        properties.isResize = false;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ServiceEditWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }

}
