package br.com.netodevel.service;

import br.com.netodevel.model.User;
import br.com.netodevel.repository.UserRepository;
import br.com.netodevel.exceptions.AppException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;
    private final Validator validator;

    @Inject
    public UserService(UserRepository repository, Validator validator) {
        this.userRepository = repository;
        this.validator = validator;
    }

    public List<User> listAll() {
        return userRepository.findAll().list();
    }

    @Transactional
    public User create(User user) {
        var violations = validator.validate(user);
        if (violations.isEmpty()) {
            userRepository.persist(user);
            return userRepository.findById(user.id);
        }

        String allViolations = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        throw new AppException(400, allViolations);
    }

    public User findById(Long id) {
        return userRepository.findByIdOptional(id).orElseThrow(() -> {
            throw new AppException(204, "user_id ".concat(String.valueOf(id)).concat(" not found"));
        });
    }

    @Transactional
    public User update(Long id, User userToUpdate) {
        var user = this.findById(id);

        userToUpdate.id = user.id;
        userRepository.persist(userToUpdate);
        return user;
    }

    @Transactional
    public String delete(long id) {
        this.findById(id);
        userRepository.deleteById(id);
        return "deleted";
    }

}
