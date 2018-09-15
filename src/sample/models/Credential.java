package sample.models;

import java.util.Date;

public class Credential {
    private String id; //student ID
    private String pin; //one-time PIN that is invalid after 20 minutes
    private Date timeRegister; //moment a user registers with the system

    public Date getTimeRegister() {
        return timeRegister;
    }

    public String getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }

    public Credential(String id, String pin, Date timeRegister) {
        this.id = id;
        this.pin = pin;
        this.timeRegister = timeRegister;
    }
}
