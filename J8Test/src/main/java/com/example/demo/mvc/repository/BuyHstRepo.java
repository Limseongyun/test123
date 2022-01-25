package com.example.demo.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.mvc.model.entity.BuyHst;

@Repository
public interface BuyHstRepo extends JpaRepository<BuyHst, Long>{

}
