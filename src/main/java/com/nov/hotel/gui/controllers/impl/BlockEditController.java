package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.entities.ApartStatus;
import com.nov.hotel.entities.Block;
import com.nov.hotel.gui.controllers.abstr.AbstractEditDialogController;
import javafx.scene.control.TextField;

public class BlockEditController extends AbstractEditDialogController<Integer,Block> {

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
    protected Block copyElem(Block elem) {
        return new Block(elem);
    }
}
