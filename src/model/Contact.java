package model;

/**
 * This class is a model for a Contact
 * */
public class Contact {

    public int contactID;
    public String contactName;
    public String contactEmail;

    /**
     * This creates a new Contact object
     *
     * @param contactEmail is the contact email
     * @param contactID is the contact id
     * @param contactName is the contact name
     * */
    public Contact(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * Returns the contact id
     * */
    public int getContactID() {
        return contactID;
    }

    /**
     * Sets the contact id
     *
     * @param contactID is the contact id
     * */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Returns the contact name
     * */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the contact name
     *
     * @param contactName is the contact name
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Returns the contact email
     * */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the contact email
     *
     * @param contactEmail is the contact email
     * */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
