package jp.ac.nii.exercise5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import com.google.common.collect.Maps;

import jp.ac.nii.mapreduceframework.Context;
import jp.ac.nii.mapreduceframework.Reducer;

/**
 * このファイルは完成です！
 */
public class StandardDeviationCalculationReducer extends Reducer<String, Integer, String, Double> {
	private Map<String, Double> subject2Average = Maps.newHashMap();

	@Override
	protected void setup(Context context) {
		// 注意：以下は分散キャッシュ（DistributedCache）から平均値の計算結果の読み込みを行なっているが、本家Hadoopとは処理が大きく異なります！
		try {
			FileInputStream intput = new FileInputStream("exercise5_average.tsv");
			Scanner scanner = new Scanner(intput, "UTF-8");
			while (scanner.hasNextLine()) {
				// subject2Averageに科目名と平均値のキーバリューを保存しよう
				String[] keyValue = scanner.nextLine().split("\t");
				subject2Average.put(keyValue[0], Double.parseDouble(keyValue[1]));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void reduce(String key, Iterable<Integer> values, Context context) {
		// 分散を計算しよう
		// ヒント: subject2Average フィールドを使おう！

		// 平均値
		double average = subject2Average.get(key);

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
