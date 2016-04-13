package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.entities.ApartType;
import com.nov.hotel.gui.controllers.abstr.AbstractEditDialogController;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ApartTypeEditController extends AbstractEditDialogController<Integer, ApartType> {

    public TextField txtName;
    public TextField txtPrice1;
    public TextField txtPrice2;
    public TextField txtPrice3;
    public TextField txtSizing;
    public TextField txtnAdditionalSlot;
    public TextArea txtarDescription;


    @Override
    protected void fillField() {
        txtName.setText(getElem().getName());
        txtSizing.setText(Integer.toString(getElem().getSize()));
        txtPrice1.setText(Float.toString(getElem().getPriceDay()));
        txtPrice2.setText(Float.toString(getElem().getPriceHour()));
        txtnAdditionalSlot.setText(Integer.toString(getElem().getnSlots()));
        txtPrice3.setText(Float.toString(getElem().getPriceSlot()));
        txtarDescription.setText(getElem().getDescription());
    }

    @Override
    protected void saveField() {
        getElem().setName(txtName.getText());
        getElem().setSize(Integer.parseInt(txtSizing.getText()));
        getElem().setPriceDay(Float.parseFloat(txtPrice1.getText()));
        getElem().setPriceHour(Float.parseFloat(txtPrice2.getText()));
        getElem().setSize(Integer.parseInt(txtnAdditionalSlot.getText()));
        getElem().setPriceSlot(Float.parseFloat(txtPrice3.getText()));
        getElem().setDescription(txtarDescription.getText());
    }

    @Override
    protected ApartType copyElem(ApartType elem) {
        return new ApartType(elem);
     }
}