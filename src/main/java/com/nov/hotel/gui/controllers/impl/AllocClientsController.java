package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.entities.AllocClient;
import com.nov.hotel.entities.Allocation;
import com.nov.hotel.entities.Client;
import com.nov.hotel.gui.controllers.abstr.AbstractController;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AllocClientsController extends AbstractController{

    public TextField txtRoom;
    public TableColumn columnName;
    public TableColumn columnSex;
    public TableColumn columnBirthday;
    public TableColumn columnPassport;
    public TableColumn columnCountry;


    public void add(ActionEvent actionEvent) {

    }

    public void delete(ActionEvent actionEvent) {
    }

    public void addOwner(ActionEvent actionEvent) {
    }

    public static void setElem(Allocation allocation) {

    }

    @Override
    public void actionClose(ActionEvent actionEvent) {

    }
}
