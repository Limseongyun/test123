package com.example.demo.samplemvc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.cmm.CommonMap;

@Mapper
public interface SampleMapper {
	List<CommonMap> selectParents(CommonMap param);
}
