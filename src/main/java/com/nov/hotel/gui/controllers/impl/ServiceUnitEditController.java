package com.nov.hotel.gui.controllers.impl;


import com.nov.hotel.entities.ServiceUnit;
import com.nov.hotel.gui.controllers.abstr.AbstractEditDialogController;
import javafx.scene.control.TextField;

public class ServiceUnitEditController extends AbstractEditDialogController<Integer, ServiceUnit> {
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
    protected ServiceUnit copyElem(ServiceUnit elem) {
        return new ServiceUnit(elem);
    }
}
