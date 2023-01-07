package com.barbershop.security.payload;

public class LoginPayload {

    private String first_name ;
    private String password;

    public LoginPayload(String first_name, String password) {
        this.first_name = first_name;
        this.password = password;
    }

    public LoginPayload() { }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
