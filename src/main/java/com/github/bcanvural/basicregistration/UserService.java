package com.github.bcanvural.basicregistration;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.bcanvural.basicregistration.exceptions.EmailExistsException;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    @Autowired
    public UserService(final UserRepository repository) {
        this.repository = repository;
    }


    @Transactional
    @Override
    public User registerNewUserAccount(final UserDto accountDto) throws EmailExistsException {
        if (emailExists(accountDto)) {
            throw new EmailExistsException("There's an account with that email address: " + accountDto.getEmail());
        }
        return createNewUser(accountDto);
    }

    private User createNewUser(final UserDto accountDto) {
        User user = new User();
        user.setEmail(accountDto.getEmail());
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        return repository.save(user);
    }

    private boolean emailExists(final UserDto accountDto) {
        return repository.findByEmail(accountDto.getEmail()) != null;
    }
}
