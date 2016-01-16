package jp.ac.nii.exercise4;

import jp.ac.nii.mapreduceframework.Context;
import jp.ac.nii.mapreduceframework.Reducer;

/**
 * このファイルは完成です！
 */
public class AverageCalculationReducer extends Reducer<String, Integer, String, Double> {
	@Override
	protected void reduce(String key, Iterable<Integer> values, Context context) {
		// 平均値を計算しよう
		double sum = 0;
		int count = 0;
		for (Integer value : values) {
			sum += value;
			count++;
		}
		context.write(key, sum / count);
	}
}
