package com.huawei.hms.loginui;

public class Menu {
    String fillings,flavour,topping,size, date;
    int imageflavor;
    int quantity;

    public Menu() {
    }

    public Menu(String fillings, String flavour, String topping, String size, String date, int imageflavor, int quantity) {
        this.fillings = fillings;
        this.flavour = flavour;
        this.topping = topping;
        this.size = size;
        this.imageflavor= imageflavor;
        this.quantity = quantity;
        this.date = date;
    }

    public String getFillings() {
        return fillings;
    }

    public void setFillings(String fillings) {
        this.fillings = fillings;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getImageflavor() {
        return imageflavor;
    }

    public void setImageflavor(int imageflavor) {
        this.imageflavor = imageflavor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Menu(String flavour, int imageflavor) {

        this.flavour = flavour;
        this.imageflavor = imageflavor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Menu(String fillings, String flavour, String topping, String size, String date, int quantity) {
        this.fillings = fillings;
        this.flavour = flavour;
        this.topping = topping;
        this.size = size;
        this.quantity = quantity;
        this.date = date;
    }
}
