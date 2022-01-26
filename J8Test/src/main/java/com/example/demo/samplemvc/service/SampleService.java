package com.example.demo.samplemvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.cmm.CommonMap;
import com.example.demo.samplemvc.entity.QSampleChild;
import com.example.demo.samplemvc.entity.QSampleParent;
import com.example.demo.samplemvc.entity.SampleChild;
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
	@Autowired private EntityManager em;

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
	
	public List<SampleParent> jpqlParent(){
		String query = "SELECT sp FROM SampleParent sp";
		List<SampleParent> parents = em.createQuery(query, SampleParent.class).getResultList();
		return parents;
	}
	
	public SampleParent jpqlParanetOne(Long parentSn) {
		String query = "SELECT sp FROM SampleParent sp WHERE sp.parentSn = :parentSn";
		return em.createQuery(query, SampleParent.class).setParameter("parentSn", parentSn).getSingleResult();
	}
	
	public SampleParent emInsertParent() {
		SampleParent sp = new SampleParent();
		sp.setParentNm("가나");
		List<SampleChild> childList = new ArrayList<SampleChild>();
		
		SampleChild sc1 = new SampleChild();
		sc1.setChildNm("im chile1");
		sc1.setParent(sp);
		em.persist(sc1);
		
		SampleChild sc2 = new SampleChild();
		sc2.setChildNm("im child2");
		sc2.setParent(sp);
		em.persist(sc2);
		
		childList.add(sc1);
		childList.add(sc2);
		sp.getChilds().add(sc1);
		sp.getChilds().add(sc2);
		
		em.persist(sp);
		
		em.flush();
		return sp;
	}
}
