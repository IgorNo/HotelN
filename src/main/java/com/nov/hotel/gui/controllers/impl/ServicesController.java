package com.nov.hotel.gui.controllers.impl;


import com.nov.hotel.collections.impl.ServiceCollection;
import com.nov.hotel.entities.Service;
import com.nov.hotel.entities.ServiceType;
import com.nov.hotel.entities.ServiceUnit;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.windows.impl.ServiceEditWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServicesController extends AbstractTableController<Service> {

    @FXML
    private TableView tableServices;
    @FXML
    private TableColumn columnName;
    @FXML
    private TableColumn columnPrice;
    @FXML
    private TableColumn columnServiceType;
    @FXML
    private TableColumn columnServiceUnit;


    @Override
    protected void initData() {
        setEditWindow(ServiceEditWindow.getInstance());
        setCollection(ServiceCollection.getInstance());
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnServiceType.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        columnServiceUnit.setCellValueFactory(new PropertyValueFactory<>("serviceUnit"));
        
    }

    @Override
    protected TableView getTable() {
        return tableServices;
    }

    @Override
    protected boolean isElemFound(Service elem) {
        String strFind = txtFind.getText().toLowerCase();
        return elem.getName().toLowerCase().contains(strFind);
    }

    public void add(ActionEvent actionEvent) {
        addAbst(new Service(new ServiceType(), new ServiceUnit()));
    }

    public void copyAndEdit(ActionEvent actionEvent) {
        copyAbst(new Service((Service) getTable().getSelectionModel().getSelectedItem()));
    }
}


