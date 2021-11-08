package junitlab.bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;

public class BankTest
{
	Bank bank;
	@Before
	public void setup()
	{
		bank = new GreatSavingsBank();
	}
	
	@Test
	public void testOpenAccount() throws Exception
	{
		String account = bank.openAccount();
		long balance = bank.getBalance(account);
		org.junit.Assert.assertEquals(0, balance);
	}
	
	@Test
	public void testUniqueAccount()
	{
		String account1 = bank.openAccount();
		String account2 = bank.openAccount();
		org.junit.Assert.assertNotEquals(account1, account2);
	}
	
	@Test(expected = AccountNotExistsException.class)
	public void testInvalidAccount() throws Exception
	{
		bank.getBalance("almáspite");
	}
	
	@Test
	public void testDeposit() throws Exception
	{
		String account = bank.openAccount();
		bank.deposit(account, 2000);
		long balance = bank.getBalance(account);
		org.junit.Assert.assertEquals(2000, balance);
	}
}
