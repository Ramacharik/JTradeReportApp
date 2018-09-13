package com.jpmc.trade.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.exceptions.TradeReportException;

public class TradeReportAppUtilTest {
	
	
     static List<TradeEntity> actualIncomingTradeList  =  new ArrayList<>();
     static List<TradeEntity> actualOutgoingingTradeList  = new ArrayList<>();
     static String fileName = "trade_instructions_data.csv";
    
	 @BeforeClass
    public static void   fetchActualData()  {
       
		 TradeEntity tradeEntity = new TradeEntity();
		  tradeEntity.setName("foo1");
		  tradeEntity.setTradeType("B");
		  tradeEntity.setAgreedFx(0.50);
		  tradeEntity.setCurrency("SGP");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,1,14));
		  tradeEntity.setPricePerUnit(100.25);
		  tradeEntity.setUnits(200);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,1,13));
		  actualIncomingTradeList.add(tradeEntity);
		  
		  tradeEntity = new TradeEntity();
		  tradeEntity.setName("foo2");
		  tradeEntity.setTradeType("S");
		  tradeEntity.setAgreedFx(0.22);
		  tradeEntity.setCurrency("AED");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,1,7));
		  tradeEntity.setPricePerUnit(22.21);
		  tradeEntity.setUnits(300);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,1,5));
		  actualOutgoingingTradeList.add(tradeEntity);
    }
	@Test
	public void testEvalDailyTrade( ) {
		
			Double totalTradeUsdAmt = TradeReportAppUtil.evalDailyTrade(actualIncomingTradeList, LocalDate.of(2016,1,14), "B");
			Double actualTradeUsdAmt = 10025.0d;
			assertEquals(actualTradeUsdAmt, totalTradeUsdAmt);;
	}
	@Test
	public void testEvalEntityRakings() {
		List<Entry<String, Double>> sortedRankinglist  = TradeReportAppUtil.evalEntityRakings(actualOutgoingingTradeList, LocalDate.of(2016,1,7), "S");
		assertFalse(sortedRankinglist.isEmpty());
	}
	
	@Test
	public void  testEvalSettlementDate() throws TradeReportException   {
		
		LocalDate adjustedSettleDate =  TradeReportAppUtil.evalSettlementDate(LocalDate.of(2016,1,14), "B");
		assertEquals(LocalDate.of(2016,1,14),adjustedSettleDate);
	}
	
	@Test(expected=TradeReportException.class)
	public void testEvalSettlementDateException() throws TradeReportException {
		
		LocalDate adjustedSettleDate =  TradeReportAppUtil.evalSettlementDate(null, "B");
		assertEquals(LocalDate.of(2016,1,14),adjustedSettleDate);
	}
	
	


}
