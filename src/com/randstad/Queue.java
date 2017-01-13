package com.randstad;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Queue {

	private BlockingQueue<PrimeModel> queue = new ArrayBlockingQueue<PrimeModel>(10); // Implemented Blocking Queue for better Performance 
	private PrimeModel pm = new PrimeModel();

	public static void main(String[] args) throws InterruptedException {

		Queue queue = new Queue();

		System.out.println("Enter Range Number to Check Prime or Not :");
		Scanner scanner = new Scanner(System.in);
		String value = scanner.nextLine();
		int typeConvValue = Integer.parseInt(value);
		if (typeConvValue == 0) {
			System.out.println("Please enter number > 0 ");
		}
		if (typeConvValue < 0) {
			System.out.println("Please enter  + ve  ");
		} else {
			queue.queueGeneration(typeConvValue);
		}
		scanner.close();
	}

	public void queueGeneration(int value) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					primeNumberRandomProducer(value);
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					statusPrimeNumber(value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

	public void primeNumberRandomProducer(int value) throws InterruptedException {

		for (int i = 0; i < value; i++) {
			Randomizer random = new Randomizer();
			//int randomNumber = random.generateRandom(); // any random number 
			int randomNumber = random.generateRandomNumberWithLimit100();
			boolean isPrime = PrimeRandomizer.generateRandom(randomNumber);
			pm.setPrimeNumber(randomNumber);
			pm.setPrime(isPrime);
			System.out.println("Random Number Generated : " + pm.getPrimeNumber());
			queue.put(pm);
			Thread.sleep(1);
		}
	}

	public void statusPrimeNumber(int value) throws InterruptedException {
		for (int i = 0; i < value; i++) {
			PrimeModel pm = queue.take();
			System.out.println("Generated  Random Number Taken From Queue :" + pm.getPrimeNumber() + "-----"
					+ " Checking number is Prime  ?? :" + pm.isPrime());
		}

	}
}