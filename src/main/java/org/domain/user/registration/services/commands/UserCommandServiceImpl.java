package org.domain.user.registration.services.commands;

import org.domain.user.registration.domain.commands.UserCreateDTO;
import org.domain.user.registration.domain.commands.UserUpdateDTO;
import org.domain.user.registration.entities.Role;
import org.domain.user.registration.entities.User;
import org.domain.user.registration.entities.UserStatus;
import org.domain.user.registration.entities.UserVerification;
import org.domain.user.registration.exceptions.UserIdNotAvailableException;
import org.domain.user.registration.repositories.UserRepository;
import org.domain.user.registration.repositories.UserVerificationRepository;
import org.domain.user.registration.services.rules.UserBusinessRulesService;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserVerificationRepository userVerificationRepository;

    @Autowired
    private UserBusinessRulesService userBusinessRulesService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String createUser(UserCreateDTO user)  {

        if (userBusinessRulesService.isUserNameAlreadyTaken(user)){
            throw new UserIdNotAvailableException("User Id " + user.getUserName() + " is already taken");
        }
        //Initializing the objects and mapping data
        User newUser = modelMapper.map(user, User.class);
        UserVerification newUserVerification = new UserVerification(UUID.randomUUID(), UUID.randomUUID().toString(), newUser);

        newUser.setId(UUID.randomUUID());
        newUser.setStatus(String.valueOf(UserStatus.HOLD));
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.grantAuthority(Role.ROLE_USER);
        if (user.getUserName().equals("admin"))
            newUser.grantAuthority(Role.ROLE_ADMIN);
        newUser.setVerification(newUserVerification);

        newUser = userRepository.save(newUser.createUser());

        return newUser.getUsername();
    }

    @Override
    @Transactional
    public String updateUser(String userName, UserUpdateDTO updateUser) {

        Optional<User> user = userRepository.findByUsername(userName);

        if (user.isPresent()){
            user.get().setFirstName(updateUser.getFirstName());
            user.get().setLastName(updateUser.getLastName());
            user.get().setEmail(updateUser.getEmail());

            return userRepository.save(user.get()).getUsername();
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    @Transactional
    public String updatePassword(String userName, String newPassword) {
        return null;
    }

    @Override
    @Transactional
    public String deleteUser(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);

        if(user.isPresent()){
            userRepository.delete(user.get());
            return userName;
        }else{
            throw new EntityNotFoundException();
        }
    }
}
