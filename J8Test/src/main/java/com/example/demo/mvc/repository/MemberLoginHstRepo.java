package com.example.demo.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.mvc.model.entity.MemberLoginHst;

@Repository
public interface MemberLoginHstRepo extends JpaRepository<MemberLoginHst, Long>{

}
