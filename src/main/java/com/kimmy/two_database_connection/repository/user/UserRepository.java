package com.kimmy.two_database_connection.repository.user;

import com.kimmy.two_database_connection.model.user.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends R2dbcRepository<User, Integer> {
}
