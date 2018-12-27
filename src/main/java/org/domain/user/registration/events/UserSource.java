package org.domain.user.registration.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserSource {

    @Output("userChannel")
    MessageChannel userChannel();
}
