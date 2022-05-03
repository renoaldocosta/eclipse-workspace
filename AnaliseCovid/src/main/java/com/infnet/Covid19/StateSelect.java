package com.infnet.Covid19;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StateSelect {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Começando programa");

		// Read from csv file
		String path = "/home/renoaldo/Downloads/caso_full.csv";
		Scanner sc = new Scanner(new File(path));

		// Create a list with the raw Data
		List<String> list = new ArrayList<String>();

		String row;
		String[] pattern = { 
				"AL",
				"AM",
				"AP",
				"BA",
				"CE",
				"MG",
				"MT",
				"PA",
				"PE",
				"PI",
				"PR",
				"RJ",
				"RR",
				"SC",
				"SE",
				"SP"
				};
		Pattern r;
		Matcher m;
		int cont = 0;
		list.add(sc.nextLine());
		while (sc.hasNext()) {
			row = sc.nextLine();
			for (String pat : pattern) {
				System.out.println("Inicio fase ");
				r = Pattern.compile(pat);
				m = r.matcher(row);
				if (m.find()) {
					cont++;
					// System.out.println("ok");
					list.add(row);
				}
				//System.out.println("Comprimento ate agora: " + list.size());
			}
			System.out.println("Match: " + cont);
			System.out.println("After: " + list.size());
		}

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //Filter by target states String[] pattern = {",CE,", ",SE,",",SP,",",RS,"};
		 * Pattern r; Matcher m;
		 * 
		 * System.out.println("Before: "+text.size());
		 * 
		 * int cont =0; for(String pat:pattern) { System.out.println("Inicio fase "); r
		 * = Pattern.compile(pat); for (int i = 0; i< text.size();i++) { m =
		 * r.matcher(text.get(i)); if(m.find()) { cont++; //System.out.println("ok");
		 * list.add(text.get(i)); i--; } }
		 * System.out.println("Comprimento ate agora: "+list.size()); }
		 * System.out.println("Match: "+ cont); System.out.println("After: "+
		 * text.size());
		 */
		// Save to csv file
		File csvFile = new File("meucsv.csv");
		PrintWriter out = new PrintWriter(csvFile);
		for (String row_ : list) {
			out.println(row_);
		}

		// Total 3 Estados "SP", "SE", "RJ" - 569298
		// Total 4 Estados "CE", "SE","SP","RS" -
		// 501846

	}

}
