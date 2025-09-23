package com.example.embededproject20252.repository;

import com.example.embededproject20252.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findBySerialNum(String serialNum);
}

