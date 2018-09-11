package com.jpmc.trade.service;


import com.jpmc.trade.dao.ITradeReportAppDao;
import com.jpmc.trade.daoimpl.TradeReportAppDaoImpl;
import com.jpmc.trade.util.TradeReportException;

public class TradeReportAppService {
	
	public ITradeReportAppDao iTradeReportAppDao = new TradeReportAppDaoImpl();
	
	public String generateDailyIncomingTradeReport()  {
		String result = null;
		try {
			result = iTradeReportAppDao.generateDailyIncomingTradeReport();
		}catch (TradeReportException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String generateDailyOutGoingTradeReport() {
		String result = null;
		try {
			result = iTradeReportAppDao.generateDailyOutGoingTradeReport();
		}catch (TradeReportException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String generateDailyEntitiesRankingReport()  {
		String result = null;
		try {
			result = iTradeReportAppDao.generateDailyEntitiesRankingReport();
		}catch (TradeReportException e) {
			e.printStackTrace();
		}
		return result;
	}
	
    public static void main(String[] args) {
		
	    	TradeReportAppService t = new TradeReportAppService();
		t.generateDailyIncomingTradeReport();
		t.generateDailyOutGoingTradeReport();
		t.generateDailyEntitiesRankingReport();
			
	}

}
