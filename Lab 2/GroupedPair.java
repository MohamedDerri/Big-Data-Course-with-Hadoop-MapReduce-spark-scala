import java.util.ArrayList;
import java.util.List;

public class GroupedPair {
	private String key;
	private List<Integer> values;

	public GroupedPair(String key, List<Integer> values2) {
		this.key = key;
		this.values = values2;
	}

	

	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public List<Integer> getValues() {
		return values;
	}



	public void setValues(List<Integer> values) {
		this.values = values;
	}



	@Override
	public String toString() {
		return "[" + key + ", " + values + "]";
	}


}
