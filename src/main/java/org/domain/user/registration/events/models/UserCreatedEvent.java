package org.domain.user.registration.events.models;

import java.util.UUID;

public class UserCreatedEvent {
    private final UUID id;

    public UserCreatedEvent(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
