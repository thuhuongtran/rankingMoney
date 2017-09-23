package com.misa.ranking.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.misa.ranking.connection.DBconnect;
import com.misa.ranking.entity.SlotGame;
import com.misa.ranking.entity.SlotGameRanking;
import com.misa.ranking.model.DataProcess;

public class Main {

	public static void main(String[] args) throws SQLException, IOException {
		List<SlotGame> slotGames = DBconnect.getSlotGameLi(DBconnect.getSlotGameDBbyID(5));
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
		List<SlotGameRanking> slotGamrRankLi = DataProcess.getSlotGameRankLi(slotGames);
		for(SlotGameRanking gameRanking : slotGamrRankLi) {
			System.out.println();
			System.out.print("\t"+gameRanking.getGameId());
			System.out.print("\t"+gameRanking.getId());
			System.out.print("\t"+gameRanking.getNickname());
			System.out.print("\t"+gameRanking.getMoneyEarned());
			System.out.print("\t"+gameRanking.getUpdateTime());
		}
		
	}

}
