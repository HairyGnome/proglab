package junitlab.bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;

public class BankTestFixtureFirstNation
{
	
	Bank bank;
	String account1;
	String account2;
	
	@Before
	public void setup() throws Exception
	{
		bank = new FirstNationalBank();
		
		account1 = bank.openAccount();
		account2 = bank.openAccount();
		
		bank.deposit(account1, 1500);
		bank.deposit(account2, 12000);
	}
	
	@Test
	public void testTransfer() throws Exception
	{
		bank.transfer(account2, account1, 3456);
		org.junit.Assert.assertEquals(4956, bank.getBalance(account1));
		org.junit.Assert.assertEquals(8544, bank.getBalance(account2));
	}
	
	@Test(expected = NotEnoughFundsException.class)
	public void testTransferWithoutEnoughFunds() throws Exception
	{
		bank.transfer(account1, account2, 3456);
	}

}
