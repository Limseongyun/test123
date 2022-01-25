package com.example.demo.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.mvc.model.entity.MoneyTransferHst;

@Repository
public interface MoneyTransferHstRepo extends JpaRepository<MoneyTransferHst, Long>{

}
