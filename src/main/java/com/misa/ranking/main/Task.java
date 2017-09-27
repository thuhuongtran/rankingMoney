package com.misa.ranking.main;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.TimerTask;

import com.misa.ranking.connection.DBconnect;
import com.misa.ranking.connection.WriteDB;
import com.misa.ranking.entity.SlotGame;
import com.misa.ranking.entity.SlotGameRanking;
import com.misa.ranking.model.DataProcess;

public class Task extends TimerTask {
	@Override
	public void run() {
		for (int gameId = 1; gameId <= 5; gameId++) {
			try {
				List<SlotGame> slotGames = DBconnect.getSlotGameLi(DBconnect.getSlotGameDBbyID(gameId));
				List<SlotGameRanking> slotGamRankLi = DataProcess.getSlotGameRankLi(slotGames);
				List<SlotGameRanking> dividedLi = DataProcess.divideListByHour(slotGamRankLi);
				List<SlotGameRanking> playerRankLi = DataProcess.getPlayerRank(dividedLi);

				WriteDB.write_onDB(playerRankLi, gameId);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
	}
}
