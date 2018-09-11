package com.jpmc.trade.daoimpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jpmc.trade.dao.ITradeReportAppDao;
import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.util.TradeReportAppUtil;
import com.jpmc.trade.util.TradeReportException;

public class TradeReportAppDaoImpl implements ITradeReportAppDao {

	private final String INCOMING_TRADETYPE = "B";
	private final String OUTGOING_TRADETYPE = "S";

	public String generateDailyIncomingTradeReport() throws TradeReportException {
		String result = null;
		try {
			
			List<TradeEntity> tradeEntityLst = TradeReportAppUtil.fetchAllInstructionsData(INCOMING_TRADETYPE);
			Map<Date, Double> totalByIncomingDate = TradeReportAppUtil.evalDailyTrade(tradeEntityLst);
			
			for (Map.Entry<Date, Double> entry : totalByIncomingDate.entrySet()) {
				String settlementDateStr = TradeReportAppUtil.convertToDate(entry.getKey());
				System.out.println("The Incoming trade on date is " + settlementDateStr
						+ " ==== and settled USD amount is " + entry.getValue());
			}
			System.out.println("=============The Incoming Trade settlement report is completed ==============");
			result = "SUCCESS";
		} catch (Exception e) {
			result = "FAILED";
			throw new TradeReportException("Incoming Trade Report generation failed ");
		}
		return result;
	}

	public String generateDailyOutGoingTradeReport() throws TradeReportException {
		String result = null;
		try {
			List<TradeEntity> tradeEntityLst = TradeReportAppUtil.fetchAllInstructionsData(OUTGOING_TRADETYPE);
			Map<Date, Double> totalByOutgoingDate = TradeReportAppUtil.evalDailyTrade(tradeEntityLst);
			
			for (Map.Entry<Date, Double> entry : totalByOutgoingDate.entrySet()) {
				String settlementDateStr = TradeReportAppUtil.convertToDate(entry.getKey());
				System.out.println("The Outgoing trade on date is " + settlementDateStr
						+ " ==== and settled USD amount is " + entry.getValue());
			}
			System.out.println("=============The Outgoing Trade settlement report is completed ==============");
			result = "SUCCESS";
		} catch (Exception e) {
			result = "FAILED";
			throw new TradeReportException("Outgoing Trade Report generation failed ");
		}
		return result;
	}

	public String generateDailyEntitiesRankingReport() throws TradeReportException {
		String result = null;
		try {
			List<TradeEntity> incomingTradeEntityLst = TradeReportAppUtil.fetchAllInstructionsData(INCOMING_TRADETYPE);
			List<Entry<String, Double>> incomingRankinglist = TradeReportAppUtil
					.evalEntityRakings(incomingTradeEntityLst);
			
			for (Map.Entry<String, Double> entry : incomingRankinglist) {
				System.out.println(
						"The Incoming Trade entity  is " + entry.getKey() + "  ==== rank is " + entry.getValue());
			}
			System.out.println("=============The Incoming Trade entity  ranking is completed ==============");
			
			List<TradeEntity> outgoingTradeEntityLst = TradeReportAppUtil.fetchAllInstructionsData(OUTGOING_TRADETYPE);
			List<Entry<String, Double>> ougoingRankinglist = TradeReportAppUtil
					.evalEntityRakings(outgoingTradeEntityLst);
			
			for (Map.Entry<String, Double> entry : ougoingRankinglist) {
				System.out.println(
						"The Outgoing Trade entity  is " + entry.getKey() + "  ==== rank is " + entry.getValue());
			}
			System.out.println("=============The Outgoing Trade entity  ranking is completed ==============");
			result = "SUCCESS";
			
		} catch (Exception e) {
			result = "FAILED";
			throw new TradeReportException("Entities Ranking Report generation failed ");
		}
		return result;

	}

}
