package com.example.demo.samplemvc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.cmm.CommonMap;
import com.example.demo.samplemvc.entity.QSampleChild;
import com.example.demo.samplemvc.entity.QSampleParent;
import com.example.demo.samplemvc.entity.SampleParent;
import com.example.demo.samplemvc.mapper.SampleMapper;
import com.example.demo.samplemvc.repository.SampleParentRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
@Transactional
public class SampleService {
	@Autowired private JPAQueryFactory qf;
	@Autowired private SampleParentRepo spRepo;
	@Autowired private SampleMapper samplemapper;

	public List<SampleParent> getParents() {
		QSampleParent qsp = QSampleParent.sampleParent;
		QSampleChild  qsc = QSampleChild.sampleChild;
		List<SampleParent> result = qf.select(qsp)
				.from(qsp)
				.leftJoin(qsp.childs, qsc)
				.offset(0).limit(50).fetchJoin().fetch();
		return result;
	}
	
	public SampleParent insertParent() {
		SampleParent sp = new SampleParent();
		sp.setParentNm("haha" + UUID.randomUUID().toString().substring(0, 10));
		SampleParent newsp = spRepo.save(sp);
		return newsp;
	}
	
	public List<CommonMap> batisParent(CommonMap param){
		return samplemapper.selectParents(param);
	}
}
