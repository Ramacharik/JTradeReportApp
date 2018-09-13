package com.jpmc.trade.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.jpmc.trade.constants.CurrencyType;
import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.exceptions.TradeReportException;

public class TradeReportAppUtil {
	
	public static Double evalDailyTrade(List<TradeEntity>  tradeEntityLst ,LocalDate reportDate ,String tradeType) {
		
			Double totalTradeUsdAmt = tradeEntityLst.stream()
					.filter(x -> tradeType.equals(x.getTradeType()))
					.filter(x -> reportDate.compareTo(x.getSettlementDate()) == 0)
					.collect(Collectors.summingDouble(TradeEntity::getTradeUsdAmt));
			return totalTradeUsdAmt;
	}
	
	public static List<Entry<String, Double>> evalEntityRakings(List<TradeEntity> tradeEntityLst,LocalDate reportDate,String tradeType) {
		
		Map<String, Double> totalIncomingByEntity = tradeEntityLst.stream()
				.filter(x -> tradeType.equals(x.getTradeType()))
				.filter(x -> reportDate.compareTo(x.getSettlementDate()) == 0)
				.collect(Collectors.groupingBy(TradeEntity::getName, Collectors.summingDouble(TradeEntity::getTradeUsdAmt)));
		
		Set<Entry<String, Double>> set = totalIncomingByEntity.entrySet();
		List<Entry<String, Double>> sortedRankinglist = new ArrayList<Entry<String, Double>>(set);
		Collections.sort( sortedRankinglist, new Comparator<Map.Entry<String, Double>>(){
			public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 )
			{
				   return (o2.getValue()).compareTo( o1.getValue() );
			}
		});
		
		return sortedRankinglist;
	}
	
	public static  LocalDate evalSettlementDate(LocalDate origSettlementDate, String currencyType) throws TradeReportException 
	    {
	    try {
			if ((currencyType.equalsIgnoreCase(CurrencyType.AED.name())) 
					|| (currencyType.equalsIgnoreCase(CurrencyType.SAR.name()))) {
			  switch(origSettlementDate.getDayOfWeek().name()) {
			    case "FRIDAY": 
			    	origSettlementDate = origSettlementDate.plusDays(2);
			      break;
			    case "SATURDAY":
			    	origSettlementDate = origSettlementDate.plusDays(1);
			      break;
			    default:
			    	origSettlementDate = origSettlementDate.plusDays(0);
			  }
			} else {
			  switch(origSettlementDate.getDayOfWeek().name()) {
			    case "SATURDAY": 
			    	origSettlementDate = origSettlementDate.plusDays(2);
			      break;
			    case "SUNDAY":
			    	origSettlementDate = origSettlementDate.plusDays(1);
			      break;
			    default:
			    	origSettlementDate = origSettlementDate.plusDays(0);
			  }
			}
		} catch (Exception ex) {
			throw new TradeReportException("SettlementDate conversion failed");
		}
	    
	    return origSettlementDate;
	  }
	  
	

}
