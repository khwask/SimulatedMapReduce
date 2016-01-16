package jp.ac.nii.exercise4;

import jp.ac.nii.mapreduceframework.Context;
import jp.ac.nii.mapreduceframework.Reducer;

/**
 * このファイルは完成です！
 */
public class StandardDeviationCalculationReducer extends Reducer<String, Integer, String, Double> {
	@Override
	protected void reduce(String key, Iterable<Integer> values, Context context) {
		// 分散を計算しよう
		// ヒント1: context.getConfiguration().get() メソッドを使おう！
		// ヒント2: Excercise4Main.createConfiguration()メソッドをよく読もう！
		
		// 平均値
		double average = Double.parseDouble(context.getConfiguration().get(key));

		double sum = 0;
		int count = 0;

		for (Integer value : values) {
			sum += (value - average) * (value - average);
			count++;
		}
		
		// 分散
		double variance = sum / count;
		// 標準偏差
		double standardDeviation = Math.sqrt(variance);
		
		context.write(key, standardDeviation);
	}
}
