package com.jpmc.trade.util;

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
import com.jpmc.trade.entity.TradeEntity;

public class TradeReportAppUtil {
	
	public static Map<Date, Double> evalDailyTrade(List<TradeEntity>  tradeEntityLst ) {
		
			Map<Date, Double> totalByDate = tradeEntityLst.stream().collect(
	                Collectors.groupingBy(TradeEntity::getSettlementDate, 
	                                      Collectors.summingDouble(TradeEntity::getTradeUsdAmt)));
			return totalByDate;
	}
	
	public static List<Entry<String, Double>> evalEntityRakings(List<TradeEntity> tradeEntityLst) {
		
		Map<String, Double> totalIncomingByEntity = tradeEntityLst.stream().collect(
				Collectors.groupingBy(TradeEntity::getName, Collectors.summingDouble(TradeEntity::getTradeUsdAmt)));
		
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
	
	public static  Date evalSettlementDate(String origSettlementDate, String currencyType) 
	    throws ParseException {
	  
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
	    Date date = format.parse(origSettlementDate.replace(" ","/"));
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    if ((currencyType.equalsIgnoreCase(CurrencyType.AED.name())) 
	    		|| (currencyType.equalsIgnoreCase(CurrencyType.SAR.name()))) {
	      switch(cal.get(Calendar.DAY_OF_WEEK)) {
	        case Calendar.FRIDAY: 
	          cal.add(Calendar.DATE, 2);
	          break;
	        case Calendar.SATURDAY:
	          cal.add(Calendar.DATE, 1);
	          break;
	        default:
	          cal.add(Calendar.DATE, 0);
	      }
	    } else {
	      switch(cal.get(Calendar.DAY_OF_WEEK)) {
	        case Calendar.SATURDAY: 
	          cal.add(Calendar.DATE, 2);
	          break;
	        case Calendar.SUNDAY:
	          cal.add(Calendar.DATE, 1);
	          break;
	        default:
	          cal.add(Calendar.DATE, 0);
	      }
	    }
	    
	    
	    return cal.getTime();
	  }
	
	public static String convertToDate(Date setlDate) {
		 String pattern = "dd/MMM/yyyy";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		 String setlDateStr = simpleDateFormat.format(setlDate);
		 return setlDateStr;
	}
	
	/**
	 * Read all InstructionsData from CSV file 
	 * and prepare TradeEntity list
	 * @param tradeType
	 * @return List<TradeEntity>
	 */
	public static List<TradeEntity> fetchAllInstructionsData(String tradeType) {
	    List<TradeEntity> tradeEntityLst = null;
		try {
			String separator = ",";
			String row = "";
			String[] rowData = new String[8];
			Date setlDate = null;
			TradeEntity tradeEntity = null;
			DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
			tradeEntityLst = new ArrayList<TradeEntity>();
 
			String fileName = "trade_instructions_data.csv";
			
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			 
			File file = new File(classLoader.getResource(fileName).getFile());
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while ((row = br.readLine()) != null) {
			  rowData = row.split(separator);
			  
			    setlDate = evalSettlementDate (rowData[5], rowData[3]);
			  
			  if (rowData[1].equalsIgnoreCase(tradeType)){
				  tradeEntity = new TradeEntity();
				  tradeEntity.setName(rowData[0]);
				  tradeEntity.setTradeType(rowData[1]);
				  tradeEntity.setAgreedFx(Double.parseDouble(rowData[2]));
				  tradeEntity.setCurrency(rowData[3]);
				  tradeEntity.setInstructionDate(df.parse(rowData[4].replace(" ","/")));
				  tradeEntity.setSettlementDate(setlDate);
				  tradeEntity.setUnits(Integer.parseInt(rowData[6]));
				  tradeEntity.setPricePerUnit(Double.parseDouble(rowData[7]));
				  tradeEntity.setTradeUsdAmt(tradeEntity.getPricePerUnit() * tradeEntity.getUnits() * tradeEntity.getAgreedFx());
			      tradeEntityLst.add(tradeEntity);
			  } 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tradeEntityLst;
	    
	  }


}
