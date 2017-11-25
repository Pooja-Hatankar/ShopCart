package com.example.shop4shop;

/**
 * Created by LENOVO on 25-11-2017.
 */

public class Product {

    int _id;
    String  _pname;
    int _price;

    public int get_id() {
        return _id;
    }



    public String get_pname() {
        return _pname;
    }

    public void set_pname(String _pname) {
        this._pname = _pname;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }
}
