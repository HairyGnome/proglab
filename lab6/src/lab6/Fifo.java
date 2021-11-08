package lab6;

import java.util.LinkedList;

public class Fifo
{
	final int MAX_ELEMENTS = 10;
	LinkedList<String> list = new LinkedList<String>();
	
	synchronized public void put(String stringToAdd) throws InterruptedException
	{
		while(list.size() >= MAX_ELEMENTS)
		{				
			wait();
		}
		notifyAll();
		list.add(stringToAdd);
		System.out.println("put " + Thread.currentThread().getId());
	}
	
	synchronized public String get() throws InterruptedException
	{
		while(list.size() < 1)
		{
			wait();
		}
		notifyAll();
		String firstElement = list.get(0);
		list.remove(0);
		System.out.println("get " + Thread.currentThread().getId());
		return firstElement;
	}
}
