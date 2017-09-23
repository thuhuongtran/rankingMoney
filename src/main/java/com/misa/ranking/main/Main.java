package com.misa.ranking.main;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.misa.ranking.connection.DBconnect;
import com.misa.ranking.entity.SlotGame;
import com.misa.ranking.entity.SlotGameRanking;
import com.misa.ranking.model.DataProcess;

public class Main {

	public static void main(String[] args) throws SQLException, IOException, ParseException {
		List<SlotGame> slotGames = DBconnect.getSlotGameLi(DBconnect.getSlotGameDBbyID(1));
		// print list of slotgame
		for(int i=0;i<50;i++) {
			System.out.println();
			System.out.print("\t"+slotGames.get(i).getGameId());
			System.out.print("\t"+slotGames.get(i).getId());
			System.out.print("\t"+slotGames.get(i).getNickname());
			System.out.print("\t"+slotGames.get(i).getPrize());
			System.out.print("\t"+slotGames.get(i).getTotalBet());
			System.out.print("\t"+slotGames.get(i).getUpdateTime());
			System.out.print("\t"+slotGames.get(i).getCreate_time());
		}
		
		System.out.println("after processing");
		
		List<SlotGameRanking> slotGamRankLi = DataProcess.getSlotGameRankLi(slotGames);
		for(int i=0;i<50;i++) {
			System.out.println();
			System.out.print("\t"+slotGamRankLi.get(i).getGameId());
			System.out.print("\t"+slotGamRankLi.get(i).getId());
			System.out.print("\t"+slotGamRankLi.get(i).getNickname());
			System.out.print("\t"+slotGamRankLi.get(i).getMoneyEarned());
			System.out.print("\t"+slotGamRankLi.get(i).getUpdateTime());
		}
		/*
		// testing sorting list by gained money
		System.out.println("after sorting");
		DataProcess.sortByWinMoney(slotGamRankLi);
		for(int i=0;i<50;i++) {
			System.out.println();
			System.out.print("\t"+slotGamRankLi.get(i).getGameId());
			System.out.print("\t"+slotGamRankLi.get(i).getId());
			System.out.print("\t"+slotGamRankLi.get(i).getNickname());
			System.out.print("\t"+slotGamRankLi.get(i).getMoneyEarned());
			System.out.print("\t"+slotGamRankLi.get(i).getUpdateTime());
		}
		*/
		// checking divided list
		List<SlotGameRanking> dividedLi = DataProcess.divideListByHour(slotGamRankLi);
		System.out.println("after divide");
		for(int i=0;i<100;i++) {
			System.out.println();
			if(dividedLi.get(i).getGameId()==0)
				System.out.println("NULL------");
			else {
				System.out.print("\t"+dividedLi.get(i).getGameId());
				System.out.print("\t"+dividedLi.get(i).getId());
				System.out.print("\t"+dividedLi.get(i).getNickname());
				System.out.print("\t"+dividedLi.get(i).getMoneyEarned());
				System.out.print("\t"+dividedLi.get(i).getUpdateTime());
			}
			
		}
		// checking winner rank
		List<SlotGameRanking> winnerRankLi = DataProcess.getWinnerRank(dividedLi);
		System.out.println("Winner in hour");
		for(SlotGameRanking slot : winnerRankLi) {
			System.out.println();
			System.out.print("\t"+slot.getGameId());
			System.out.print("\t"+slot.getId());
			System.out.print("\t"+slot.getNickname());
			System.out.print("\t"+slot.getMoneyEarned());
			System.out.print("\t"+slot.getUpdateTime());
		}
	}

}
