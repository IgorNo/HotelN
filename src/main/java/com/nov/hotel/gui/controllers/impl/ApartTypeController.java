package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.collections.impl.ApartTypeCollection;
import com.nov.hotel.entities.ApartType;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.windows.impl.ApartTypeEditWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApartTypeController extends AbstractTableController<ApartType> {

    @FXML
    public TableView tableAppartType;

    @FXML
    public TableColumn<ApartType, String> columnRoomType;
    @FXML
    public TableColumn<ApartType, Integer> columnSizing;
    @FXML
    public TableColumn<ApartType, Float> columnPrice1;
    @FXML
    public TableColumn<ApartType, Float> columnPrice2;
    @FXML
    public TableColumn<ApartType, Float> columnPrice3;
    public TableColumn columnSlots;


    @Override
    protected void initData() {
        setEditWindow(ApartTypeEditWindow.getInstance());
        setCollection(ApartTypeCollection.getInstance());
        columnRoomType.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSizing.setCellValueFactory(new PropertyValueFactory<>("size"));
        columnPrice1.setCellValueFactory(new PropertyValueFactory<>("priceDay"));
        columnPrice2.setCellValueFactory(new PropertyValueFactory<>("priceHour"));
        columnSlots.setCellValueFactory(new PropertyValueFactory<>("nSlots"));
        columnPrice3.setCellValueFactory(new PropertyValueFactory<>("priceSlot"));
    }

    @Override
    protected TableView getTable() {
        return tableAppartType;
    }

    @Override
    protected boolean isElemFound(ApartType elem) {

        return elem.getName().toLowerCase().contains(txtFind.getText().toLowerCase());
    }

    public void add(ActionEvent actionEvent) {
        addAbst(new ApartType());
    }

    public void copyAndEdit(ActionEvent actionEvent) {
        copyAbst(new ApartType((ApartType) getTable().getSelectionModel().getSelectedItem()));
    }

}
