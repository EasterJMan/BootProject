package com.itheima.mapper;

import java.util.List;

import com.itheima.domain.BaseDict;

public interface BaseDictMapper {
	public List<BaseDict> selectBaseDictByTypecode(String typecode);	
}
 