package com.g.bathcenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.g.bathcenter.bean.Recharge;
@Mapper
public interface RechargeMapper {
	public void insert(Recharge recharge);
	public List<Recharge> find(int customerId);
}
