package com.nov.hotel.gui.windows.impl;
import javafx.stage.Modality;

public class ServiceUnitEditWindow extends AbstractWindow{

    private static AbstractWindow uniqueWindow;

    private ServiceUnitEditWindow() {
        properties.fxmlFile = "/fxml/service_unit-edit.fxml";
        properties.header = "header.edit.service.unit";
        properties.style = "/styles/styles.css";
        properties.isResize = false;
        properties.modality = Modality.WINDOW_MODAL;
        init();
    }

    public static AbstractWindow getInstance() {
        if (uniqueWindow == null){
            uniqueWindow = new ServiceUnitEditWindow();
        }
        return uniqueWindow;
    }

    public void close(){
        super.close();
        uniqueWindow = null;
    }


}
