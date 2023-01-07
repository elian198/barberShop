package com.barbershop.DTO;

import java.time.LocalTime;

public class Time {

    private LocalTime time;

    public Time(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Time{" +
                "time=" + time +
                '}';
    }
}
