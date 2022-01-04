package com.huawei.hms.loginui;

public class Menu {
    String fillings,flavour,topping,size;
    int imageflavor;

    public Menu(String fillings, String flavour, String topping, String size,int imageflavor) {
        this.fillings = fillings;
        this.flavour = flavour;
        this.topping = topping;
        this.size = size;
        this.imageflavor= imageflavor;
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

    public Menu(String flavour, int imageflavor) {
        this.flavour = flavour;
        this.imageflavor = imageflavor;
    }
}
