package com.example.shop4shop;

/**
 * Created by LENOVO on 25-11-2017.
 */

public class User {

    int _id;
    String _name;
    String _username;
    String _password;
    public int get_id() {
        return _id;
    }



    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }



    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
