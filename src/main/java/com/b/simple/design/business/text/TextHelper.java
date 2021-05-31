package com.b.simple.design.business.text;

public class TextHelper {

	public String swapLastTwoCharacters(String str) {
		int length = str.length();
		if(length<2)
			return str;
		char lastString = chartAt(str,length-1);
		char secondLastString = chartAt(str,length-2);
		String restOfString = str.substring(0,length-2);
		return restOfString+lastString +secondLastString ;
	}

	public char chartAt(String text,int charIndex){
		char[] chars = text.toCharArray();
		for (int i=0;i<chars.length;i++
			 ) {
			if (i==charIndex){
				return chars[i];
			}
		}

		throw new StringIndexOutOfBoundsException(charIndex);
	}

	public String truncateAInFirst2Positions(String str) {

		int length = str.length();
		String CHAR_A="A";

		if(length<2)
			return str.replace(CHAR_A,"");

		String firtTwoPosition= str.substring(0,2).replace(CHAR_A,"");
		String restOfString = str.substring(2);

		return firtTwoPosition+restOfString ;
	}
}
