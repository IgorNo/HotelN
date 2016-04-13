package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.collections.impl.ApartmentCollection;
import com.nov.hotel.entities.ApartType;
import com.nov.hotel.entities.Apartment;
import com.nov.hotel.entities.Block;
import com.nov.hotel.entities.interfaces.Entity;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.windows.impl.ApartmentEditWindow;
import com.nov.hotel.gui.windows.impl.ApartmentsWindow;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ApartmentController extends AbstractTableController<Apartment> {

    @FXML
    public TableView tableAppartments;
    @FXML
    public TableColumn columnBlock;
    @FXML
    public TableColumn columnLevel;
    @FXML
    public TableColumn columnRoomNumber;
    @FXML
    public TableColumn columnSizing;
    @FXML
    public TableColumn columnRoomType;
    @FXML
    public TableColumn columnSlots;
    @FXML
    public TableColumn columnPrice1;
    @FXML
    public TableColumn columnPrice2;
    @FXML
    public TableColumn columnPrice3;
    @FXML
    public TableColumn columnRepair;

    @Override
    protected void initData() {

        setEditWindow(ApartmentEditWindow.getInstance());
        setCollection(ApartmentCollection.getInstance());
        columnBlock.setCellValueFactory(new PropertyValueFactory<>("block"));
        columnLevel.setCellValueFactory(new PropertyValueFactory<>("levelNumber"));
        columnRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        columnRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnSizing.setCellValueFactory(new PropertyValueFactory<>("type"));

        // Custom rendering of the table cell.
        columnSizing.setCellFactory(column -> {
            return new TableCell<Apartment, ApartType>(){
                @Override
                protected void updateItem(ApartType item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) setText(null);
                    else setText(Integer.toString(item.getSize()));
                }
            };
        });
        columnSlots.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnSlots.setCellFactory(column -> {
            return new TableCell<Apartment, ApartType>(){
                @Override
                protected void updateItem(ApartType item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) setText(null);
                    else setText(Integer.toString(item.getnSlots()));
                }
            };
        });
        columnPrice1.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnPrice1.setCellFactory(column -> {
            return new TableCell<Apartment, ApartType>(){
                @Override
                protected void updateItem(ApartType item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) setText(null);
                    else setText(Float.toString(item.getPriceDay()));
                }
            };
        });
        columnPrice2.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnPrice2.setCellFactory(column -> {
            return new TableCell<Apartment, ApartType>(){
                @Override
                protected void updateItem(ApartType item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) setText(null);
                    else setText(Float.toString(item.getPriceHour()));
                }
            };
        });
        columnPrice3.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnPrice3.setCellFactory(column -> {
            return new TableCell<Apartment, ApartType>(){
                @Override
                protected void updateItem(ApartType item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) setText(null);
                    else setText(Float.toString(item.getPriceSlot()));
                }
            };
        });
        columnRepair.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnRepair.setCellFactory(new Callback<TableColumn<Apartment, Boolean>, TableCell<Apartment, Boolean>>() {

            public TableCell<Apartment, Boolean> call(TableColumn<Apartment, Boolean> p) {
                return new CheckBoxTableCell<Apartment, Boolean>();
            }
        });
    }

    @Override
    protected TableView getTable() {
        return tableAppartments;
    }

    @Override
    protected boolean isElemFound(Apartment elem) {
        String strFind = txtFind.getText().toLowerCase();
        return elem.getRoomNumber().toLowerCase().contains(strFind) ||
                elem.getBlock().getName().toLowerCase().contains(strFind) ||
                elem.getType().getName().toLowerCase().contains(strFind);
    }

    public void add(ActionEvent actionEvent) {
        addAbst(new Apartment(new ApartType(), new Block()));
    }

    public void copyAndEdit(ActionEvent actionEvent) {
        copyAbst(new Apartment((Apartment) getTable().getSelectionModel().getSelectedItem()));
    }
}
