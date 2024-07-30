package com.assignment3.assignment3.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "api_key")
public class ApiKey {

    @Id
    private Long id;

    @Column(name = "key_api", nullable = false, length = 255)
    private String key;

    private String username;
    private LocalDateTime lastUsed;

}
