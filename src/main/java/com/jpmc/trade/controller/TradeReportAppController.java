package com.jpmc.trade.controller;

import com.jpmc.trade.service.TradeReportAppService;

public class TradeReportAppController {

	public static void main(String[] args) {

		TradeReportAppService t = new TradeReportAppService();
		t.generateDailyIncomingTradeReport();
		t.generateDailyOutGoingTradeReport();
		t.generateDailyEntitiesRankingReport();

	}

}
