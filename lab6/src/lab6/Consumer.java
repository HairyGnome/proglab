package lab6;

public class Consumer extends Thread
{
	Fifo f;
	String s;
	int n;
	
	public Consumer(Fifo f, String s, int n)
	{
		this.f = f;
		this.s = s;
		this.n = n;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{				
				String read = f.get();
				System.out.println("consumed " + s + " " + read + " " + System.currentTimeMillis()%100000);
				sleep(n);
			}
			catch(InterruptedException ex)
			{
				return;
			}
		}
	}
	
}
