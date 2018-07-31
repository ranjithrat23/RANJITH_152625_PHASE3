package com.cg.paymentwalletjpa.dao;

import com.cg.paymentwalletjpa.Exception.WalletException;
import com.cg.paymentwalletjpa.dto.Customer;

public interface IWalletDao {
	public void createAccount(Customer customer);

	public double showBalance(String userId);

	public void deposit(String userId, double amount);

	public void withdraw(String userId, double amount);

	public void fundTransfer(String userIdSender, String userIdReceiver, double amount) throws WalletException;

	public String printTransactions(String userId);

	public Customer login(String id, String password) throws WalletException;

	void beginTransaction();

	void commitTransaction();
}
