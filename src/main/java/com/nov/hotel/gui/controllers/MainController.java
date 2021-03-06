package com.nov.hotel.gui.controllers;


import com.nov.hotel.collections.impl.*;
import com.nov.hotel.entities.Invoice;
import com.nov.hotel.gui.controllers.abstr.AbstractController;
import com.nov.hotel.gui.controllers.abstr.AbstractEditDialogController;
import com.nov.hotel.gui.windows.impl.AbstractWindow;
import com.nov.hotel.gui.windows.impl.*;
import javafx.event.ActionEvent;

public class MainController extends AbstractController {

    private AbstractWindow settlingWindow;
    private AbstractWindow priceListWindow;
    private AbstractWindow apartmentsWindow;
    private AbstractWindow servicesWindow;
    private AbstractWindow apartStatusWindow;
    private AbstractWindow blocksWindow;
    private AbstractWindow clientWindow;
//    private AbstractEditDialogController invoiceEC;
    private AbstractWindow serviceTypesWindow;
    private AbstractWindow serviceUnitsWindow;
    

    public void hotelSettling(ActionEvent actionEvent) {
        settlingWindow = SettlingWindow.getInstance();
        settlingWindow.initOwner(MainWindow.getInstance().getStage());
        settlingWindow.showAndWait();
    }

    public void editPriceList(ActionEvent actionEvent) {
        priceListWindow = ApartTypeWindow.getInstance();
        priceListWindow.initOwner(MainWindow.getInstance().getStage());
        ApartTypeCollection.getInstance().readAllData();
        priceListWindow.showAndWait();
    }

    public void editAppartments(ActionEvent actionEvent) {
        apartmentsWindow = ApartmentsWindow.getInstance();
        apartmentsWindow.initOwner(MainWindow.getInstance().getStage());
        ApartmentCollection.getInstance().readAllData();
        apartmentsWindow.showAndWait();
    }

    public void editServices(ActionEvent actionEvent) {
        servicesWindow = ServicesWindow.getInstance();
        servicesWindow.initOwner(MainWindow.getInstance().getStage());
        ServiceCollection.getInstance().readAllData();
        servicesWindow.showAndWait();
    }

    public void editAppartmentStatus(ActionEvent actionEvent) {
        apartStatusWindow = ApartStatusWindow.getInstance();
        apartStatusWindow.initOwner(MainWindow.getInstance().getStage());
        ApartStatusCollection.getInstance().readAllData();
        apartStatusWindow.showAndWait();
    }

    public void editBlocks(ActionEvent actionEvent) {
        blocksWindow = BlockWindow.getInstance();
        blocksWindow.initOwner(MainWindow.getInstance().getStage());
        BlockCollection.getInstance().readAllData();
        blocksWindow.showAndWait();
    }

     public void clientAll(ActionEvent actionEvent) {
        clientWindow = ClientsWindow.getInstance();
        clientWindow.initOwner(MainWindow.getInstance().getStage());
        ClientCollection.getInstance().readAllData();
        clientWindow.showAndWait();
    }

    public void actionClose(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void editServiceTypes(ActionEvent actionEvent) {
        serviceTypesWindow = ServiceTypeWindow.getInstance();
        serviceTypesWindow.initOwner(MainWindow.getInstance().getStage());
        ServiceTypeCollection.getInstance().readAllData();
        serviceTypesWindow.showAndWait();
    }

    public void editServiceUnits(ActionEvent actionEvent) {
        serviceUnitsWindow = ServiceUnitWindow.getInstance();
        serviceUnitsWindow.initOwner(MainWindow.getInstance().getStage());
        ServiceUnitCollection.getInstance().readAllData();
        serviceUnitsWindow.showAndWait();
    }
}
