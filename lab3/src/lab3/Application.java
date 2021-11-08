package lab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Application
{
	
	static File wd;
	
	public static void exit(String[] cmd)
	{
		System.exit(0);
	}
	
	public static void pwd(String[] cmd)
	{
		String folder = System.getProperty("user.dir");
		File dir = new File(folder);
		System.out.println(folder);
		System.out.println(dir.listFiles().length);
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String[] cmd = reader.readLine().split(" ");
			if(cmd[0].equals("exit"))
			{
				exit(cmd);
			}
			else
			{
				if(cmd[0].equals("pwd"))
				{
					pwd(cmd);
				}
			}
		}
	}
}
