package com.jpmc.trade.daoimpl;

import java.time.LocalDate;
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
	      static TradeEntity tradeEntity = new TradeEntity();
	      
		  @BeforeClass
	      public static void  fetchActualData() throws TradeReportException  {
			  tradeEntity.setName("foo1");
			  tradeEntity.setTradeType("B");
			  tradeEntity.setAgreedFx(0.50);
			  tradeEntity.setCurrency("SGP");
			  tradeEntity.setSettlementDate(LocalDate.of(2016,1,14));
			  tradeEntity.setPricePerUnit(100.25);
			  tradeEntity.setUnits(200);
			  tradeEntity.setInstructionDate(LocalDate.of(2016,1,13));
	      }
		  
		  @Test
	      public void testCreateTradeEntry()  throws TradeReportException{
	         
			  String result =  iTradeReportAppDao.createTradeEntry(tradeEntity);
			  Assert.assertEquals("SUCCESS", result);
	      }
		  @Test
	      public void testFetchInstructionsData()  throws TradeReportException{
			  String result =  iTradeReportAppDao.createTradeEntry(tradeEntity);
			  List<TradeEntity> tradeEntityLst =  iTradeReportAppDao.fetchInstructionsData();
			  assertFalse(tradeEntityLst.isEmpty());
	      }
		  
		  
		  
		}