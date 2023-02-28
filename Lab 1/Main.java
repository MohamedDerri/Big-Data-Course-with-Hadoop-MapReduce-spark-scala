public class Main {
	public static void main(String[] args) {
		Mapper mp = new Mapper();
		mp.mapper(new BufferReader().wordsReader("testDataForW1D1.txt"));
		System.out.println(mp.pairs);
	}
}
