package com.nov.hotel.gui.controllers.impl;


import com.nov.hotel.entities.ServiceType;
import com.nov.hotel.gui.controllers.abstr.AbstractEditDialogController;
import javafx.scene.control.TextField;

public class ServiceTypeEditController extends AbstractEditDialogController<Integer, ServiceType> {
    public TextField txtName;

    @Override
    protected void fillField() {
        txtName.setText(getElem().getName());
    }


    @Override
    protected void saveField() {
        getElem().setName(txtName.getText());

    }

    @Override
    protected ServiceType copyElem(ServiceType elem) {
        return new ServiceType(elem);
    }
}
