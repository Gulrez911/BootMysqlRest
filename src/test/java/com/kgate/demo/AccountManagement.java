package com.kgate.demo;

import java.util.Scanner;

public class AccountManagement {
	private float currentBalance = 15000f;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter amount to add in your balance: ");
		float newAmount = scan.nextFloat();

		try {
			float totalAmount = new AccountManagement().addAmount(newAmount);
			System.out.println("Total Account Balance: " + totalAmount);
		} catch (AccountBalanceException e) {
			float fdAmount = e.getAccountBalance() - 2000;
			System.out.println("Your account balance is more than 20K now, So creating FD of Amount: "
					+ fdAmount);
			new AccountManagement().createFixDeposit(fdAmount);
			System.out.println("Account Balance = " + 20000);
		}
	}

	public float addAmount(float amount) throws AccountBalanceException {
		float total = currentBalance + amount;
		if (total > 20000) {
			throw new AccountBalanceException(total);
		}
		return total;
	}

	public void createFixDeposit(float fxAmount) {
		// Implimentation of FD creation
	}
}

class AccountBalanceException extends Exception {
	private float accountBalance;

	public AccountBalanceException(float f) {
		super();
		this.accountBalance = f;
		// TODO Auto-generated constructor stub
	}

	public AccountBalanceException(String message) {
		super(message);
	}

	public float getAccountBalance() {
		return accountBalance;
	}

}