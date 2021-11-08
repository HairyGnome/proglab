package lab6;

public class Producer implements Runnable
{
	String message;
	Fifo fifo;
	int n;
	
	public Producer(String message, Fifo fifo, int n)
	{
		this.message = message;
		this.fifo = fifo;
		this.n = n;
	}
	
	public void go()
	{
		int i = 0;
		while(true)
		{
			try
			{
				String produced = message + " " + i;
				fifo.put(produced);
			}
			catch(InterruptedException ex)
			{
				return;
			}
			System.out.println("produced " + message + " " + i + " " + System.currentTimeMillis()%100000);
			i++;
			try
			{				
				Thread.sleep(n);
			}
			catch(InterruptedException ex)
			{
				return;
			}
		}
	}
	
	@Override
	public void run()
	{
		go();
	}
}
