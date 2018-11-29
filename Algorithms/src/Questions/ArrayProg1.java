package Questions;

import java.util.Map;
import java.util.TreeMap;

public class ArrayProg1
{

	public static void main(String[] args)
	{
		String[] arr = { "Manish", "Mohit", "Harsh", "Manish", "Mohit", "Harsh",
				"Manish", "AA", "AA", "AA" };
		TreeMap<String, Integer> m = new TreeMap<>();
		for (int i = 0; i < arr.length; ++i)
		{
			if (!m.containsKey(arr[i].toLowerCase()))
			{
				m.put(arr[i].toLowerCase(), 1);
			}
			else
			{
				m.put(arr[i].toLowerCase(), m.get(arr[i].toLowerCase()) + 1);
			}
		}

		int max = 0;
		String key = null;
		for (Map.Entry<String, Integer> mEnt : m.entrySet())
		{
			if (mEnt.getValue() > max)
			{
				max = mEnt.getValue();
				key = mEnt.getKey();
			}
		}

		System.out.println(key + "  " + max);
	}

}
