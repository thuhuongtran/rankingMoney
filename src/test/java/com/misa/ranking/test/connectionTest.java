package com.misa.ranking.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.misa.ranking.connection.DBconnect;
import com.misa.ranking.entity.SlotGame;
import com.misa.ranking.entity.SlotGameRanking;
import com.misa.ranking.model.DataProcess;
import com.misa.ranking.pools.HikariPool;

/*
 * get all data
 * sort by hour of date
 * divide to small list limited by hour of date
 * then sort by win money
 *  player who fail will not be added in new list
 *  then combine those small list into one list limit 
 *  
 *  add all failure and winner in rank
 * failure moneyEarned will be set to 0
 *  
 * */
public class connectionTest {

	public static void main(String[] args) throws SQLException, IOException, ParseException {
		// testing ranking players by money earned
		/*
		List<SlotGame> slotGames = DBconnect.getSlotGameLi(DBconnect.getSlotGameDBbyID(1));
		List<SlotGameRanking> slotGamRankLi = DataProcess.getSlotGameRankLi(slotGames);
		List<SlotGameRanking> dividedLi = DataProcess.divideListByHour(slotGamRankLi);
		List<SlotGameRanking> playerRankLi = DataProcess.getPlayerRank(dividedLi);
		System.out.println("players ranking");
		for (SlotGameRanking slotR : playerRankLi) {
			System.out.println();
			System.out.print("\t" + slotR.getGameId());
			System.out.print("\t" + slotR.getId());
			System.out.print("\t" + slotR.getNickname());
			System.out.print("\t" + slotR.getMoneyEarned());
			System.out.print("\t" + slotR.getPrize());
			System.out.print("\t" + slotR.getTotalBet());
			System.out.print("\t" + slotR.getUpdateTime());
		}
		*/
		/*
		//testing timer tasks
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new Task(), 0, 2000);
		*/
		
		// testing connecting to database --connected
		/*
		HikariPool.init_writeDB();
		Connection connection = HikariPool.getConnection();
		String query = "SELECT `user_id`, `nickname`,`gameId`,`prize`,`totalBet`,`updateTime` "
				+ "FROM ranking.playerRanking_gameId2 ";
		// execute query
		PreparedStatement st = connection.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		// close the connection
		connection.close();
		
		while(rs.next()) {
			System.out.println(rs.getInt("user_id"));
			System.out.println(rs.getString("nickname"));
			System.out.println(rs.getInt("gameId"));
			System.out.println(rs.getLong("prize"));
			System.out.println(rs.getLong("totalBet"));
			System.out.println(rs.getString("updateTime"));
		}
		*/
		// testing writing on database
		
	}


}
