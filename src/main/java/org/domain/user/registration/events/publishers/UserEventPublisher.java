package org.domain.user.registration.events.publishers;

import org.domain.user.registration.events.models.UserCreatedEvent;

public interface UserEventPublisher {
    public void handleUserCreatedEvent(UserCreatedEvent userCreatedEvent);
}
