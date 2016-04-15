package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Customer;
import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Client implements Entity<Long, Client>, Customer, Comparable<Client> {

    private long id;
    private ObjectProperty<LocalDateTime> regDate = new SimpleObjectProperty<>();

    private StringProperty name = new SimpleStringProperty("");
    private StringProperty surname = new SimpleStringProperty("");
    private StringProperty patronymic = new SimpleStringProperty("");

    private ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();
    private BooleanProperty sex  = new SimpleBooleanProperty();
    private ObjectProperty<Country> citizenship = new SimpleObjectProperty<>();

    private ObjectProperty<DocumType> docType = new SimpleObjectProperty<>();
    private StringProperty docSeries = new SimpleStringProperty();
    private StringProperty docNumber = new SimpleStringProperty();
    private ObjectProperty<LocalDate> docDate = new SimpleObjectProperty<>();
    private StringProperty docIssue = new SimpleStringProperty();

    private ObjectProperty<Region> regionAddress = new SimpleObjectProperty<>();
    private StringProperty address = new SimpleStringProperty();

    private ObjectProperty<ClientType> type = new SimpleObjectProperty<>();
    private FloatProperty discount = new SimpleFloatProperty();


    public Client() {
        regDate.set(LocalDateTime.now().now());
        setDiscount(1.0f);
        setSex(true);
    }

    public Client(DocumType docType, ClientType clientType, Country country, Region region) {
        this();
//        setDocDate(docDate);
        setDocType(docType);
        setType(clientType);
        setCitizenship(country);
        setRegionAddress(region);
    }

    public Client(Client elem) {
        this();
        assign(elem);
    }

    @Override
    public void assign(Client elem) {
        setId(elem.getId());
        setName(elem.getName());
        setSurname(elem.getSurname());
        setPatronymic(elem.getPatronymic());
        setSex(elem.getSex());
        setDocType(elem.getDocType());
        setDocSeries(elem.getDocSeries());
        setDocNumber(elem.getDocNumber());
        setDocDate(elem.getDocDate());
        setDocIssue(elem.getDocIssue());
        setBirthday(elem.getBirthday());
        setDiscount(elem.getDiscount());
        setAddress(elem.getAddress());
        setType(elem.getType());
        setCitizenship(elem.getCitizenship());
        setRegionAddress(elem.getRegionAddress());
    }

    @Override
    public boolean validate() {
        return true;
//                getSurname() != null && !getSurname().trim().isEmpty() && getName() != null && !getName().trim().isEmpty() &&
//                getDocSeries() != null && !getDocSeries().trim().isEmpty() && getDocNumber() != null && getDocNumber().trim().isEmpty() &&
//                getDocIssue() != null && getDocIssue().trim().isEmpty() && getAddress() != null && getAddress().trim().isEmpty() &&
//                getDocDate() != null && getDocDate().isBefore(LocalDate.now()) && getBirthday() != null && getBirthday().isBefore(LocalDate.now()) &&
//                getDiscount() > 0 && getDiscount() <= 1;
    }

    public boolean getSex() {
        return sex.get();
    }

    public BooleanProperty sexProperty() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex.set(sex);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegDate() {
        return regDate.get();
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate.set(regDate);
    }

    public ObjectProperty<LocalDateTime> regDateProperty() {
        return regDate;
    }

    public StringProperty fullNameProperty(){
        StringProperty fullName = new SimpleStringProperty();
        fullName.bind(surname.concat(" " + name.concat(" " + patronymic.get()).get()));
        return fullName;
    }

    public StringProperty passportProperty(){
        StringProperty passport = new SimpleStringProperty();
        passport.bind(docSeries.concat(" " + docNumber.get()));
        return passport;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public String getDocSeries() {
        return docSeries.get();
    }

    public StringProperty docSeriesProperty() {
        return docSeries;
    }

    public void setDocSeries(String docSeries) {
        this.docSeries.set(docSeries);
    }

    public String getDocNumber() {
        return docNumber.get();
    }

    public StringProperty docNumberProperty() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber.set(docNumber);
    }

    public LocalDate getDocDate() {
        return docDate.get();
    }

    public ObjectProperty<LocalDate> docDateProperty() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate.set(docDate);
    }

    public String getDocIssue() {
        return docIssue.get();
    }

    public StringProperty docIssueProperty() {
        return docIssue;
    }

    public void setDocIssue(String docIssue) {
        this.docIssue.set(docIssue);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public float getDiscount() {
        return discount.get();
    }

    public FloatProperty discountProperty() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount.set(discount);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public DocumType getDocType() {
        return docType.get();
    }

    public ObjectProperty<DocumType> docTypeProperty() {
        return docType;
    }

    public void setDocType(DocumType docType) {
        this.docType.set(docType);
    }

    public ClientType getType() {
        return type.get();
    }

    public ObjectProperty<ClientType> typeProperty() {
        return type;
    }

    public void setType(ClientType type) {
        this.type.set(type);
    }

    public Country getCitizenship() {
        return citizenship.get();
    }

    public ObjectProperty<Country> citizenshipProperty() {
        return citizenship;
    }

    public void setCitizenship(Country citizenship) {
        this.citizenship.set(citizenship);
    }

    public Region getRegionAddress() {
        return regionAddress.get();
    }

    public ObjectProperty<Region> regionAddressProperty() {
        return regionAddress;
    }

    public void setRegionAddress(Region regionAddress) {
        this.regionAddress.set(regionAddress);
    }

    @Override
    public int compareTo(Client o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;

    }
}
