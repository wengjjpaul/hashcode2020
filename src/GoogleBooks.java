import java.util.ArrayList;
import java.util.List;

public class GoogleBooks {
	protected GoogleBooks() {
		super();
		listOfLibraries = new ArrayList<Library>();
	}

	private List<Library> listOfLibraries;
	private int numberOfDaysForScanning;

	public List<Library> getListOfLibraries() {
		return listOfLibraries;
	}

	public void setListOfLibraries(List<Library> listOfLibraries) {
		this.listOfLibraries = listOfLibraries;
	}

	public int getNumberOfDaysForScanning() {
		return numberOfDaysForScanning;
	}

	public void setNumberOfDaysForScanning(int numberOfDaysForScanning) {
		this.numberOfDaysForScanning = numberOfDaysForScanning;
	}
}
