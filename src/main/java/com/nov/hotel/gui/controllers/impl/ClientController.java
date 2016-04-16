package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.collections.impl.ClientCollection;
import com.nov.hotel.entities.*;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.windows.impl.ClientEditWindow;
import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.time.LocalDate;

public class ClientController extends AbstractTableController<Client> {

    public TableView tableClients;
    public TableColumn columnType;
    public TableColumn columnRegDate;
    public TableColumn columnName;
    public TableColumn columnSex;
    public TableColumn columnBirthday;
    public TableColumn columnPassport;
    public TableColumn columnCountry;
    public TableColumn columnLastStay;
    public TableColumn columnNumberStay;

    @Override
    protected void initData() {
        setEditWindow(ClientEditWindow.getInstance());
        setCollection(ClientCollection.getInstance());

        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnRegDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        columnSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        // Custom rendering of the table cell.
        columnSex.setCellFactory(column -> {
            return new TableCell<Client, Boolean>(){
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) setText(null);
                    else {
                        if (item) setText("  "+rBundle.getString("radio.button.man"));
                        else setText("  "+rBundle.getString("radio.button.woman"));
                    }
                }
            };
        });

        columnBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        columnPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        columnCountry.setCellValueFactory(new PropertyValueFactory<>("citizenship"));
        columnLastStay.setCellValueFactory(new PropertyValueFactory<>("lastStay"));
        columnNumberStay.setCellValueFactory(new PropertyValueFactory<>("numberStay"));
    }

    @Override
    protected TableView getTable() {
        return tableClients;
    }

    @Override
    protected boolean isElemFound(Client elem) {
        return false;
    }

    public void add(ActionEvent actionEvent) {
        addAbst(new Client(new DocumType(), new ClientType(), new Country(), new Region()));
    }

    public void copyAndEdit(ActionEvent actionEvent) {
        copyAbst(new Client((Client) getTable().getSelectionModel().getSelectedItem()));
    }
}
