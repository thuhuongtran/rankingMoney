package com.misa.ranking.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.misa.ranking.entity.SlotGame;
import com.misa.ranking.entity.SlotGameRanking;

public class DataProcess {
	// check the hour after
	public static boolean isHourAfter(String befTime, String afTime) throws ParseException {
		// get hour from date string
		Date befDate = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(befTime);
		Date afDate = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(afTime);
		DateFormat format = new SimpleDateFormat("HH");
		String befHour = format.format(befDate);
		String afHour = format.format(afDate);
		// compare to each other
		if (!afHour.equals(befHour))
			return true;
		return false;
	}

	// difference between win money and playing fee
	public static long getMoneyEarned(long prize, long totalBet) {
		return prize - totalBet;
	}

	// turn list of SlotGame to list of SlotGameRanking
	// remove players who failed
	public static List<SlotGameRanking> getSlotGameRankLi(List<SlotGame> slotGameLi) {
		List<SlotGameRanking> slotGamRankLi = new ArrayList<SlotGameRanking>();
		for (SlotGame slotGame : slotGameLi) {
			SlotGameRanking gameRanking = new SlotGameRanking();
			gameRanking.setId(slotGame.getId());
			gameRanking.setGameId(slotGame.getGameId());
			gameRanking.setNickname(slotGame.getNickname());
			gameRanking.setUpdateTime(slotGame.getUpdateTime());
			// remove player who fail
			long moneyEarned = getMoneyEarned(slotGame.getPrize(), slotGame.getTotalBet());

			if (moneyEarned > 0) {
				gameRanking.setMoneyEarned(moneyEarned);
				slotGamRankLi.add(gameRanking);
			}
		}
		return slotGamRankLi;
	}

	// sort list of slotGameRank by moneyEarned
	// then add winners who have highest moneyEarned in list
	public static void addWinners(List<SlotGameRanking> slotGamRankLi, List<SlotGameRanking> winnerLi) {
		// sorting firstly
		slotGamRankLi.sort(new Comparator<SlotGameRanking>() {
			public int compare(SlotGameRanking o1, SlotGameRanking o2) {
				return Long.compare(o2.getMoneyEarned(),o1.getMoneyEarned());
			}
		});
		// add all winners in list
		for(int i=0; i< slotGamRankLi.size();i++) {
			if(i>=1 && slotGamRankLi.get(i).getMoneyEarned() < slotGamRankLi.get(i-1).getMoneyEarned())
				break;
			winnerLi.add(slotGamRankLi.get(i));
		}
	}
	
	
	/*
	 * divide into small list by hour of date
	 * each list of hour is separated by null object
	 */
	public static List<SlotGameRanking> divideListByHour(List<SlotGameRanking> slotGamRankLi) throws ParseException {
		
		List<SlotGameRanking> dividedSlotGamRankLi = new ArrayList<SlotGameRanking>();
		// create first time of date
		String time = slotGamRankLi.get(0).getUpdateTime();
		for (SlotGameRanking slotGamRank : slotGamRankLi) {
			// add into divided list
			if (isHourAfter(time, slotGamRank.getUpdateTime())) {
				SlotGameRanking slotR = new SlotGameRanking();
				slotR.setGameId(0);
				dividedSlotGamRankLi.add(slotR);
			}
			dividedSlotGamRankLi.add(slotGamRank);
			// update time of date
			time = slotGamRank.getUpdateTime();
		}
		// if list is over then add one more object which has gameId = 0
		SlotGameRanking slotR = new SlotGameRanking();
		slotR.setGameId(0);
		dividedSlotGamRankLi.add(slotR);
		
		return dividedSlotGamRankLi;
	}
	/*
	 * sort list by moneyEarned
	 * add all winners with greatest money into an other list
	 * */
	public static List<SlotGameRanking> getWinnerRank(List<SlotGameRanking> dividedSlotGamRankLi) {
		// small list is to save lists winner divided by hour of date
		List<SlotGameRanking> smallLi = new ArrayList<SlotGameRanking>();
		List<SlotGameRanking> winnerRankLi = new ArrayList<SlotGameRanking>();
		SlotGameRanking winner = new SlotGameRanking();
		int startPoint =0;
		int endPoint = 0;
		for(SlotGameRanking slotGamRank : dividedSlotGamRankLi) {
			if(slotGamRank.getGameId()==0) {
				// firstly, divide list 
				endPoint = dividedSlotGamRankLi.indexOf(slotGamRank);
				smallLi = dividedSlotGamRankLi.subList(startPoint, endPoint);
				
				// get object which has the highest moneyEarned --> n^2 OMG
				// then add winners in new list
				addWinners(smallLi, winnerRankLi);
				
				// update points
				startPoint = endPoint+1;
			}
			
		}
		return winnerRankLi;
	}
}
