package com.nov.hotel.entities;


public class ServiceCheckDetail {
    private ServiceCheck serviceCheck;
    private Services services;
    private int quantity;
    private float price;

    public ServiceCheck getServiceCheck() {
        return serviceCheck;
    }

    public void setServiceCheck(ServiceCheck serviceCheck) {
        this.serviceCheck = serviceCheck;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
