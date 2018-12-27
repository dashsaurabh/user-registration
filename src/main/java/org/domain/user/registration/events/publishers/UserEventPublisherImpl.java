package org.domain.user.registration.events.publishers;

import org.domain.user.registration.entities.User;
import org.domain.user.registration.events.UserSource;
import org.domain.user.registration.events.models.UserCreatedEvent;
import org.domain.user.registration.events.snapshots.UserEventSnapshot;
import org.domain.user.registration.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@EnableBinding(UserSource.class)
public class UserEventPublisherImpl implements UserEventPublisher {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSource userSource;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleUserCreatedEvent(UserCreatedEvent userCreatedEvent) {
        User createdUser = userRepository.findById(userCreatedEvent.getId()).get();
        System.out.println(createdUser.getVerification().toString());

        UserEventSnapshot userEventSnapshot = new UserEventSnapshot(createdUser.getId(),
                createdUser.getUsername(),
                createdUser.getFirstName(),
                createdUser.getLastName(),
                createdUser.getEmail(),
                createdUser.getStatus(),
                createdUser.getVerification().getToken());

        System.out.println(userEventSnapshot);

        userSource.userChannel().send(MessageBuilder.withPayload(userEventSnapshot).build());
    }
}
