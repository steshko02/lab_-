package com.example.demo.repos;

import com.example.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepos extends JpaRepository<Subject,String> {
    Optional<Subject> findByValue(String subject);
}
