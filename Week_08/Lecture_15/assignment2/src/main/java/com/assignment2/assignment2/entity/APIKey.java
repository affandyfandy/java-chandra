package com.assignment2.assignment2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "api_key")
public class APIKey {

    @Id
    private Long id;

    @Column(name = "key_api", nullable = false, length = 255)
    private String key;
}