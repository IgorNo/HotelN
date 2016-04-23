package com.nov.hotel.gui.controllers.impl;


import com.nov.hotel.collections.impl.ServiceTypeCollection;
import com.nov.hotel.collections.impl.ServiceUnitCollection;
import com.nov.hotel.collections.interfaces.ObservableCollection;
import com.nov.hotel.entities.Service;
import com.nov.hotel.entities.ServiceType;
import com.nov.hotel.entities.ServiceUnit;
import com.nov.hotel.gui.controllers.abstr.AbstractEditDialogController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ServicesEditController extends AbstractEditDialogController<Integer, Service> {
    public TextField serviceName;
    public TextField servicePrice;

    public ComboBox serviceType;
    public ComboBox serviceUnit;

    private ObservableCollection<ServiceType> types = ServiceTypeCollection.getInstance().readAllData();
    private ObservableCollection<ServiceUnit> units = ServiceUnitCollection.getInstance().readAllData();

    @Override
    protected void fillField() {
        serviceName.setText(getElem().getName());
        servicePrice.setText(Float.toString(getElem().getPrice()));

        serviceType.setItems(types.getViewList());
        serviceType.getSelectionModel().select(getElem().getServiceType());

        serviceUnit.setItems(units.getViewList());
        serviceUnit.getSelectionModel().select(getElem().getServiceUnit());

    }


    @Override
    protected void saveField() {
        getElem().setName(serviceName.getText());
        getElem().setPrice(Float.parseFloat(servicePrice.getText()));

        ServiceType type = (ServiceType) serviceType.getSelectionModel().getSelectedItem();
        if (type != null) {
            getElem().setServiceType(type);
        }
        ServiceUnit unit = (ServiceUnit) serviceUnit.getSelectionModel().getSelectedItem();
        if (type != null) {
            getElem().setServiceUnit(unit);
        }
    }

    @Override
    protected Service copyElem(Service elem) {
        return new Service(elem);
    }

}
