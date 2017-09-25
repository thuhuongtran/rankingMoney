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
 * */
public class connectionTest {

	public static void main(String[] args) throws SQLException, IOException, ParseException {
		
		// testing sublist
		List<Integer> intli = new ArrayList<Integer>();
		intli.add(0);
		intli.add(1);
		intli.add(2);
		intli.add(3);
		intli.add(4);
		intli.add(5);
		intli.add(6);
		
		List<Integer> smallLi = intli.subList(0, 6);
		for(Integer i : smallLi) {
			System.out.println(i);
		}
		System.out.println("index: "+intli.indexOf(3));
	}
}
