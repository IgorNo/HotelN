package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.collections.impl.*;
import com.nov.hotel.collections.interfaces.ObservableCollection;
import com.nov.hotel.entities.*;
import com.nov.hotel.gui.controllers.abstr.AbstractController;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.windows.DialogManager;
import com.nov.hotel.gui.windows.impl.*;
import com.nov.hotel.main.Start;
import com.nov.hotel.services.impl.GetSelectedRoom;
import javafx.beans.binding.FloatBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;

import java.net.URL;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class InvoiceAddController extends AbstractController implements Initializable {

    private static final String ROOM_NOT_SELECTED = "????";
    private static final ServiceUnit BED_DAY = new ServiceUnit();
    private static final ServiceUnit BED_HOUR = new ServiceUnit();


    private Invoice invoice = new Invoice();
    private ResourceBundle rBundle;
    private ValidationSupport validationSupport = new ValidationSupport();

    private AbstractWindow clientWindow = ClientsWindow.getInstance();

    private ObservableCollection<Block> blocks = BlockCollection.getInstance().readAllData();
    private ObservableCollection<ApartType> types = ApartTypeCollection.getInstance().readAllData();
    private ObservableCollection<Price> prices = PriceCollection.getInstance().readAllData();

    private ObservableList<ServiceInvoice> check = FXCollections.observableArrayList();
    private ObservableList<Apartment> selectedRooms = FXCollections.observableArrayList();

    public TextField txtInvoiceNumber;
    public DatePicker datepInvoice;
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
    public TextField txtHours;

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
    
    public TableView tableClients;
    public TableColumn columnName;
    public TableColumn columnSex;
    public TableColumn columnBirthday;
    public TableColumn columnPassport;
    public TableColumn columnCountry;

    public TextField txtRoom;
    public TextField txtPersons;
    public TextField txtBeds;

    public Tab tabServices;
    public Tab tabInvoice;
    public Tab tabPayments;

    public AnchorPane anchorpSelRoom;

    private FloatProperty priceDay = new SimpleFloatProperty();
    private FloatProperty priceHour = new SimpleFloatProperty();
    private FloatProperty priceSlot = new SimpleFloatProperty();
    private IntegerProperty days = new SimpleIntegerProperty();
    private IntegerProperty hours = new SimpleIntegerProperty();
    private IntegerProperty mBedsN = new SimpleIntegerProperty();
    private IntegerProperty eBedsN = new SimpleIntegerProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.rBundle = resources;
        validationSupport.setValidationDecorator(new StyleClassValidationDecoration());
        BED_DAY.setName(rBundle.getString("unit.bed.day"));
        BED_HOUR.setName(rBundle.getString("unit.bed.hour"));
        fillField();
        initListeners();
        fillFieldAccomodation();
        initListenersAccomodation();
        fillFieldRegistration();
    }

    private void fillField() {
        txtInvoiceNumber.setText(invoice.getInvoiceN());
        validationSupport.registerValidator(txtInvoiceNumber, Validator.createEmptyValidator(rBundle.getString("prompt.text")));
        datepInvoice.setValue(invoice.getInvoiceDate());
        validationSupport.registerValidator(datepInvoice, Validator.createEmptyValidator(rBundle.getString("prompt.date")));

        rbtIndivid.setSelected(true);
        if (invoice.getCustomer() != null) {
            String className = invoice.getCustomer().getClass().getSimpleName();
            if (className.equals("Office")) {
                rbtnOffice.setSelected(true);
                rbtIndivid.setSelected(false);
            }
            txtCustomer.setText(invoice.getCustomer().fullNameProperty().getValue());
            anchorpSelRoom.setDisable(false);
        } else {
            tabInvoice.setDisable(true);
            tabPayments.setDisable(true);
            tabRegistration.setDisable(true);
            tabServices.setDisable(true);
        }
        validationSupport.registerValidator(txtCustomer, Validator.createEmptyValidator(rBundle.getString("prompt.text")));

        listvRoom.setItems(invoice.getAllocations());
    }

    protected void fillFieldAccomodation() {

        comboBlock.setItems(blocks.getViewList());
        comboRoomType.setItems(types.getViewList());
        comboPriceType.setItems(prices.getViewList());

    }

    private void fillFieldRegistration() {

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
    }

    private void initListeners() {
        // Listen for selection changes and show the person details when changed.
        listvRoom.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> changeAllocation((Allocation) newValue));

        invoice.getAllocations().addListener(new ListChangeListener<Allocation>() {
            @Override
            public void onChanged(Change<? extends Allocation> c) {
                if (invoice.getAllocations().size() > 0) {
                    tabAccomodation.setDisable(false);
                }
            }
        });
    }

    protected void initListenersAccomodation() {

        comboSelectedRoom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            }
        });

        comboPriceType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                changePriceValues((Price)comboPriceType.getSelectionModel().getSelectedItem(), (ApartType)comboRoomType.getSelectionModel().getSelectedItem());
            }
        });

        comboRoomType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                changePriceValues((Price)comboPriceType.getSelectionModel().getSelectedItem(), (ApartType)comboRoomType.getSelectionModel().getSelectedItem());
            }
        });

        txtDays.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    days.set(Integer.parseInt(newValue));
                    LocalTime time = LocalTime.parse(txtStartTime.getText());
                    if (time.getHour() + hours.get() > 24)
                        datepEnd.setValue(datepStart.getValue().plusDays(days.get()+1));
                    else
                        datepEnd.setValue(datepStart.getValue().plusDays(days.get()));
                }
            }
        });

        txtMasterBadsN.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals(""))
                    mBedsN.setValue(Integer.parseInt(newValue));
            }
        });

        txtExtraBadsN.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals(""))
                    eBedsN.setValue(Integer.parseInt(newValue));
            }
        });

        txtHours.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    hours.set(Integer.parseInt(newValue));
                    LocalTime time = LocalTime.parse(txtStartTime.getText());
                    txtEndTime.setText(time.plusHours(hours.getValue()).toString());
//                    if (time.getHour() + hours.get() > 24)
//                        datepEnd.setValue(datepEnd.getValue().plusDays(1));
                }
            }
        });

        txtStartTime.textProperty().addListener(
                (observable, oldValue, newValue) -> setStartEnd()
        );

        txtEndTime.textProperty().addListener(
                (observable, oldValue, newValue) -> setStartEnd()
        );

        datepStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setStartEnd();
            }
        });

        datepEnd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setStartEnd();
            }
        });

        txtPriceDay.textProperty().bind(priceDay.asString());
        txtPriceHour.textProperty().bind(priceHour.asString());
        txtPriceSlot.textProperty().bind(priceSlot.asString());
        txtAmount.textProperty().bind(amount());
    }

    protected void saveField() {

    }

    public void actionClose(ActionEvent actionEvent) {
        closeWindow();
    }

    public void selectCustomer(ActionEvent actionEvent) {
        if (rbtIndivid.isSelected()) {
            Client selectedClient = getClient();
            if (selectedClient != null) {
                invoice.setCustomer(selectedClient);
                txtCustomer.setText(selectedClient.fullNameProperty().getValue());
                anchorpSelRoom.setDisable(false);
            }
        }
    }

    public void addRoom(ActionEvent actionEvent) {
        Allocation allocation = new Allocation();
        Apartment room = new Apartment();
        room.setRoomNumber(ROOM_NOT_SELECTED);
        allocation.setRoom(room);
        LocalDate date = LocalDate.now();
        allocation.setStartDate(date);
        allocation.setStartTime(Start.SETTINGS.getStartTime());
        allocation.setEndDate(date.plusDays(1));
        allocation.setEndTime(Start.SETTINGS.getEndTime());
        LocalTime nowTime = LocalTime.now();
        nowTime = nowTime.withNano(0).withSecond(0).withMinute(0);
        allocation.setArrivalDate(LocalDateTime.of(date, nowTime));
        invoice.getAllocationCollection().add(allocation);
        listvRoom.getSelectionModel().select(allocation);
        selectedRooms.clear();
    }

    public void deleteRoom(ActionEvent actionEvent) {
        invoice.getAllocations().remove(listvRoom.getSelectionModel().getSelectedItem());
    }

    public void resetBlock(ActionEvent actionEvent) {
        comboBlock.getSelectionModel().select(null);
    }

    public void resetRoomType(ActionEvent actionEvent) {
        comboRoomType.getSelectionModel().select(null);
    }

    public void accomMap(ActionEvent actionEvent) {

    }

    public void selectRooms(ActionEvent actionEvent) {
        RoomQuery query = new RoomQuery();
        query.setBlock((Block) comboBlock.getSelectionModel().getSelectedItem());
        query.setType((ApartType) comboRoomType.getSelectionModel().getSelectedItem());
        if (!txtLevel.getText().isEmpty()) query.setLevel(Integer.parseInt(txtLevel.getText()));
        if (!txtMasterBadsN.getText().isEmpty()) query.setmBedsN(Integer.parseInt(txtLevel.getText()));
        if (!txtExtraBadsN.getText().isEmpty()) query.seteBedN(Integer.parseInt(txtLevel.getText()));
        query.setDtStart(LocalDateTime.of(datepStart.getValue(), LocalTime.parse(txtStartTime.getText())));
        query.setDtEnd(LocalDateTime.of(datepEnd.getValue(), LocalTime.parse(txtEndTime.getText())));
        selectedRooms.clear();
        selectedRooms.addAll(GetSelectedRoom.getInstance().get(query));
        comboSelectedRoom.setItems(selectedRooms);
    }

    public void saveRoom(ActionEvent actionEvent) {
        Allocation allocation = (Allocation) listvRoom.getSelectionModel().getSelectedItem();
        allocation.setRoom((Apartment) comboSelectedRoom.getSelectionModel().getSelectedItem());
        listvRoom.setItems(invoice.getAllocations());
        Service serviceDay = new Service();

    }

    public void actionSave(ActionEvent actionEvent) {
        try {
            saveField();
            closeWindow();
        } catch (NumberFormatException e){
            DialogManager.showInfoDialog(rBundle.getString("message.error"), rBundle.getString("message.invalid.data"));
            return;
        }
    }


    private void setStartEnd() {
        try {
            LocalTime startTime = LocalTime.parse(txtStartTime.getText());
            LocalTime endTime = LocalTime.parse(txtEndTime.getText());
            if (datepStart.getValue() != null && datepEnd != null) {
                LocalDateTime dtStart = LocalDateTime.of(datepStart.getValue(), startTime);
                LocalDateTime dtEnd = LocalDateTime.of(datepEnd.getValue(), endTime);
                Duration duration = Duration.between(dtStart, dtEnd);
                int secondInDay = 86400;
                int secondInHour = 3600;
                days.set((int) (duration.getSeconds() / secondInDay));
                hours.set((int) ((duration.getSeconds() - days .get()* secondInDay) / secondInHour));
                txtDays.setText(Integer.toString(days.get()));
                txtHours.setText(Integer.toString(hours.get()));
            }
        } catch (DateTimeParseException dtp){ }
    }

    private ObservableValue<? extends String> amount() {

        NumberBinding amount = new FloatBinding() {
            {super.bind(priceDay,priceHour,priceSlot,days,hours,mBedsN,eBedsN);}
            @Override
            protected float computeValue() {
                return (priceDay.get()*days.get() + priceHour.get()*hours.get())*mBedsN.get() +
                        (priceSlot.get()*days.get() + priceHour.get()*hours.get())*eBedsN.get();
            }
        };
        return amount.asString();
    }

    private void changePriceValues(Price price, ApartType roomType) {
        float priceValue;
        if (price == null) priceValue = 1;
        else priceValue = price.getPrice();
        if (roomType != null) {
            priceDay.set(priceValue*roomType.getPriceDay());
            priceHour.set(priceValue*roomType.getPriceHour());
            priceSlot.set(priceValue*roomType.getPriceSlot());
        } else {
            priceDay.set(0);
            priceHour.set(0);
            priceSlot.set(0);
        }
    }

    private void changeAllocation(Allocation alloc){
        if (alloc != null) {
            datepStart.setValue(alloc.getStartDate());
            txtStartTime.setText(alloc.getStartTime().toString());
            datepEnd.setValue(alloc.getEndDate());
            txtEndTime.setText(alloc.getEndTime().toString());
            Period nDays = alloc.getStartDate().until(alloc.getEndDate());
            txtDays.setText(Integer.toString(nDays.getDays()));
            days.set(nDays.getDays());
            int nHours = alloc.getEndTime().getHour() - Start.SETTINGS.getEndTime().getHour();
            hours.set(nHours);

            txtArrivalTime.setText(alloc.getArrivalDate().toString());
            comboPriceType.getSelectionModel().select(alloc.getPriceType());

            comboBlock.getSelectionModel().select(alloc.getRoom().getBlock());
            txtLevel.setText(Integer.toString(alloc.getRoom().getLevelNumber()));
            comboRoomType.getSelectionModel().select(alloc.getRoom().getType());

            changePriceValues((Price) comboPriceType.getSelectionModel().getSelectedItem(), (ApartType) comboRoomType.getSelectionModel().getSelectedItem());

            if (alloc.getRoom().getRoomNumber().equals(ROOM_NOT_SELECTED)) {
                txtMasterBadsN.setText("1");
                txtExtraBadsN.setText("0");
            } else {
                txtMasterBadsN.setText(Integer.toString(alloc.getMasterBedsN()));
                txtExtraBadsN.setText(Integer.toString(alloc.getExtraBedsN()));
            }
            tabRegistration.setDisable(false);
            ObservableList<AllocClient> clients = alloc.getAllocClients();
            tableClients.setItems(clients);
        } else {
            tabAccomodation.setDisable(true);
            tabRegistration.setDisable(true);
        }

    }

    public void addClient(ActionEvent actionEvent) {
        Client selectedClient = getClient();
        if (selectedClient != null) {
            Allocation currAllocation = (Allocation) listvRoom.getSelectionModel().getSelectedItem();
            currAllocation.getAllocClientCollection().add(new AllocClient(currAllocation.getId(), selectedClient));
        }
    }

    private Client getClient() {
        clientWindow.initOwner(SettlingWindow.getInstance().getStage());
        ClientCollection.getInstance().readAllData();
        AbstractTableController<Client> controller = clientWindow.getLoader().getController();
        clientWindow.showAndWait();
        return controller.getSelectedElem();
    }

    public void delClient(ActionEvent actionEvent) {
        Allocation currAllocation = (Allocation) listvRoom.getSelectionModel().getSelectedItem();
        currAllocation.getAllocClientCollection().delete((AllocClient)tableClients.getSelectionModel().getSelectedItem());
    }

    public void addOwner(ActionEvent actionEvent) {
        String className = invoice.getCustomer().getClass().getSimpleName();
        if (className.equals("Client")) {
            Client client = (Client)invoice.getCustomer();
            Allocation currAllocation = (Allocation) listvRoom.getSelectionModel().getSelectedItem();
            currAllocation.getAllocClients().add(new AllocClient(currAllocation.getId(), client));
        }
    }

    public void addService(ActionEvent actionEvent) {

    }

    public void delService(ActionEvent actionEvent) {

    }
}
