package com.jpmc.trade.service;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jpmc.trade.dao.ITradeReportAppDao;
import com.jpmc.trade.daoimpl.TradeReportAppDaoImpl;
import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.exceptions.TradeReportException;
import com.jpmc.trade.util.TradeReportAppUtil;

public class TradeReportAppService {
	
	public ITradeReportAppDao iTradeReportAppDao = new TradeReportAppDaoImpl();
	
	public String generateDailyIncomingTradeReport()  {
		String result = null;
		try {
			List<TradeEntity> incomingTradeEntityLst = iTradeReportAppDao.fetchIncomingInstructionsData();
            Map<Date, Double> totalByIncomingDate = TradeReportAppUtil.evalDailyTrade(incomingTradeEntityLst);
			
			for (Map.Entry<Date, Double> entry : totalByIncomingDate.entrySet()) {
				String settlementDateStr = TradeReportAppUtil.convertToDate(entry.getKey());
				System.out.println("The Incoming trade on date is " + settlementDateStr
						+ " ==== and settled USD amount is " + entry.getValue());
			}
			System.out.println("=============The Incoming Trade settlement report is completed ==============");
			result = "SUCCESS";
		}catch (TradeReportException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String generateDailyOutGoingTradeReport() {
		String result = null;
		try {
			List<TradeEntity> outingTradeEntityLst = iTradeReportAppDao.fetchOutgoingInstructionsData();
             Map<Date, Double> totalByOutgoingDate = TradeReportAppUtil.evalDailyTrade(outingTradeEntityLst);
			
			for (Map.Entry<Date, Double> entry : totalByOutgoingDate.entrySet()) {
				String settlementDateStr = TradeReportAppUtil.convertToDate(entry.getKey());
				System.out.println("The Outgoing trade on date is " + settlementDateStr
						+ " ==== and settled USD amount is " + entry.getValue());
			}
			System.out.println("=============The Outgoing Trade settlement report is completed ==============");
			result = "SUCCESS";
		}catch (TradeReportException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String generateDailyEntitiesRankingReport()  {
		String result = null;
		try {
			List<TradeEntity> incomingTradeEntityLst = iTradeReportAppDao.fetchIncomingInstructionsData();
			List<Entry<String, Double>> incomingRankinglist = TradeReportAppUtil
					.evalEntityRakings(incomingTradeEntityLst);
			
			for (Map.Entry<String, Double> entry : incomingRankinglist) {
				System.out.println(
						"The Incoming Trade entity  is " + entry.getKey() + "  ==== rank is " + entry.getValue());
			}
			System.out.println("=============The Incoming Trade entity  ranking is completed ==============");
			List<TradeEntity> outingTradeEntityLst = iTradeReportAppDao.fetchOutgoingInstructionsData();
			List<Entry<String, Double>> ougoingRankinglist = TradeReportAppUtil
					.evalEntityRakings(outingTradeEntityLst);
			
			for (Map.Entry<String, Double> entry : ougoingRankinglist) {
				System.out.println(
						"The Outgoing Trade entity  is " + entry.getKey() + "  ==== rank is " + entry.getValue());
			}
			System.out.println("=============The Outgoing Trade entity  ranking is completed ==============");
			result = "SUCCESS";
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
