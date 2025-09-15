package com.example.embededproject20252;

import com.example.embededproject20252.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
