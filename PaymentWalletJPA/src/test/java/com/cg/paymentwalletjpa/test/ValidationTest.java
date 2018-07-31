package com.cg.paymentwalletjpa.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cg.paymentwalletjpa.Exception.WalletException;
import com.cg.paymentwalletjpa.dao.IWalletDao;
import com.cg.paymentwalletjpa.dao.WalletDaoImpl;
import com.cg.paymentwalletjpa.dto.Customer;
import com.cg.paymentwalletjpa.dto.Wallet;
import com.cg.paymentwalletjpa.service.IWalletService;
import com.cg.paymentwalletjpa.service.WalletServiceImpl;

public class ValidationTest {
	IWalletService service = new WalletServiceImpl();
	IWalletDao dao = new WalletDaoImpl();

	@Test
	public void CheckForZeroDeposittest() throws WalletException {
		boolean condition = false;
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789789");
		customer.setEmailId("ranjith@gmail.com");
		customer.setUserId("ranjith");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		dao.createAccount(customer);
		condition = service.deposit("ranjith", 0.0);
		assertFalse(condition);
	}

	@Test(expected = WalletException.class)
	public void CheckForInvalidNameTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("fd65f46");
		customer.setPhNumber("9789789789");
		customer.setEmailId("ranjith@gmail.com");
		customer.setUserId("ranjith");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		service.validateDetails(customer);
	}

	@Test
	public void CheckForValidNameTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789789");
		customer.setEmailId("ranjith@gmail.com");
		customer.setUserId("ranjith");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertTrue(condition);
	}

	@Test(expected = WalletException.class)
	public void CheckForInvalidPhoneNumberTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789");
		customer.setEmailId("abcd@gmail.com");
		customer.setUserId("ranjith");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertFalse(condition);
	}

	@Test
	public void CheckForValidPhoneNumberTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789789");
		customer.setEmailId("ranjith@gmail.com");
		customer.setUserId("ranjith");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertTrue(condition);
	}

	@Test(expected = WalletException.class)
	public void CheckForInvalidEmailTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789789");
		customer.setEmailId("4gfgaff");
		customer.setUserId("ranjith");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertFalse(condition);
	}

	@Test
	public void CheckForValidEmailTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789789");
		customer.setEmailId("ranjith@gmail.com");
		customer.setUserId("ranjith");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertTrue(condition);
	}

	@Test(expected = WalletException.class)
	public void CheckForInvalidUserId() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789789");
		customer.setEmailId("ranjith@gmail.com");
		customer.setUserId("abc");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertFalse(condition);

	}

	@Test
	public void CheckForValidUserId() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789789");
		customer.setEmailId("ranjith@gmail.com");
		customer.setUserId("abcde");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertTrue(condition);

	}

	@Test(expected = WalletException.class)
	public void CheckForInvalidassword() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789789");
		customer.setEmailId("ranjith@gmail.com");
		customer.setUserId("abcde");
		Wallet wallet = new Wallet();
		wallet.setPassword("123");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertFalse(condition);

	}

	@Test
	public void CheckForValidPassword() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Ranjith");
		customer.setPhNumber("9789789789");
		customer.setEmailId("ranjith@gmail.com");
		customer.setUserId("abcde");
		Wallet wallet = new Wallet();
		wallet.setPassword("12345678");
		wallet.setTransaction("aaa");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertTrue(condition);

	}
}
