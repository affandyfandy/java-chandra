package com.assignment3.assignment3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment3.assignment3.entity.ApiKey;

public interface ApiRepository extends JpaRepository<ApiKey, Long> {
    ApiKey findByKey(String key);
}
