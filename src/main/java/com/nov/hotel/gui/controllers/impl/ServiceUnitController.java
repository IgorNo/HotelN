package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.collections.impl.ServiceUnitCollection;
import com.nov.hotel.entities.ServiceUnit;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.windows.impl.ServiceUnitEditWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServiceUnitController extends AbstractTableController<ServiceUnit> {
    @FXML
    private TableView tableServiceUnit;
    @FXML
    private TableColumn columnName;

    @Override
    protected void initData() {
        setEditWindow(ServiceUnitEditWindow.getInstance());
        setCollection(ServiceUnitCollection.getInstance());
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @Override
    protected TableView getTable() {
        return tableServiceUnit;
    }

    @Override
    protected boolean isElemFound(ServiceUnit elem) {
        return elem.getName().toLowerCase().contains(txtFind.getText().toLowerCase());
    }

    public void add(ActionEvent actionEvent) {
        addAbst(new ServiceUnit());
    }

    public void copyAndEdit(ActionEvent actionEvent) {
        copyAbst(new ServiceUnit((ServiceUnit) getTable().getSelectionModel().getSelectedItem()));
    }

}
