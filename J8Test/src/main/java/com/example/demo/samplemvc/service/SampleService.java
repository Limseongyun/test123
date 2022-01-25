package com.example.demo.samplemvc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.samplemvc.entity.QSampleParent;
import com.example.demo.samplemvc.entity.SampleParent;
import com.example.demo.samplemvc.repository.SampleParentRepo;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class SampleService {
	@Autowired private JPAQueryFactory qf;
	@Autowired private SampleParentRepo spRepo;

	public List<SampleParent> getParents() {
		List<SampleParent> result = qf.select(QSampleParent.sampleParent)
				.from(QSampleParent.sampleParent).offset(0).limit(10).fetch();
		return result;
	}
	
	public SampleParent insertParent() {
		SampleParent sp = new SampleParent();
		sp.setParentNm("haha" + UUID.randomUUID().toString().substring(0, 50));
		SampleParent newsp = spRepo.save(sp);
		return newsp;
	}
}
