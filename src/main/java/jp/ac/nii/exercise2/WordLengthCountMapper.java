package jp.ac.nii.exercise2;

import jp.ac.nii.mapreduceframework.Context;
import jp.ac.nii.mapreduceframework.Mapper;

public class WordLengthCountMapper extends Mapper<Long, String, Integer, Integer> {

	@Override
	protected void map(Long key, String line, Context context) {
		String[] words = line.split(" ");
		for (String word : words) {
			if (isWord(word)) {
				context.write(word.length(), 1);
			}
		}
	}

	private boolean isWord(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (!Character.isAlphabetic(word.charAt(i))) {
				return false;
			}
		}
		return word.length() > 0;
	}
}
