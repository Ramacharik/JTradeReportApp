
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jpmc.trade.service.TradeReportAppService;


public class JTradeReportAppTest  {
	  
	TradeReportAppService tradeReportAppService = new TradeReportAppService();
	  
	  
	  @Test
      public void testGenerateDailyIncomingTradeReport()  {
         
		  String result =  tradeReportAppService.generateDailyIncomingTradeReport();
		  assertEquals("SUCCESS", result);
      }
	  @Test
      public void testGenerateDailyOutGoingTradeReport()  {
         
		  String result = tradeReportAppService.generateDailyOutGoingTradeReport();
		  assertEquals("SUCCESS", result);
      }
	  
	  @Test
      public void testGenerateDailyEntitiesRankingReport()  {
         
		  String result = tradeReportAppService.generateDailyEntitiesRankingReport();
		  assertEquals("SUCCESS", result);
      }
	  
	}