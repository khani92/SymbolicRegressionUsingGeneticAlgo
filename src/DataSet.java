import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataSet {

	List<DataRow> dataRowList = new ArrayList<>();

	public void getDataFromFile(String fileName) {

		try (Scanner scannerObject = new Scanner(new File(fileName));) {

			scannerObject.useDelimiter("([ ]+)|\n");
			int numberofVar = scannerObject.nextInt();
			//To skip num of rows. We're not using it in our code below
			scannerObject.next().trim();
			DataRow dR = null;

			while (scannerObject.hasNext()) {
				dR = new DataRow();
				dR.setY(Double.valueOf(scannerObject.next()));

				double[] valuesOfX = new double[numberofVar];

				for (int j = 0; j < numberofVar; j++) {
					valuesOfX[j] = Double.valueOf(scannerObject.next());
				}
				dR.setxValues(valuesOfX);
				dataRowList.add(dR);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
