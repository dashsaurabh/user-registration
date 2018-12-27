package org.domain.user.registration.services.rules;

import org.domain.user.registration.domain.commands.UserCreateDTO;
import org.domain.user.registration.domain.commands.UserUpdateDTO;
import org.domain.user.registration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessRulesServiceImpl implements UserBusinessRulesService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isUserNameAlreadyTaken(UserCreateDTO userCreateDTO) {
        if (userRepository.findByUsername(userCreateDTO.getUserName()).isPresent()){
            return true;
        }
        return false;
    }

}
