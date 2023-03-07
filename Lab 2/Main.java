import javax.sound.midi.Soundbank;

public class Main {
	public static void main(String[] args) {
		Mapper mapper = new Mapper();
		mapper.map(new BufferReader().wordsReader("Dummy Data.txt"));
		// System.out.println(mapper.mapped);
		Reducer reducer = new Reducer();
		reducer.reduce(mapper.mapped);
		reducer.printGroupedPairs();
		reducer.printSumTheGroupedPairs();
	}
}
