package com.example.embededproject20252.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class ArduinoData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "serial_num", nullable = false)
  private String serialNum;

  @Column(name = "motion", nullable = false)
  private Integer motion;

  public ArduinoData(String serialNum, Integer motion){
    this.serialNum = serialNum;
    this.motion = motion;
  }
}
