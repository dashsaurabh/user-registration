package org.domain.user.registration.services.queries;

import org.domain.user.registration.domain.queries.UserQueryDTO;

public interface UserQueryService {

    UserQueryDTO findByUserName(String userName);
}
