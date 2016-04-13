package com.nov.hotel.gui.windows.impl;


import javafx.stage.Modality;

public class SettlingWindow extends AbstractWindow {

    private static AbstractWindow uniqueWindow;

    private SettlingWindow() {
        properties.fxmlFile = "/fxml/invoice-edit.fxml";
        properties.header = "header.settling";
        properties.style = "/styles/styles.css";
        properties.isResize = true;
        properties.minWidth = 950;
        properties.minHeight = 620;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new SettlingWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }


}
