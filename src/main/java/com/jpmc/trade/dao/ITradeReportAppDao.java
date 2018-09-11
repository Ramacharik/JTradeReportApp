package com.jpmc.trade.dao;

import com.jpmc.trade.util.TradeReportException;

public interface ITradeReportAppDao {
	
	public String generateDailyIncomingTradeReport() throws TradeReportException;
	public String generateDailyOutGoingTradeReport() throws TradeReportException;
	public String generateDailyEntitiesRankingReport() throws TradeReportException;

}
