package com.misa.ranking.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.misa.ranking.entity.SlotGame;
import com.misa.ranking.entity.SlotGameRanking;

public class DataProcess {
	// check the hour after
	public static boolean isHourAfter(String befTime, String afTime) throws ParseException {
		//get hour from date string
		Date befDate = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(befTime);
		Date afDate = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(afTime);
		DateFormat format = new SimpleDateFormat("HH");
		String befHour = format.format(befDate);
		String afHour = format.format(afDate);
		// compare to each other
		if(!afHour.equals(befHour))
			return true;
		return false;
	}
	
	// difference between win money and playing fee
	public static long getMoneyEarned(long prize, long totalBet) {
		return prize-totalBet;
	}
	
	// turn list of SlotGame to list of SlotGameRanking
	// remove players who failed
	public static List<SlotGameRanking> getSlotGameRankLi(List<SlotGame> slotGameLi){
		List<SlotGameRanking> slotGamRankLi = new ArrayList<SlotGameRanking>();
		for(SlotGame slotGame : slotGameLi) {
			SlotGameRanking gameRanking = new SlotGameRanking();
			gameRanking.setId(slotGame.getId());
			gameRanking.setGameId(slotGame.getGameId());
			gameRanking.setNickname(slotGame.getNickname());
			// remove player who fail
			long moneyEarned = getMoneyEarned(slotGame.getPrize(), slotGame.getTotalBet());
			if(moneyEarned>0)
				gameRanking.setMoneyEarned(moneyEarned);
			
			gameRanking.setUpdateTime(slotGame.getUpdateTime());
			
			slotGamRankLi.add(gameRanking);
		}
		return slotGamRankLi;
	}
}
