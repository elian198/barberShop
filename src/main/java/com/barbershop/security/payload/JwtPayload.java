package com.barbershop.security.payload;

public class JwtPayload {

    private String jwt;

    public JwtPayload(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
