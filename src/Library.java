import java.util.ArrayList;
import java.util.List;

public class Library {
	protected Library() {
		super();
		listOfBooks = new ArrayList<Book>();
		signedUp = false;
		bookIndexesToScan = new ArrayList<Book>();
	}
	private List<Book> listOfBooks;
	private int signUpDays;
	private int numberOfBooksShippedInADay;
	private boolean signedUp;
	private int id;
	private List<Book> bookIndexesToScan;
	public List<Book> getListOfBooks() {
		return listOfBooks;
	}
	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}
	public int getSignUpDays() {
		return signUpDays;
	}
	public void setSignUpDays(int signUpDays) {
		this.signUpDays = signUpDays;
	}
	public int getNumberOfBooksShippedInADay() {
		return numberOfBooksShippedInADay;
	}
	public void setNumberOfBooksShippedInADay(int numberOfBooksShippedInADay) {
		this.numberOfBooksShippedInADay = numberOfBooksShippedInADay;
	}
	public boolean isSignedUp() {
		return signedUp;
	}
	public void setSignedUp(boolean signedUp) {
		this.signedUp = signedUp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Book> getBookIndexesToScan() {
		return bookIndexesToScan;
	}
	public void setBookIndexesToScan(List<Book> bookIndexesToScan) {
		this.bookIndexesToScan = bookIndexesToScan;
	}
}
