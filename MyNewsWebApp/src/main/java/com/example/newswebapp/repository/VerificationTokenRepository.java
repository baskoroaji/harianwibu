package com.example.newswebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newswebapp.Model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

}
