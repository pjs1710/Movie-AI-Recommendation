package com.movie_ai_recommend.movie_ai_recommend.repository;

import com.movie_ai_recommend.movie_ai_recommend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
