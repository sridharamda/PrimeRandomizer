package com.randstad;

import java.util.Random;

public class Randomizer {

	
	// Method  Return only positive numbers
	public Integer generateRandom() {

		return new Random().nextInt(Integer.SIZE - 1); 
	}

	// Random number with Range Takes Two Arguments 
	public Integer generateRandomwithRange(int start, int end) {

		// get the range, casting to long to avoid overflow problems
		long range = (long) start - (long) end + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * new Random().nextDouble());

		int randomNumber = (int) (fraction + start);

		return randomNumber;
	}

	//Random number with Max value 100
	public Integer generateRandomNumberWithLimit100() {

		return new Random().nextInt(100);
	}

}
