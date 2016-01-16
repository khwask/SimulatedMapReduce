package jp.ac.nii.exercise3;

import java.util.List;

import jp.ac.nii.mapreduceframework.Context;
import jp.ac.nii.mapreduceframework.Mapper;

public class JapaneseWordCountMapper extends Mapper<Long, String, String, Integer> {

	@Override
	protected void map(Long key, String line, Context context) {
		List<String> words = Tokenizer.tokenize(line);
		for (String word : words) {
			context.write(word, 1);
		}
	}
}
