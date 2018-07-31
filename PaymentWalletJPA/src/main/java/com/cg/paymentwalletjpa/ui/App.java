package com.cg.paymentwalletjpa.ui;

import java.util.Scanner;

import com.cg.paymentwalletjpa.Exception.WalletException;
import com.cg.paymentwalletjpa.dto.Customer;
import com.cg.paymentwalletjpa.dto.Wallet;
import com.cg.paymentwalletjpa.service.IWalletService;
import com.cg.paymentwalletjpa.service.WalletServiceImpl;

public class App {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int choice1 = 0;

		do {
			System.out.println(" ");
			System.out.println("________________________Payment Wallet_____________________________");
			System.out.println("Enter 1 to Create Account");
			System.out.println("Enter 2 to Login");
			System.out.println("Enter 3 to Exit App");
			System.out.println("____________________________________________________________________");
			choice1 = scanner.nextInt();
			IWalletService service = new WalletServiceImpl();
			switch (choice1) {
			case 1:
				System.out.println("Enter your Phone Number");
				String phNum = scanner.next();
				System.out.println("Enter your Name");
				String name = scanner.next();
				System.out.println("Enter your Email ID");
				String emailId = scanner.next();
				System.out.println("Enter the userId to be created");
				String userId = scanner.next();
				System.out.println("Set your Password");
				String pass = scanner.next();
				System.out.println("Enter the Initial Amount to be Deposited");
				double balance = scanner.nextDouble();

				Customer customer= new Customer();
				customer.setPhNumber(phNum);
				customer.setName(name);
				customer.setEmailId(emailId);
				Wallet wallet=new Wallet();
				customer.setUserId(userId);
				wallet.setPassword(pass);
				wallet.setBalance(balance);
				wallet.setTransaction("aaa");
				customer.setWallet(wallet);

				boolean result = false;
				try {
					result = service.validateDetails(customer);
					service.createAccount(customer);
					System.out.println("Hello " + customer.getName());
					System.out.println("Your Account is Created Succesfully");
				} catch (WalletException e) {

					e.getMessage();
				}
				if (!result) {
					System.out.println("Cannot create account. Try Again");
				}
				break;
			case 2:
				System.out.println("Enter your UserID");
				String id = scanner.next();
				System.out.println("Enter your Password");
				String password = scanner.next();
				Customer loginWallet = new Customer();
				try {
					loginWallet = service.login(id, password);
					login(loginWallet);
				} catch (WalletException e) {
					e.getMessage();
				}
				break;
			case 3:
				System.out.println("Thank You for using Payment Wallet");
				break;
			default:
				System.out.println("Wrong choice! Try Again");
				break;
			}
		} while (choice1 != 3);
	}

	private static void login(Customer customer) {
		int choice2 = 0;
		System.out.println("Welcome " + customer.getName());
		do {
			System.out.println(" ");
			System.out.println("________________________Payment Wallet_____________________________");
			System.out.println(" 1 - Show Balance");
			System.out.println(" 2 - Withdraw Money from wallet");
			System.out.println(" 3 - Add Money to wallet");
			System.out.println(" 4 - Transfer Fund");
			System.out.println(" 5 - Print Transaction history");
			System.out.println(" 6 - LogOut");
			System.out.println("____________________________________________________________________");
			choice2 = scanner.nextInt();
			IWalletService service = new WalletServiceImpl();
			String userId = customer.getUserId();

			switch (choice2) {
			case 1:
				System.out.println("Your Account Balance is " + service.showBalance(userId));
				break;

			case 2:
				System.out.println("Enter the Amount to be Withdrawn");
				double withdrawAmount = scanner.nextDouble();
				if (service.withdraw(userId, withdrawAmount)) {
					System.out.println("Rupees " + withdrawAmount + " Withdrawn from your Wallet");
					System.out.println("Your Updates Account Balance is Rupees " + service.showBalance(userId));
				} else
					System.out.println("Insuffecient Balance");
				break;

			case 3:
				System.out.println("Enter the Amount to be Deposited");
				double depositAmount = scanner.nextDouble();
				if (service.deposit(userId, depositAmount)) {
					System.out.println("Rupees " + depositAmount + " Deposited to your Wallet");
					System.out.println("Your Updated Balance is Rupees " + service.showBalance(userId));
				} else
					System.out.println("Unable to deposit");
				break;

			case 4:
				System.out.println("Enter the UserId of Whom the fund is to be Transfered");
				String receiverUserId = scanner.next();
				System.out.println("Enter the amount to be Transfered");
				double transferAmount = scanner.nextDouble();
				try {
					if (service.fundTransfer(userId, receiverUserId, transferAmount)) {
						System.out.println("Rupees " + transferAmount + " Succesfully Transfered to " + receiverUserId);
						System.out.println("Your Updated Balance is Rupees " + service.showBalance(userId));
					}
				} catch (WalletException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				System.out.println("_______________________Transactions Details_________________________");
				String trans = service.printTransaction(userId);
				Scanner scanner=new Scanner(trans).useDelimiter("aaa");
				while (scanner.hasNext()) {
					String string=scanner.next();
					System.out.println(string);
				}
				System.out.println("____________________________________________________________________");
				break;
			case 6:
				System.out.println("__________________________Logged out________________________________");
				break;

			default:
				System.out.println("Wrong Choice! try Again");
				break;
			}
		} while (choice2 != 6);

	}
}
