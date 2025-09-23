package com.example.embededproject20252.repository;

import com.example.embededproject20252.domain.SerialNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialNumRepository extends JpaRepository<SerialNum, Long> {

}
