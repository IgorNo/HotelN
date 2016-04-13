package com.nov.hotel.gui.windows.impl;

import javafx.stage.Modality;

public class ApartmentsWindow extends AbstractWindow {

    private static AbstractWindow uniqueWindow;

    private ApartmentsWindow() {
        properties.fxmlFile = "/fxml/apartments.fxml";
        properties.header = "header.appartments";
        properties.style = "/styles/styles.css";
        properties.isResize = true;
        properties.minWidth = 975;
        properties.minHeight = 550;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ApartmentsWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }

}
