package com.example.tonto.demoretrofit.networks.getalltasks;

import java.util.Date;

/**
 * Created by tonto on 5/29/2017.
 */

public class GetAllTasksResponse {
    private String id;
    private String local_id;
    private String color;
    private String name;
    private boolean done;
    private float payment_per_hour;
    private String due_date;

    public GetAllTasksResponse(String id, String local_id, String color, String name, boolean done, float payment_per_hour, String due_date) {
        this.id = id;
        this.local_id = local_id;
        this.color = color;
        this.name = name;
        this.done = done;
        this.payment_per_hour = payment_per_hour;
        this.due_date = due_date;
    }

    public String getId() {
        return id;
    }

    public String getLocal_id() {
        return local_id;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public float getPayment_per_hour() {
        return payment_per_hour;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setPayment_per_hour(float payment_per_hour) {
        this.payment_per_hour = payment_per_hour;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
