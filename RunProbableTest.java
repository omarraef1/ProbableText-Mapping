
// Omar R. Gebril 	SID: 23323978 	CSC210

// You may need this with larger input files with a map where seed length gets to 12:
// Select Run > Open Run Dialog
// Select the tab that has "(x=) Arguments"
// In the VM Arguments area, enter this text:  -XX:+AggressiveHeap
// Select Run
//
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class RunProbableTest {

	private static String text;
	private static OurMap<String, ArrayList<Character>> allNgrams = new OurMap<String, ArrayList<Character>>();

	public static void main(String[] args) {
		// Complete this program to generate probabalistic text.
		// by partitioning the problem into at least 4 methods.
		// Or write a new class that is constructed here with
		// the user input read in this main method.
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter book name: ");
		String name = keyboard.next();
		System.out.print("Enter ngram length: ");
		int nLength = keyboard.nextInt();
		System.out.print("How many letters? ");
		int letters = keyboard.nextInt();
		keyboard.close();
		try {
			text = readTheBook(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		buildTheMap(text, nLength);
		String randomNgram = setRandomSeed(text, nLength);
		printProbText(letters, nLength, randomNgram);
	}

	private static String readTheBook(String name) throws FileNotFoundException {
		StringBuilder text = new StringBuilder();
		Scanner fileText = new Scanner(new File(name));
		while (fileText.hasNextLine()) {
			text.append(fileText.nextLine() + " ");
		}
		return text.toString();
	}

	private static void buildTheMap(String text, int n) {
		for (int i = 0; i < text.length() - n; i++) {
			String nGram = text.substring(i, i + n);
			ArrayList<Character> vals = new ArrayList<Character>();
			vals.add(text.charAt(i + n));
			allNgrams.put(nGram, vals);
		}
	}

	private static String setRandomSeed(String text, int n) {
		Random gen = new Random();
		int randy = gen.nextInt(text.length() - n);
		String randomNgram = text.substring(randy, randy + n);
		return randomNgram;
	}

	private static void printProbText(int letters, int nLength, String ngram) {
		Random gen = new Random();
		String extr = "";
		int index = 0;
		while (index < letters) {
			String key = ngram.substring(index, index + nLength);
			if (allNgrams.get(key) != null) {
				ArrayList<Character> vals = allNgrams.get(key);
				int randy = gen.nextInt(vals.size());
				Character randomChar = vals.get(randy);
				ngram += randomChar;
				extr += randomChar;
				index++;
			}
		}
		int flag = 0;
		for (int i = 0; i < extr.length(); i++) {
			if (flag >= 60 && extr.charAt(i) == ' ') {
				flag = 0;
				System.out.print(" \n ");
			} else {
				System.out.print(extr.charAt(i));
				flag++;
			}
		}
	}
}