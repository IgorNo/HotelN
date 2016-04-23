package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.collections.impl.ServiceTypeCollection;
import com.nov.hotel.entities.ServiceType;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.windows.impl.ServiceTypeEditWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServiceTypeController extends AbstractTableController<ServiceType> {
    @FXML
    private TableView tableServiceType;
    @FXML
    private TableColumn columnName;

    @Override
    protected void initData() {
        setEditWindow(ServiceTypeEditWindow.getInstance());
        setCollection(ServiceTypeCollection.getInstance());
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @Override
    protected TableView getTable() {
        return tableServiceType;
    }

    @Override
    protected boolean isElemFound(ServiceType elem) {
        return elem.getName().toLowerCase().contains(txtFind.getText().toLowerCase());
    }

    public void add(ActionEvent actionEvent) {
        addAbst(new ServiceType());
    }

    public void copyAndEdit(ActionEvent actionEvent) {
        copyAbst(new ServiceType((ServiceType) getTable().getSelectionModel().getSelectedItem()));
    }

}
