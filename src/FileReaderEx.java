

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class FileReaderEx {

	public static void main(String[] args) {
		int[] data = readFiles("VehicleDataFile.txt");
		// System.out.println(Arrays.toString(data));
		/*
		 * for (int i = 0; i < data.length; i++) { System.out.println(data[i]); }
		 */
	}

	public static int[] readFiles(String file) {
		try {
			File f = new File(file);
			Scanner s = new Scanner(f);
			int ctr = 0;
			while (s.hasNextInt()) {
				ctr++;
				s.nextInt();
			}
			int[] arr = new int[ctr];
			Scanner s1 = new Scanner(f);
			for (int i = 0; i < arr.length; i++) {
				arr[i] = s1.nextInt();
				int x = arr[0];
				int y = arr[1];
				System.out.println(arr[i]);
			}
			return arr;
		} catch (Exception e) {
			return null;
		}
	}
}
