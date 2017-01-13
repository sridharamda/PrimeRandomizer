package com.randstad;

public class PrimeRandomizer {

	public static boolean generateRandom(int num) {

		boolean isPrime = true;

		for (int i = 2; i < num; i++) {    // Logic to find out prime number
			if (num % i == 0) {
				isPrime = false;
				break;
			}
		}

		return isPrime;

	}

}
