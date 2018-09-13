package com.jpmc.trade.dao;

import java.util.List;

import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.exceptions.TradeReportException;

public interface ITradeReportAppDao {
	
	public String createTradeEntry(TradeEntity tradeEntity) ;
	public List<TradeEntity>  fetchInstructionsData() ;

}
