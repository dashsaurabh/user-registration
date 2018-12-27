package org.domain.user.registration.services.queries;

import org.domain.user.registration.domain.queries.UserQueryDTO;
import org.domain.user.registration.entities.User;
import org.domain.user.registration.entities.UserStatus;
import org.domain.user.registration.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserQueryDTO findByUserName(String userName) {

        Optional<User> user = userRepository.findByUsername(userName);

        if(user.isPresent()){
            return modelMapper.map(user.get(), UserQueryDTO.class);
        }else{
            throw new EntityNotFoundException();
        }

    }

}
