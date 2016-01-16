package jp.ac.nii.exercise2;

import jp.ac.nii.mapreduceframework.Context;
import jp.ac.nii.mapreduceframework.Reducer;

public class WordLengthCountReducer extends Reducer<Integer, Integer, Integer, Integer> {

	@Override
	protected void reduce(Integer key, Iterable<Integer> values, Context context) {
		int sum = 0;
		for (Integer value : values) {
			sum += value;
		}
		context.write(key, sum);
	}
}
