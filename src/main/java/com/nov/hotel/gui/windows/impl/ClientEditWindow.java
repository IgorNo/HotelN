package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ClientEditWindow extends AbstractWindow{
    private static AbstractWindow uniqueWindow;

    private ClientEditWindow() {
        properties.fxmlFile = "/fxml/client-edit.fxml";
        properties.header = "header.edit.client";
        properties.style = "/styles/styles.css";
        properties.isResize = false;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ClientEditWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }

}
