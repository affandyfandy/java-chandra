package com.assignment2.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment2.assignment2.entity.APIKey;

public interface ApiRepository extends JpaRepository<APIKey, Long> {
    APIKey findByKey(String key);
}
