package com.jpmc.trade.controller;

import java.time.LocalDate;

import com.jpmc.trade.entity.TradeEntity;
import com.jpmc.trade.exceptions.TradeReportException;
import com.jpmc.trade.service.TradeReportAppService;

public class TradeReportAppController {

	public static void main(String[] args) throws TradeReportException {

		//Creating Sample TradeEntity data 
		TradeReportAppService tradeReportAppService = new TradeReportAppService();
		TradeEntity tradeEntity = new TradeEntity();
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
		  tradeEntity.setName("foo2");
		  tradeEntity.setTradeType("B");
		  tradeEntity.setAgreedFx(0.22);
		  tradeEntity.setCurrency("AED");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,1,7));
		  tradeEntity.setPricePerUnit(22.21);
		  tradeEntity.setUnits(300);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,1,5));
		  tradeReportAppService.createTradeEntry(tradeEntity);
		  
		  tradeEntity = new TradeEntity();
		  tradeEntity.setName("foo3");
		  tradeEntity.setTradeType("B");
		  tradeEntity.setAgreedFx(0.50);
		  tradeEntity.setCurrency("SAR");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,1,23));
		  tradeEntity.setPricePerUnit(60.5);
		  tradeEntity.setUnits(30);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,1,21));
		  tradeReportAppService.createTradeEntry(tradeEntity);
		  
		  tradeEntity = new TradeEntity();
		  tradeEntity.setName("foo4");
		  tradeEntity.setTradeType("B");
		  tradeEntity.setAgreedFx(0.15);
		  tradeEntity.setCurrency("EUR");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,1,14));
		  tradeEntity.setPricePerUnit(101.5);
		  tradeEntity.setUnits(220);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,1,13));
		  tradeReportAppService.createTradeEntry(tradeEntity);
		  
		  tradeEntity = new TradeEntity();
		  tradeEntity.setName("foo5");
		  tradeEntity.setTradeType("S");
		  tradeEntity.setAgreedFx(0.55);
		  tradeEntity.setCurrency("SGP");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,7,3));
		  tradeEntity.setPricePerUnit(100.25);
		  tradeEntity.setUnits(200);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,7,2));
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
		  
		  tradeEntity = new TradeEntity();
		  tradeEntity.setName("foo7");
		  tradeEntity.setTradeType("S");
		  tradeEntity.setAgreedFx(0.50);
		  tradeEntity.setCurrency("SAR");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,7,22));
		  tradeEntity.setPricePerUnit(10.25);
		  tradeEntity.setUnits(20);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,7,21));
		  tradeReportAppService.createTradeEntry(tradeEntity);
		  
		  tradeEntity = new TradeEntity();
		  tradeEntity.setName("foo8");
		  tradeEntity.setTradeType("S");
		  tradeEntity.setAgreedFx(0.50);
		  tradeEntity.setCurrency("EUR");
		  tradeEntity.setSettlementDate(LocalDate.of(2016,8,11));
		  tradeEntity.setPricePerUnit(90.25);
		  tradeEntity.setUnits(100);
		  tradeEntity.setInstructionDate(LocalDate.of(2016,8,10));
		  tradeReportAppService.createTradeEntry(tradeEntity);
		
		  //ReportDate - required day passed for generate report 
		  tradeReportAppService.generateIncomingTradeAmountReport(LocalDate.of(2016,1,14));
		  tradeReportAppService.generateOutingTradeAmountReport(LocalDate.of(2016,7,21));
		  tradeReportAppService.generateDailyEntitiesRankingReport(LocalDate.of(2016,8,11));

	}
	

}
