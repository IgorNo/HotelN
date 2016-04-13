package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Customer;
import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Invoice implements Entity<Long, Invoice>, Comparable<Invoice> {

    private Long id = new Long(0);
    private ObjectProperty<LocalDateTime> createTime = new SimpleObjectProperty<>();
    private StringProperty invoiceN = new SimpleStringProperty();
    private ObjectProperty<LocalDate> invoiceDate = new SimpleObjectProperty<>();
    private FloatProperty amount = new SimpleFloatProperty();
    private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
    private ObjectProperty<User> user = new SimpleObjectProperty<>();


    @Override
    public void assign(Invoice elem) {
        setId(elem.getId());
        setCreateTime(elem.getCreateTime());
        setInvoiceDate(elem.getInvoiceDate());
        setAmount(elem.getAmount());
        setCustomer(elem.getCustomer());
        setUser(elem.getUser());
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime.get();
    }

    public ObjectProperty<LocalDateTime> createTimeProperty() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime.set(createTime);
    }

    public String getInvoiceN() {
        return invoiceN.get();
    }

    public StringProperty invoiceNProperty() {
        return invoiceN;
    }

    public void setInvoiceN(String invoiceN) {
        this.invoiceN.set(invoiceN);
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate.get();
    }

    public ObjectProperty<LocalDate> invoiceDateProperty() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate.set(invoiceDate);
    }

    public float getAmount() {
        return amount.get();
    }

    public FloatProperty amountProperty() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount.set(amount);
    }

    public Customer getCustomer() {
        return customer.get();
    }

    public ObjectProperty<Customer> customerProperty() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer.set(customer);
    }

    public User getUser() {
        return user.get();
    }

    public ObjectProperty<User> userProperty() {
        return user;
    }

    public void setUser(User user) {
        this.user.set(user);
    }

    @Override
    public int compareTo(Invoice o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;
    }
}
