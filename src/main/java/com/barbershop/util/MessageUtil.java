package com.barbershop.util;

public enum MessageUtil {

    OK("Proceso exitoso ", 200),
    CREATED("creacion exitosa ", 201),
    UPDATED("Aactualizacion exitosa ", 200),
    DELETED("Eliminacion exitosa ", 200),
    NOTFOUND("No existen registros ", 404),
    INTERNALERROR("Error en el servidor ", 500),
    CONFLICT("Hubo un error en su peticion ", 409),
    BADREQUEST("Hubo un error en su peticion ", 400);

    private  String key;
    private  int code;

    MessageUtil(String key, int code) {
        this.key = key;
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
