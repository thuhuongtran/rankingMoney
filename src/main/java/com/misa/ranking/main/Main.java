package com.misa.ranking.main;

import java.util.Timer;

public class Main {

	public static void main(String[] args){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new Task(), 0, 3600000);
		System.out.println("Success!");
	}

}
