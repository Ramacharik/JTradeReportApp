package com.jpmc.trade.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.exceptions.TradeReportException;
import com.jpmc.trade.service.TradeReportAppService;


public class TradeReportAppServiceTest  {
	  
	 static TradeReportAppService tradeReportAppService = new TradeReportAppService();
	 static TradeEntity tradeEntity = new TradeEntity();
	@BeforeClass
    public  static void  fetchActualData() throws TradeReportException  {
		  tradeEntity.setName("foo1");
		  tradeEntity.setTradeType("B");
		  tradeEntity.setAgreedFx(0.50);
		  tradeEntity.setCurrency("SGP");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,1,14));
		  tradeEntity.setPricePerUnit(100.25);
		  tradeEntity.setUnits(200);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,1,13));
		  tradeReportAppService.createTradeEntry(tradeEntity);
		  tradeEntity = new TradeEntity();
		  tradeEntity.setName("foo6");
		  tradeEntity.setTradeType("S");
		  tradeEntity.setAgreedFx(0.50);
		  tradeEntity.setCurrency("AED");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,7,21));
		  tradeEntity.setPricePerUnit(30.25);
		  tradeEntity.setUnits(200);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,7,20));
		  tradeReportAppService.createTradeEntry(tradeEntity);
    }
	 
	 @Test
     public void testCreateTradeEntry() throws TradeReportException  {
		  String result =  tradeReportAppService.createTradeEntry(tradeEntity);
		  assertEquals("SUCCESS", result);
     }
	
	  @Test
      public void testGenerateDailyIncomingTradeReport() throws TradeReportException  {
		  String result =  tradeReportAppService.generateIncomingTradeAmountReport(LocalDate.of(2016,1,14));
		  assertEquals("SUCCESS", result);
      }
	  @Test
      public void testGenerateDailyOutGoingTradeReport() throws TradeReportException  {
         
		  String result = tradeReportAppService.generateOutingTradeAmountReport(LocalDate.of(2016,7,21));
		  assertEquals("SUCCESS", result);
      }
	  
	  @Test
      public void testGenerateDailyEntitiesRankingReport() throws TradeReportException  {
         
		  String result = tradeReportAppService.generateDailyEntitiesRankingReport(LocalDate.of(2016,7,21));
		  assertEquals("SUCCESS", result);
      }
	  
	  @Test(expected=TradeReportException.class)
		public void testGenerateIncomingTradeAmountReportException() throws TradeReportException {
			
		  String result =  tradeReportAppService.generateIncomingTradeAmountReport(null);
		  assertEquals("SUCCESS", result);
		}
	  
	  @Test(expected=TradeReportException.class)
		public void testGenerateOutgoingTradeAmountReportException() throws TradeReportException {
			
		  String result =  tradeReportAppService.generateOutingTradeAmountReport(null);
		  assertEquals("SUCCESS", result);
		}
	  
	  @Test(expected=TradeReportException.class)
		public void testGenerateDailyEntitiesRankingReportException() throws TradeReportException {
			
		  String result =  tradeReportAppService.generateDailyEntitiesRankingReport(null);
		  assertEquals("SUCCESS", result);
		}
	  
	}