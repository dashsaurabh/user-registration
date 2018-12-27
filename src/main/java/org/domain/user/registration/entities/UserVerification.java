package org.domain.user.registration.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_verification")
public class UserVerification {

    @Id
    private UUID id;

    private String token;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public UserVerification() {
    }

    public UserVerification(UUID id, String token, User user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
