package com.itheima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.domain.BaseDict;
import com.itheima.mapper.BaseDictMapper;

@Service("baseDictService")
public class BaseDictServiceImpl implements BaseDictService{
	@Autowired
	private BaseDictMapper baseDictMapper;
	@Override
	public List<BaseDict> findBaseDictByTypeCode(String typecode) {
		return baseDictMapper.selectBaseDictByTypecode(typecode);
	}

}
