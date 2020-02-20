import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

	public static GoogleBooks readFile(String filename) {

		Scanner fi = null;
		try {
			fi = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		GoogleBooks googleBooks = new GoogleBooks();

		int numberOfBooks = fi.nextInt();
		int numberOfLibraries = fi.nextInt();
		int numberOfDaysForScanning = fi.nextInt();
		googleBooks.setNumberOfDaysForScanning(numberOfDaysForScanning);
		List<Book> listOfAllBooks = new ArrayList<>();

		int totalScore = 0;
		for (int i = 0; i < numberOfBooks; i++) {
			Book book = new Book();
			book.setId(i);
			book.setScore(fi.nextInt());
			listOfAllBooks.add(book);
			totalScore = totalScore + book.getScore();
		}

		System.out.println("Total Score: " + totalScore);

		for (int i = 0; i < numberOfLibraries; i++) {
			Library library = new Library();

			library.setId(i);
			int numberOfBooksInLibrary = fi.nextInt();
			library.setSignUpDays(fi.nextInt());
			library.setNumberOfBooksShippedInADay(fi.nextInt());

			List<Book> listOfBooks = new ArrayList<Book>();

			for (int j = 0; j < numberOfBooksInLibrary; j++) {
				listOfBooks.add(listOfAllBooks.get(fi.nextInt()));
			}
			library.setListOfBooks(listOfBooks);
			googleBooks.getListOfLibraries().add(library);
		}

		return googleBooks;

	}

	public static void writeToFile(List<Integer> listOfSignedUpLibaries,
			GoogleBooks googleBooks) {

		int score = 0;

		String filename = (new StringBuilder()).append("Result_")
				.append(LocalDateTime.now().format(
						DateTimeFormatter.ofPattern("YYYY-MM-dd_hh-mm-ss")))
				.toString();

		System.out.println(filename);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(
					new FileWriter("./" + filename + ".txt", true));

			// Write output here
			writer.write(String.valueOf(listOfSignedUpLibaries.size()));
			writer.newLine(); // Add new line

			for (int i = 0; i < listOfSignedUpLibaries.size(); i++) {
				writer.write(String.valueOf(listOfSignedUpLibaries.get(i)));
				writer.write(" ");
				writer.write(String.valueOf(googleBooks.getListOfLibraries()
						.get(listOfSignedUpLibaries.get(i))
						.getBookIndexesToScan().size()));
				writer.newLine();

				// Write books indexes to scan
				for (int j = 0; j < googleBooks.getListOfLibraries()
						.get(listOfSignedUpLibaries.get(i))
						.getBookIndexesToScan().size(); j++) {
					writer.write(String.valueOf(googleBooks.getListOfLibraries()
							.get(listOfSignedUpLibaries.get(i))
							.getBookIndexesToScan().get(j).getId()));
					score = score + googleBooks.getListOfLibraries()
							.get(listOfSignedUpLibaries.get(i))
							.getBookIndexesToScan().get(j).getScore();
					writer.write(" ");
				}
				writer.newLine();

			}

			writer.close();
			System.out.println(score);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
