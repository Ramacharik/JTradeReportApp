package com.jpmc.trade.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.jpmc.trade.dao.ITradeReportAppDao;
import com.jpmc.trade.entity.TradeEntity;

public class TradeReportAppDaoImpl implements ITradeReportAppDao {

	private List<TradeEntity> tradeEntityLst = new ArrayList<>();

	public String createTradeEntry(TradeEntity tradeEntity) {
	   String result =  "";	
	   if(tradeEntityLst!=null) {
	     tradeEntityLst.add(tradeEntity);
	     result = "SUCCESS";
	   }
	   return result;
	}

	public List<TradeEntity> fetchInstructionsData() {
		return tradeEntityLst;
	}

}
