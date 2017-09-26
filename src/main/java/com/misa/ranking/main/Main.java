package com.misa.ranking.main;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.misa.ranking.connection.DBconnect;
import com.misa.ranking.connection.WriteDB;
import com.misa.ranking.entity.SlotGame;
import com.misa.ranking.entity.SlotGameRanking;
import com.misa.ranking.model.DataProcess;

public class Main {

	public static void main(String[] args) throws SQLException, IOException, ParseException {

		for (int gameId = 1; gameId <= 5; gameId++) {
			List<SlotGame> slotGames = DBconnect.getSlotGameLi(DBconnect.getSlotGameDBbyID(gameId));
			List<SlotGameRanking> slotGamRankLi = DataProcess.getSlotGameRankLi(slotGames);
			List<SlotGameRanking> dividedLi = DataProcess.divideListByHour(slotGamRankLi);
			List<SlotGameRanking> winnerRankLi = DataProcess.getWinnerRank(dividedLi);

			WriteDB.write_onDB(winnerRankLi, gameId);
		}

	}
}
