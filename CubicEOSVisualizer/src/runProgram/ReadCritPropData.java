package runProgram;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import static java.nio.file.AccessMode.*;

public class ReadCritPropData
{
	public static ArrayList<String> compNameArray = new ArrayList();
	public static ArrayList<Float> tcArray = new ArrayList();
	public static ArrayList<Float> pcArray = new ArrayList();
	public static ArrayList<Float> wArray = new ArrayList();
	
	public static String[] compNameArray2;
	public static Float[] tcArray2;
	public static Float[] pcArray2;
	public static Float[] wArray2;
	
	public static void ReadData()
	{
		Path file = Paths.get("C:\\Users\\Matt\\workspace\\CubicEOSVisualizer\\Critical Properties.txt");
		String[] array = new String[4];
		String s = "";
		String delimeter = "\\t";
		try
		{
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			System.out.println();
			s = reader.readLine();

				while(s != null)
				{
					if(s.charAt(0) == '=')
					{
						compNameArray.add(s);
						tcArray.add(Float.NaN);
						pcArray.add(Float.NaN);
						wArray.add(Float.NaN);
					}
					else
					{
					array = s.split(delimeter);
					compNameArray.add(array[0]);
					tcArray.add(Float.parseFloat(array[1]));
					pcArray.add(Float.parseFloat(array[2]));
					wArray.add(Float.parseFloat(array[3]));
					}
					s = reader.readLine();
				}
				reader.close();
		}
		catch(Exception e)
		{
			System.out.println("Message: " + e);
		}
		
		compNameArray2 = new String[compNameArray.size()];
		tcArray2 = new Float[tcArray.size()];
		pcArray2 = new Float[pcArray.size()];
		wArray2 = new Float[wArray.size()];
		
		for(int i = 0; i < compNameArray.size(); i++)
		{
			compNameArray2[i] = compNameArray.get(i);
		}
		for(int i = 0; i < tcArray.size(); i++)
		{
			tcArray2[i] = tcArray.get(i);
		}
		for(int i = 0; i < pcArray.size(); i++)
		{
			pcArray2[i] = pcArray.get(i);
		}
		for(int i = 0; i < wArray.size(); i++)
		{
			wArray2[i] = wArray.get(i);
		}
	}
}
