package com.github.bcanvural.basicregistration;

import com.github.bcanvural.basicregistration.exceptions.EmailExistsException;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;
}
