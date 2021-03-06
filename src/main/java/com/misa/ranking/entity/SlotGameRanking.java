package com.misa.ranking.entity;

public class SlotGameRanking {
	private int gameId;
	private long id;
	private String nickname;
	private long moneyEarned;
	private long prize;
	private long totalBet;
	private String updateTime;
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public long getMoneyEarned() {
		return moneyEarned;
	}
	public void setMoneyEarned(long moneyEarned) {
		this.moneyEarned = moneyEarned;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public long getPrize() {
		return prize;
	}
	public void setPrize(long prize) {
		this.prize = prize;
	}
	public long getTotalBet() {
		return totalBet;
	}
	public void setTotalBet(long totalBet) {
		this.totalBet = totalBet;
	}
	
}
