package com.example.embededproject20252.repository;

import com.example.embededproject20252.domain.ArduinoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArduinoDataRepository extends JpaRepository<ArduinoData, Long> {

}
