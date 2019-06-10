package com.itheima.service;

import java.util.List;

import com.itheima.domain.BaseDict;

public interface BaseDictService {
	public List<BaseDict> findBaseDictByTypeCode(String typecode);

}
