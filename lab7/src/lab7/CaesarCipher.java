package lab7;

public class CaesarCipher
{
	final static int A_CODE = 65;
	final static int Z_CODE = 90;
	
	public static String CaesarCode(String input, char offset)
	{
		char[] output = input.toUpperCase().toCharArray();
		int offsetNumber = Character.toUpperCase(offset)-'A';
		for(int i = 0; i < output.length; i++)
		{
			if(output[i] == ' ')
			{
				continue;
			}
			char swappedChar = (char)(output[i] + offsetNumber);
			while(swappedChar > Z_CODE)
			{
				swappedChar -= 26;
			}
			output[i] = swappedChar;
		}
		return new String(output);
	}
	
	public static String CaesarDecode(String input, char offset)
	{
		char[] output = input.toUpperCase().toCharArray();
		int offsetNumber = Character.toUpperCase(offset)-'A';
		for(int i = 0; i < output.length; i++)
		{
			if(output[i] == ' ')
			{
				continue;
			}
			char swappedChar = (char)(output[i] - offsetNumber);
			while(swappedChar < A_CODE)
			{
				swappedChar += 26;
			}
			output[i] = swappedChar;
		}
		return new String(output);
	}
	
	/*public static void main(String[] args)
	{
		CaesarCipher cipher = new CaesarCipher();
		System.out.println(cipher.CaesarCode("Ave Imperator moritvri te salutant", 't'));
		System.out.println(cipher.CaesarDecode("TOX BFIXKTMHK FHKBMOKB MX LTENMTGM", 't'));
	}*/
}
