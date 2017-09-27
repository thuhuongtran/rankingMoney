package com.misa.ranking.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.misa.ranking.entity.SlotGame;
import com.misa.ranking.pools.HikariPool;

public class DBconnect {
	// return resultSet of SlotGame database table 
	public static ResultSet getSlotGameDBbyID(int gameId) throws SQLException, IOException {
		// connect to db
		HikariPool.init_getDB();
		Connection connection = HikariPool.getConnection();
		String query = "SELECT `id`, `nickname`,`gameId`,`prize`,`totalBet`,`updateTime`,`create_time` "
				+ "FROM misa.slot_game_data WHERE `gameId` =?";
		// execute query
		PreparedStatement st = connection.prepareStatement(query);
		st.setInt(1, gameId);
		ResultSet rs = st.executeQuery();
		// close the connection
		connection.close();
		return rs;
	}
	
	// return list of slotGame database table
	public static List<SlotGame> getSlotGameLi(ResultSet rs) throws SQLException{
		List<SlotGame> slotGameLi = new ArrayList<SlotGame>();
		while(rs.next()) {
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
		return slotGameLi;
	}
}
