package org.domain.user.registration.events.snapshots;

import java.util.UUID;

public class UserEventSnapshot {

    private final UUID id;

    private final String userName;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String state;

    private final String verificationToken;

    public UserEventSnapshot(UUID id, String userName, String firstName, String lastName, String email, String state, String verificationToken) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.state = state;
        this.verificationToken = verificationToken;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getState() {
        return state;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    @Override
    public String toString() {
        return "UserEventSnapshot{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", verificationToken='" + verificationToken + '\'' +
                '}';
    }
}
