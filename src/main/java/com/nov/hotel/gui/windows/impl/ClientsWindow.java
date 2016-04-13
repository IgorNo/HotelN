package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ClientsWindow extends AbstractWindow{

    private static AbstractWindow uniqueWindow;

    private ClientsWindow() {
        properties.fxmlFile = "/fxml/clients.fxml";
        properties.header = "header.clients";
        properties.style = "/styles/styles.css";
        properties.isResize = true;
        properties.minWidth = 985;
        properties.minHeight = 550;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ClientsWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }

}
