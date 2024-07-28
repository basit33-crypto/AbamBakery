package com.example.abambakery;
public class cake {
    String name;
    int img;
    String cat;
    String price;

    public cake(String name,String cat, String price,int img) {
        this.name = name;
        this.img = img;
        this.cat=cat;
        this.price=price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCat() {
        return cat;
    }
    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
