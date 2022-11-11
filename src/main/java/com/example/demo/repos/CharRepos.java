package com.example.demo.repos;

import com.example.demo.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharRepos extends JpaRepository<Characteristic,String> {
    Characteristic findByValue(String value);
}
