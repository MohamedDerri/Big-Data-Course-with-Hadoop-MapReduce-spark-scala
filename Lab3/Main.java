public class Main {
	public static void main(String[] args) {
		int numMappers = 5;
		int numReducers = 10;
	
		String[] text = {"this is my first text to test this main", "this is my second text to test this main", "this is my third text to test this main"
	, "this is my fourth text to test this main", "this is my fifth text to test this main"};
	
		WordCount wordCount = new WordCount(numMappers, numReducers);
		wordCount.mapShuffleReduce(text);
	}
}
