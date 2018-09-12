package com.jpmc.trade.entity;

import java.util.Date;

public class TradeEntity {

	private String name;
	private String tradeType;
	private Double agreedFx;
	private String currency;
	private Date instructionDate;
	private Date settlementDate;
	private Integer units;
	private Double pricePerUnit;
	private Double tradeUsdAmt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Double getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(Double agreedFx) {
		this.agreedFx = agreedFx;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Double getTradeUsdAmt() {
		return tradeUsdAmt;
	}

	public void setTradeUsdAmt(Double tradeUsdAmt) {
		this.tradeUsdAmt = tradeUsdAmt;
	}

	@Override
	public boolean equals(Object obj) {
		TradeEntity tradeEntity = (TradeEntity) obj;
		boolean status = false;
		if (this.name.equalsIgnoreCase(tradeEntity.name) && this.agreedFx == tradeEntity.agreedFx
				&& this.tradeType == tradeEntity.tradeType && this.currency == tradeEntity.currency) {
			status = true;
		}
		return status;
	}

	
}
