package lambeer;

import java.util.Comparator;

public class NameComparator implements Comparator<Beer>
{
	@Override
	public int compare(Beer o1, Beer o2) {
		return o1.GetName().toLowerCase().compareTo(o2.GetName().toLowerCase());
	}

}
