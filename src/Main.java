import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		GoogleBooks googleBooks = FileManager.readFile("b_read_on.txt");

		solve(googleBooks);

	}

	private static void solve(GoogleBooks googleBooks) {

		List<Integer> listOfSignedUpLibaries = new ArrayList<>();

		for (int i = googleBooks.getNumberOfDaysForScanning(); i > 0;) {
			System.out.println("Left scanning days: " + i);

			Library bestLibrary = findBestLibraryToSignup(googleBooks, i);

			if (bestLibrary == null) {
				break;
			}
			System.out.println(
					"Days to register library: " + bestLibrary.getSignUpDays());

			i = i - bestLibrary.getSignUpDays();

			listOfSignedUpLibaries.add(bestLibrary.getId());
		}

		System.out.println("Signed up libraries");
		System.out.println(listOfSignedUpLibaries);

		for (int i = 0; i < listOfSignedUpLibaries.size(); i++) {
			Library library = googleBooks.getListOfLibraries()
					.get(listOfSignedUpLibaries.get(i));
			Collections.sort(library.getListOfBooks(),
					(a, b) -> a.getScore() > b.getScore()
							? -1
							: a.getScore() == b.getScore() ? 0 : 1);
		}

		FileManager.writeToFile(listOfSignedUpLibaries, googleBooks);

	}

	private static Library findBestLibraryToSignup(GoogleBooks googleBooks,
			int daysToScan) {

		int bestScannedLibraryCount = 0;
		Library bestLibrary = null;

		for (Iterator library = googleBooks.getListOfLibraries()
				.iterator(); library.hasNext();) {
			Library libraryElement = (Library) library.next();

			if (!libraryElement.isSignedUp()) {
				int daysLeftToScan = daysToScan
						- libraryElement.getSignUpDays();

				int numberOfBooksPossibleToScan = daysLeftToScan
						* libraryElement.getNumberOfBooksShippedInADay();

				// if (numberOfBooksPossibleToScan > libraryElement
				// .getListOfBooks().size()) {
				// numberOfBooksPossibleToScan = libraryElement
				// .getListOfBooks().size();
				// }

				// System.out.println("Number of books possible to scan: " +
				// numberOfBooksPossibleToScan);

				Collections.sort(libraryElement.getListOfBooks(),
						(a, b) -> a.getScore() > b.getScore()
								? -1
								: a.getScore() == b.getScore() ? 0 : 1);

				int bestScore = 0;
				int booksScanned = 0;
				List<Book> bookIndexesToScan = new ArrayList<>();
				for (int bookToScanIndex = 0; bookToScanIndex < libraryElement
						.getListOfBooks().size(); bookToScanIndex++) {
					if (booksScanned >= numberOfBooksPossibleToScan) {
						break;
					}
					// System.out.println("Book score: " + libraryElement
					// .getListOfBooks().get(bookToScanIndex).getScore());

					if (!libraryElement.getListOfBooks().get(bookToScanIndex)
							.isScanned()) {
						bestScore = bestScore + libraryElement.getListOfBooks()
								.get(bookToScanIndex).getScore();
						booksScanned++;
						bookIndexesToScan.add(libraryElement.getListOfBooks()
								.get(bookToScanIndex));

					}

				}
				if (bestScore == 0) {
					continue;
				}
				bestScore = bestScore / libraryElement.getSignUpDays();

				// System.out.println();



				if (bestScannedLibraryCount < bestScore) {
					bestScannedLibraryCount = bestScore;
					bestLibrary = libraryElement;
					bestLibrary.setBookIndexesToScan(bookIndexesToScan);



					// System.out.println("numberOfBooksPossibleToScan: " +
					// numberOfBooksPossibleToScan);
					// System.out.println("size of library: " +
					// libraryElement.getListOfBooks().size());
					// if(numberOfBooksPossibleToScan >
					// libraryElement.getListOfBooks().size()) {
					// System.out.println("Idle library");
					// }
				}
			}
		}
		
		if (bestScannedLibraryCount == 0) {
			System.out.println("Best Score: " + bestScannedLibraryCount);
		}
		System.out.println("Best Score: " + bestScannedLibraryCount);
		if(bestLibrary != null) {
			for (Iterator book2 = bestLibrary.getBookIndexesToScan()
					.iterator(); book2.hasNext();) {
				Book book3 = (Book) book2.next();
				book3.setScanned(true);
			}
			bestLibrary.setSignedUp(true);	
		}
		

		return bestLibrary;
	}

}
