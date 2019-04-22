package org.itstep.util;

import java.util.Random;

public class Randomizer {
	
	public static int getRandomInt(int min, int max) {
		int diff = max - min;
		Random r = new Random();
		return r.nextInt(diff + 1) + min;
	}

}
