package com.app.users.Models;

public class UserForCreationDTO {
    private String firstName;
    private String lastName;

    public UserForCreationDTO() {
    }

    public UserForCreationDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
