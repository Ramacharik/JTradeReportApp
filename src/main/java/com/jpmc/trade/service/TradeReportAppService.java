package com.jpmc.trade.service;


import java.time.LocalDate;
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
	private final String INCOMING_TRADETYPE = "B";
	private final String OUTGOING_TRADETYPE = "S";

	
	/**
	 * Storing TradeEntry data
	 * Save TradeEntry
	 * @param tradeEntity
	 * @return String
	 */
	public String createTradeEntry(TradeEntity tradeEntity) {
		return iTradeReportAppDao.createTradeEntry(tradeEntity);
	}
	
	/**
	 * 1.Fetch all instructions data 
	 * 2.Apply SettlementDate rule
	 * 3.Evaluate of Incoming report generate date's totalTradeAmount in USD
	 * @param reportDate
	 * @return String
	 * @throws TradeReportException
	 */
	public String generateIncomingTradeAmountReport(LocalDate reportDate) throws TradeReportException  {
		String result = null;
		try {
			List<TradeEntity> incomingTradeEntityLst = iTradeReportAppDao.fetchInstructionsData();
			for(TradeEntity tradeEntity:incomingTradeEntityLst) {
				tradeEntity.setSettlementDate(TradeReportAppUtil.evalSettlementDate
						(tradeEntity.getSettlementDate(), tradeEntity.getCurrency()));
			}
            Double totalTradeAmount = TradeReportAppUtil.evalDailyTrade(incomingTradeEntityLst,reportDate,INCOMING_TRADETYPE);
			
			System.out.println("The Incoming trade on date is " + reportDate
					+ " ==== and settled USD amount is " + totalTradeAmount);
			System.out.println("=============The Incoming Trade settlement report is completed ==============");
			result = "SUCCESS";
		}catch (Exception e) {
			throw new TradeReportException("Incoming Trade Report generation failed ");
		}
		return result;
	}
	
	/**
	 * 1.Fetch all instructions data 
	 * 2.Apply SettlementDate rule
	 * 3.Evaluate of Outgoing trade report generate date's totalTradeAmount in USD of 
	 * @param reportDate
	 * @return String
	 * @throws TradeReportException
	 */
	public String generateOutingTradeAmountReport(LocalDate reportDate) throws TradeReportException {
		String result = null;
		try {
			List<TradeEntity> outingTradeEntityLst = iTradeReportAppDao.fetchInstructionsData();
			for(TradeEntity tradeEntity:outingTradeEntityLst) {
				tradeEntity.setSettlementDate(TradeReportAppUtil.evalSettlementDate
						(tradeEntity.getSettlementDate(), tradeEntity.getCurrency()));
			}
			Double totalTradeAmount = TradeReportAppUtil.evalDailyTrade(outingTradeEntityLst,reportDate,OUTGOING_TRADETYPE);
			
			System.out.println("The Incoming trade on date is " + reportDate
					+ " ==== and settled USD amount is " + totalTradeAmount);
			System.out.println("=============The Outgoing Trade settlement report is completed ==============");
			result = "SUCCESS";
		}catch (Exception e) {
			throw new TradeReportException("Outgoing Trade Report generation failed ");
		}
		return result;
	}
	
	/**
	 * 1.Fetch all instructions data 
	 * 2.Evaluate ranking of the report date's Trade Type
	 * 
	 * @param reportDate
	 * @return String
	 * @throws TradeReportException
	 */
	public String generateDailyEntitiesRankingReport(LocalDate reportDate) throws TradeReportException  {
		String result = null;
		
		try {
			int rank = 1;
			List<TradeEntity> tradeEntityLst = iTradeReportAppDao.fetchInstructionsData();
			List<Entry<String, Double>> incomingRankinglist = TradeReportAppUtil
					.evalEntityRakings(tradeEntityLst,reportDate,INCOMING_TRADETYPE);
			
			for (Map.Entry<String, Double> entry : incomingRankinglist) {
				System.out.println(
						"The Incoming Trade entity  is " + entry.getKey() + "  ==== amount is " + entry.getValue() +" == Rank is "+(rank++));
			}
			System.out.println("=============The Incoming Trade entity  ranking is completed ==============");
			rank = 1;
			List<Entry<String, Double>> ougoingRankinglist = TradeReportAppUtil
					.evalEntityRakings(tradeEntityLst,reportDate,OUTGOING_TRADETYPE);
			
			for (Map.Entry<String, Double> entry : ougoingRankinglist) {
				System.out.println(
						"The Outgoing Trade entity  is " + entry.getKey() + "  ==== amount is " + entry.getValue() +" == Rank is "+(rank++));
			}
			System.out.println("=============The Outgoing Trade entity  ranking is completed ==============");
			result = "SUCCESS";
		}catch (Exception e) {
			throw new TradeReportException(" Trade Report ranking generation failed ");
		}
		return result;
	}
	
    

}
