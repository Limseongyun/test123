package com.example.demo.samplemvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.samplemvc.entity.SampleParent;

@Repository
public interface SampleParentRepo extends JpaRepository<SampleParent, Long> {

}
