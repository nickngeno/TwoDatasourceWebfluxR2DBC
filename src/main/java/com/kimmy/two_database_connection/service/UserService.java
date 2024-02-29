package com.kimmy.two_database_connection.service;

import com.kimmy.two_database_connection.model.user.User;
import com.kimmy.two_database_connection.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> addUser(User user) {
        logger.info("Saving a user...");
        return userRepository.save(user);
    }
    public Mono<User> findUser(int id) {
        return userRepository.findById(id)
                .defaultIfEmpty(new User());
    }

    public Mono<User> updateUser(User user) {
        return userRepository.findById(user.getId())
                .flatMap(u -> {
                    u.setName(user.getName());
                    return userRepository.save(u);
                }).switchIfEmpty(Mono.defer(() -> {
                    logger.warn("User with id: {} not found!",user.getId());
                    return Mono.just(new User());
                }));
    }

    public Mono<Void> deleteUser(int id) {
        return userRepository.deleteById(id);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }
}
