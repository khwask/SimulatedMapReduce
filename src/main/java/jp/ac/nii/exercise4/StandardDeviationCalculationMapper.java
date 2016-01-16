package jp.ac.nii.exercise4;

import jp.ac.nii.mapreduceframework.Context;
import jp.ac.nii.mapreduceframework.Mapper;

/**
 * このファイルは完成です！
 */
public class StandardDeviationCalculationMapper extends Mapper<Long, String, String, Integer> {
	@Override
	protected void map(Long key, String line, Context context) {
		// 科目と点数のキーバリューに変換しよう
		String[] words = line.split(",");
		context.write(words[0], Integer.parseInt(words[1]));
	}
}
