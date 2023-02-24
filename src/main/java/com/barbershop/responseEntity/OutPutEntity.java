package com.barbershop.responseEntity;
import static com.barbershop.util.MessageUtil.INTERNALERROR;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class OutPutEntity<T> {
     private HttpStatus status;
     private ArrayList<String> messages = new ArrayList<>();
     private Boolean error = false;
     private  T data;

    public OutPutEntity<T> done(Integer code, String message, T data){
        status = status(code);
        this.messages.add(message);
        this.data = data;
        return  this;
    }

    public OutPutEntity<T> failed(Integer code, String message, T data){
        status = status(code);
        this.messages.add(message);
        this.data = data;
        return  this;
    }


    public OutPutEntity<T> error(){
        status = status(500);
        this.messages.add(INTERNALERROR.getKey());
        this.data = data;
        this.error = true;
        return  this;
    }

    private HttpStatus status(Integer code){
        HttpStatus status = null;
        switch (code){
            case 200 :
            status = HttpStatus.OK;
            break;

            case 201 :
                status = HttpStatus.CREATED;
                break;

            case 404:
                status = HttpStatus.NOT_FOUND;
                break;

            case 400:
                status = HttpStatus.BAD_REQUEST;
                break;

            case 500:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;

        }
        return status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
