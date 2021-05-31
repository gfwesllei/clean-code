package com.d.tdd;

public class StringHelper {

	public String replaceAInFirst2Positions(String str) {

		if(str.length()<2)
			return replaceAFromString(str);

		String first2Chars = str.substring(0, 2);
		String restOfTheString = str.substring(2);

		return replaceAFromString(first2Chars) + restOfTheString;
	}

	private String replaceAFromString(String str) {
		return str.replace("A", "");
	}

	public boolean areFirstTwoAndLastTwoCharsTheSame(String str) {
		
		if(str.length()<2)
			return false;
		
		String first2Chars = str.substring(0, 2);
		String last2Chars = str.substring(str.length() - 2);

		return first2Chars.equals(last2Chars);
	}

}
