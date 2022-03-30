package model;

import java.time.LocalDateTime;

/**
 * This class is a model for an Appointment
 *
 * @author Dallin Reeves
 * */
public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    public int contactId;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public int customerId;
    public int userId;

    /**
     * This creates a new Appointment object
     *
     * @param appointmentId is the appointment id
     * @param contactId is the contact id
     * @param customerId is the customer id
     * @param description is the description
     * @param endTime is the end time
     * @param location is the location
     * @param startTime is the start time
     * @param title is the title
     * @param type is the type
     * @param userId is the user id
     * */
    public Appointment(int appointmentId, String title, String description, String location, int contactId,
                       String type, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactId = contactId;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     * Returns the appointment id
     * */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the appointment id
     *
     * @param appointmentId is the appointment id
     * */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Returns the title
     * */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title
     *
     * @param title is the title
     * */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     *
     * @param description is the description
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the location
     * */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location
     *
     * @param location is the location
     * */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the contact id
     * */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the contact id
     *
     * @param contactId is the contact id
     * */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Returns type
     * */
    public String getType() {
        return type;
    }

    /**
     * Sets the type
     *
     * @param type is the type
     * */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns start time
     * */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets start time
     *
     * @param startTime is start time
     * */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns end time
     * */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time
     *
     * @param endTime is the end time
     * */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns customer id
     * */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets customer id
     *
     * @param customerId is the customer id
     * */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns user id
     * */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user id
     *
     * @param userId is the user id
     * */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
