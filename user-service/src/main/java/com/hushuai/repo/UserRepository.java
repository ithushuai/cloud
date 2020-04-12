package com.hushuai.repo;

import com.hushuai.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by it_hushuai
 * 2020/4/9 22:34
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
