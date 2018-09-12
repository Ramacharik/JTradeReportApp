package com.jpmc.trade.daoimpl;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertFalse;

import com.jpmc.trade.dao.ITradeReportAppDao;
import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.exceptions.TradeReportException;
import com.jpmc.trade.util.TradeReportAppUtil;

public class TradeReportAppDaoImplTest  {
	     
	      ITradeReportAppDao iTradeReportAppDao = new TradeReportAppDaoImpl();
	      static List<TradeEntity> actualIncomingTradeList  = null;
	      static List<TradeEntity> actualOutgoingingTradeList  = null;
	      static String fileName = "trade_instructions_data.csv";
	      
		  @BeforeClass
	      public static void  fetchActualData() throws TradeReportException  {
	         
			   actualIncomingTradeList  =  TradeReportAppUtil.fetchAllInstructionsData("B",fileName);
			   actualOutgoingingTradeList  =  TradeReportAppUtil.fetchAllInstructionsData("S",fileName);
	      }
		  
		  @Test
	      public void testevalDailyTrade()  throws TradeReportException{
	         
			  List<TradeEntity> incomingTradeList =  iTradeReportAppDao.fetchIncomingInstructionsData();
			  assertFalse(incomingTradeList.isEmpty());
			  Assert.assertEquals(incomingTradeList.size(), actualIncomingTradeList.size());
	      }
		  @Test
	      public void testFetchOutgoingInstructionsData()  throws TradeReportException{
	         
			  List<TradeEntity> outgoingTradeList =  iTradeReportAppDao.fetchOutgoingInstructionsData();
			  assertFalse(outgoingTradeList.isEmpty());
			  Assert.assertEquals(outgoingTradeList.size(), actualOutgoingingTradeList.size());
	      }
		  
		  
		  
		}