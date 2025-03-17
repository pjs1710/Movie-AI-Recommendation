package com.movie_ai_recommend.movie_ai_recommend.repository;

import com.movie_ai_recommend.movie_ai_recommend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    Optional<User> findByEmail(String email);
}
