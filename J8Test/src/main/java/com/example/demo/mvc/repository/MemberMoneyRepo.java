package com.example.demo.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.mvc.model.entity.Member;
import com.example.demo.mvc.model.entity.MemberMoney;

@Repository
public interface MemberMoneyRepo extends JpaRepository<MemberMoney, Member>{

}
