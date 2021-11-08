package lab6;

import java.util.Random;

public class Application
{
	public static void main(String[] args) throws InterruptedException
	{
		Fifo fifo = new Fifo();
		
		Random generator = new Random();
		
		/*Thread producerThread1 = new Thread(new Producer("Prod1", fifo, generator.nextInt(2000)));
		Thread producerThread2 = new Thread(new Producer("Prod2", fifo, generator.nextInt(2000)));
		Thread producerThread3 = new Thread(new Producer("Prod3", fifo, generator.nextInt(2000)));
		
		Consumer consumerThread1 = new Consumer(fifo, "Con1", generator.nextInt(2000));
		Consumer consumerThread2 = new Consumer(fifo, "Con2", generator.nextInt(2000));
		Consumer consumerThread3 = new Consumer(fifo, "Con3", generator.nextInt(2000));
		Consumer consumerThread4 = new Consumer(fifo, "Con3", generator.nextInt(2000));*/
		
		Thread producerThread1 = new Thread(new Producer("Prod1", fifo, 100));
		
		Consumer consumerThread4 = new Consumer(fifo, "Con3", 300);
		
		producerThread1.start();
		/*producerThread2.start();
		producerThread3.start();*/
		
		/*consumerThread1.start();
		consumerThread2.start();
		consumerThread3.start();*/
		consumerThread4.start();
	}
}
