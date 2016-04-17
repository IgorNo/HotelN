package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.entities.Invoice;
import com.nov.hotel.gui.controllers.abstr.AbstractEditDialogController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import jfxtras.scene.control.ListView;

public class InvoiceEditController extends AbstractEditDialogController<Long, Invoice> {
    
    public TextField txtInvoiceNumber;
    public ToggleGroup tgCustomer;
    public RadioButton rbtIndivid;
    public RadioButton rbtnOffice;
    public TextField txtCustomer;
    
    public ListView listvRoom;
    
    public Tab tabAccomodation;

    public DatePicker datepStart;
    public DatePicker datepEnd;
    public TextField txtStartTime;
    public TextField txtEndTime;
    public TextField txtDays;

    public ChoiceBox comboPriceType;
    public TextField txtArrivalTime;

    public ChoiceBox comboBlock;
    public ChoiceBox comboRoomType;
    public TextField txtLevel;
    public ToggleGroup tgConditions;
    public RadioButton rbtnLess;
    public RadioButton rbtnEqual;
    public RadioButton rbtnGreater;

    public TextField txtMasterBadsN;
    public TextField txtExtraBadsN;
    public ToggleGroup tgComposition;
    public RadioButton rbtMan;
    public RadioButton rbtnWoman;
    public RadioButton rbtnMixed;
    
    public ChoiceBox comboSelectedRoom;

    public TextField txtPriceDay;
    public TextField txtPriceHour;
    public TextField txtPriceSlot;
    public TextField txtAmount;

    public Tab tabRegistration;
    public Tab tabServices;
    public Tab tabInvoice;
    public Tab tabPayments;


    @Override
    protected void fillField() {

    }

    @Override
    protected void saveField() {

    }

    @Override
    protected Invoice copyElem(Invoice elem) {
        return null;
    }

    public void selectCustomer(ActionEvent actionEvent) {

    }

    public void addRoom(ActionEvent actionEvent) {
    }

    public void deleteRoom(ActionEvent actionEvent) {
    }

    public void selectBlock(ActionEvent actionEvent) {
    }

    public void selectRoomType(ActionEvent actionEvent) {
    }

    public void accomMap(ActionEvent actionEvent) {
    }

    public void selectRooms(ActionEvent actionEvent) {
    }

    public void saveRoom(ActionEvent actionEvent) {
    }
}
