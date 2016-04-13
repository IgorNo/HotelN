package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.collections.impl.ApartTypeCollection;
import com.nov.hotel.collections.impl.BlockCollection;
import com.nov.hotel.collections.interfaces.ObservableCollection;
import com.nov.hotel.entities.ApartType;
import com.nov.hotel.entities.Apartment;
import com.nov.hotel.entities.Block;
import com.nov.hotel.gui.controllers.abstr.AbstractEditDialogController;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.windows.impl.AbstractWindow;
import com.nov.hotel.gui.windows.impl.ApartmentsWindow;
import com.nov.hotel.gui.windows.impl.BlockWindow;
import com.nov.hotel.gui.windows.impl.ApartTypeWindow;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ApartmentEditController extends AbstractEditDialogController<Long,Apartment> {

    public ComboBox comboBlock;
    public ComboBox comboType;
    public TextField txtRoomNumber;
    public TextField txtLevel;
    public CheckBox chekbStatus;

    ObservableCollection<Block> blocks = BlockCollection.getInstance().readAllData();
    ObservableCollection<ApartType> types = ApartTypeCollection.getInstance().readAllData();

    @Override
    protected void fillField() {
        txtRoomNumber.setText(getElem().getRoomNumber());
        txtLevel.setText(Integer.toString(getElem().getLevelNumber()));
        chekbStatus.setSelected(getElem().getStatus());
//        blocks.readAllData();
        comboBlock.setItems(blocks.getViewList());
        comboBlock.getSelectionModel().select(getElem().getBlock());
//        types.readAllData();
        comboType.setItems(types.getViewList());
        comboType.getSelectionModel().select(getElem().getType());
    }

    @Override
    protected void saveField() {
        getElem().setRoomNumber(txtRoomNumber.getText());
        getElem().setLevelNumber(Integer.parseInt(txtLevel.getText()));
        getElem().setStatus(chekbStatus.isSelected());
        Block block = (Block) comboBlock.getSelectionModel().getSelectedItem();
        if (block != null) {
            getElem().setBlock(block);
        }
        ApartType type = (ApartType) comboType.getSelectionModel().getSelectedItem();
        if (type != null) {
            getElem().setType(type);
        }
    }

    @Override
    protected Apartment copyElem(Apartment elem) {
        return new Apartment(elem);
    }

    public void selectBlock(ActionEvent actionEvent) {
        AbstractWindow window = BlockWindow.getInstance();
        window.initOwner(ApartmentsWindow.getInstance().getStage());
        window.showAndWait();
        AbstractTableController<Block> controller = window.getLoader().getController();
//        comboBlock.setItems(blocks.getViewList());
        if (controller.getSelectedElem() != null) {
            comboBlock.getSelectionModel().select(controller.getSelectedElem());
        }
    }

    public void selectType(ActionEvent actionEvent) {
        AbstractWindow window = ApartTypeWindow.getInstance();
        window.initOwner(ApartmentsWindow.getInstance().getStage());
        window.showAndWait();
        AbstractTableController<ApartType> controller = window.getLoader().getController();
//        comboType.setItems(types.getViewList());
        if (controller.getSelectedElem() != null) {
            comboType.getSelectionModel().select(controller.getSelectedElem());
        }
    }
}
