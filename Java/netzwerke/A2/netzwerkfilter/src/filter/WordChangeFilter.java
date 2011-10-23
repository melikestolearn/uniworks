package filter;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.IOException;

public class WordChangeFilter extends BufferedReader {
	String word;
	String changedWord;
	public WordChangeFilter(Reader in,String word,String changedWord) {
		super(in);
		this.word = word;
		this.changedWord = changedWord;
	}
	
	public String readLine()throws IOException{
		String str = super.readLine();
		if(str!=null && str.contains(word))
			str = str.replace(word, changedWord);
		return str;
	}
}