package com.misa.ranking.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.misa.ranking.entity.SlotGameRanking;
import com.misa.ranking.pools.HikariPool;

public class WriteDB {
	public static final int MYSQL_DUPLICATE_CODE = 1062;
	//write data on table winnerRankingId1
	// If insert duplicate values into table then next to an other one
	public static void write_onDB(List<SlotGameRanking> winnerRankLi, int gameId) throws SQLException, IOException {
		// connect to db
		HikariPool.init();
		Connection connection = HikariPool.getConnection();
		for(SlotGameRanking slotGamRank : winnerRankLi) {
			try {
				// execute query
				String query = "INSERT INTO `winnerRankingId"+gameId+"` (`user_id`,`nickname`,`winCash`,`updateTime`)"
						+ "VALUES (?,?,?,?)";
				PreparedStatement st = connection.prepareStatement(query);
				st.setLong(1, slotGamRank.getId());
				st.setString(2, slotGamRank.getNickname());
				st.setLong(3, slotGamRank.getMoneyEarned());
				st.setString(4, slotGamRank.getUpdateTime());
				
				st.executeUpdate();
			}
			catch(SQLException e) {
				if(e.getErrorCode()==MYSQL_DUPLICATE_CODE) {
					continue;
				}
			}
			
		}
		connection.close();
		
	}

}
