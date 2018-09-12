package com.jpmc.trade.util;

import static org.junit.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpmc.trade.constants.CurrencyType;
import com.jpmc.trade.dao.ITradeReportAppDao;
import com.jpmc.trade.daoimpl.TradeReportAppDaoImpl;
import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.exceptions.TradeReportException;

public class TradeReportAppUtilTest {
	
	
     static List<TradeEntity> actualIncomingTradeList  =  new ArrayList<>();
     static List<TradeEntity> actualOutgoingingTradeList  = new ArrayList<>();
     static String fileName = "trade_instructions_data.csv";
    
	 @BeforeClass
    public static void   fetchActualData()  {
       
		  TradeEntity tradeEntity = new TradeEntity();
		  tradeEntity.setName("foo");
		  tradeEntity.setTradeType("B");
		  tradeEntity.setAgreedFx(0.50);
		  tradeEntity.setCurrency("SGP");
		  tradeEntity.setSettlementDate(new Date());
		  tradeEntity.setPricePerUnit(100.25);
		  tradeEntity.setUnits(200);
		  tradeEntity.setInstructionDate(new Date());
		  tradeEntity.setTradeUsdAmt(2000.25);
		  actualIncomingTradeList.add(tradeEntity);
		  TradeEntity tradeEntitySales = new TradeEntity();
		  tradeEntitySales.setName("bar");
		  tradeEntitySales.setTradeType("S");
		  tradeEntitySales.setAgreedFx(0.10);
		  tradeEntitySales.setCurrency("AED");
		  tradeEntitySales.setSettlementDate(new Date());
		  tradeEntitySales.setPricePerUnit(110.25);
		  tradeEntitySales.setUnits(220);
		  tradeEntitySales.setTradeUsdAmt(2000.25);
		  tradeEntitySales.setInstructionDate(new Date());
		  actualOutgoingingTradeList.add(tradeEntitySales);
    }
	@Test
	public void testEvalDailyTrade( ) {
		
			Map<Date, Double> totalByDate = TradeReportAppUtil.evalDailyTrade(actualIncomingTradeList);
			assertFalse(totalByDate.isEmpty());
	}
	@Test
	public void testEvalEntityRakings() {
		List<Entry<String, Double>> sortedRankinglist  = TradeReportAppUtil.evalEntityRakings(actualOutgoingingTradeList);
		assertFalse(sortedRankinglist.isEmpty());
	}
	
	@Test
	public void  testFetchAllInstructionsData() throws TradeReportException {
		
		List<TradeEntity> incomingTradeList =  TradeReportAppUtil.fetchAllInstructionsData("B",fileName);
		assertFalse(incomingTradeList.isEmpty());
	}
	
	@Test(expected=TradeReportException.class)
	public void  testFetchAllInstructionsDataException() throws TradeReportException {
		
		List<TradeEntity> incomingTradeList =  TradeReportAppUtil.fetchAllInstructionsData("B","");
		assertFalse(incomingTradeList.isEmpty());
	}


}
