
public class Book {
	
	protected Book() {
		super();
		scanned = false;
	}
	private int id;
	private int score;
	private boolean scanned;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isScanned() {
		return scanned;
	}
	public void setScanned(boolean scanned) {
		this.scanned = scanned;
	}
}
