package com.example.demo.jpaTest;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.samplemvc.entity.QSampleParent;
import com.example.demo.samplemvc.entity.SampleParent;
import com.example.demo.samplemvc.repository.SampleParentRepo;
import com.example.demo.samplemvc.service.SampleService;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class Test1 {

	@Autowired private EntityManager em;
	@Autowired private SampleService ss;
	@Autowired private SampleParentRepo spRepo;
	@Autowired private JPAQueryFactory qf;
	
	@Test
	public void test1() {
		SampleParent sp = em.find(SampleParent.class, 3L);
		log.debug("sp: {}", sp.getParentNm());
		
		SampleParent sp2 = ss.jpqlParanetOne(4L);
		log.debug("sp2: {}", sp2.getParentNm());
		
		SampleParent sp3 = new SampleParent();
		sp3.setParentNm("::;;;");
		spRepo.save(sp3);
		
		QSampleParent qsp = QSampleParent.sampleParent;
		List<SampleParent> sp4 = qf.selectFrom(qsp).where(qsp.parentNm.contains("-")).fetch();
		for(SampleParent e : sp4) {
			log.debug("sp4: {}", e.getParentNm());
		}
	}
}
