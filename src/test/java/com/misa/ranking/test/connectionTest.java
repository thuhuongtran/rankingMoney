package com.misa.ranking.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.misa.ranking.entity.SlotGame;
import com.misa.ranking.pools.HikariPool;

/*
 * get all data
 * sort by hour of date
 * divide to small list limited by hour of date
 * then sort by win money
 *  player who fail will not be added in new list
 *  then combine those small list into one list limit 
 * */
public class connectionTest {

	public static void main(String[] args) throws SQLException, IOException, ParseException {
		// testing connect to database
		HikariPool.init();
		Connection connection = HikariPool.getConnection();
		String query = "SELECT `id`, `nickname`,`gameId`,`prize`,`totalBet`,`updateTime`,`create_time` FROM misa.slot_game_data WHERE `gameId` =1";
		PreparedStatement st = connection.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		// System.out.println("result "+rs);
		List<SlotGame> slotGameLi = new ArrayList<SlotGame>();
		while (rs.next()) {
			SlotGame slotGame = new SlotGame();
			slotGame.setId(rs.getLong("id"));
			slotGame.setNickname(rs.getString("nickname"));
			slotGame.setGameId(rs.getInt("gameId"));
			slotGame.setPrize(rs.getLong("prize"));
			slotGame.setTotalBet(rs.getLong("totalBet"));
			slotGame.setUpdateTime(rs.getString("updateTime"));
			slotGame.setCreate_time(rs.getString("create_time"));
			slotGameLi.add(slotGame);
		}
		connection.close();
		System.out.println(slotGameLi.get(0).getUpdateTime());
		// get hour from date string
		
	}
}
