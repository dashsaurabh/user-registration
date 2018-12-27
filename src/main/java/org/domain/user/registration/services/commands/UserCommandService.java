package org.domain.user.registration.services.commands;

import org.domain.user.registration.domain.commands.UserCreateDTO;
import org.domain.user.registration.domain.commands.UserUpdateDTO;
import org.domain.user.registration.domain.queries.UserQueryDTO;

public interface UserCommandService {
    String createUser(UserCreateDTO newUser);
    String updateUser(String userName, UserUpdateDTO updateUser);
    String updatePassword(String userName, String newPassword);
    String deleteUser(String userName);
}
