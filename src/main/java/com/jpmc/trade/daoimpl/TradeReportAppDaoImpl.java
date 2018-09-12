package com.jpmc.trade.daoimpl;

import java.util.List;

import com.jpmc.trade.dao.ITradeReportAppDao;
import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.exceptions.TradeReportException;
import com.jpmc.trade.util.TradeReportAppUtil;

public class TradeReportAppDaoImpl implements ITradeReportAppDao {

	private final String INCOMING_TRADETYPE = "B";
	private final String OUTGOING_TRADETYPE = "S";
	private final String fileName = "trade_instructions_data.csv";
	

	public List<TradeEntity> fetchIncomingInstructionsData() throws TradeReportException {
		List<TradeEntity> tradeEntityLst = null;
		try {
			tradeEntityLst = TradeReportAppUtil.fetchAllInstructionsData(INCOMING_TRADETYPE,fileName);

		} catch (Exception e) {
			throw new TradeReportException("Incoming Trade Report generation failed ");
		}
		return tradeEntityLst;
	}

	public List<TradeEntity> fetchOutgoingInstructionsData() throws TradeReportException {
		List<TradeEntity> tradeEntityLst = null;
		try {
			tradeEntityLst = TradeReportAppUtil.fetchAllInstructionsData(OUTGOING_TRADETYPE,fileName);

		} catch (Exception e) {
			throw new TradeReportException("Outgoing Trade Report generation failed ");
		}
		return tradeEntityLst;
	}

}
