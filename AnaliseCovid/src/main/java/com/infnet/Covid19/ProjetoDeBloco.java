package com.infnet.Covid19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.lang.model.element.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class ProjetoDeBloco {

	private static JavaSparkContext sc;

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("AnaliseCovid").setMaster("local");
		sc = new JavaSparkContext(conf);

		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.WARN);

		String pathfile = 
				new String("hdfs://localhost:9000/user/renoaldo/database/COVID19/casoFull16Estados.csv");

		JavaRDD<String> covidRawDataBeforeFilter = sc.textFile(pathfile);
		System.out.println("Count lines, before 1º filter: " + covidRawDataBeforeFilter.count());

		JavaRDD<String> covidRawData = sc.textFile(pathfile)
				.filter(row -> Arrays.asList(row.split(",")).size() == 18)
				.cache();

		System.out.println("covidRawData number of lines: " + covidRawData.count());

		System.out.println("=============== Columns ===============");
		String first = covidRawData.first();
		List<String> head = Arrays.asList(first.split(","));
		for (int i = 0; i < head.size(); i++) {
			System.out.println(i + " - " + head.get(i));
		}

		System.out.println("=============== Macro analysis on Data ===============");
		covidRawData = covidRawData.filter(row -> !row.contains(first));
		System.err.println("====>>> There are " + head.size() + " colums on the dataset.");
		System.err.println("====>>> There are " + covidRawData.count() + " lines on the dataset.");

		System.out.println("=============== Take 10 lines ===============");
		covidRawData.take(10).forEach(System.out::println);

		System.out.println("=============== Lines per State ===============");
		// State, amount of lines
		JavaPairRDD<String, Long> entryPerState = covidRawData
				.mapToPair(row -> new Tuple2<String, Long>(Arrays.asList(row.split(",")).get(15), 1L))
				.reduceByKey((value1, value2) -> value1 + value2);
		entryPerState.take(10).forEach(System.out::println);

		System.out.println("=============== Sorting data by Lines per State ===============");
		// State, amount of lines per state
		entryPerState = entryPerState
				.mapToPair(row -> new Tuple2<Long, String>(row._2, row._1))
				.sortByKey(false)
				.mapToPair(row -> new Tuple2<String, Long>(row._2, row._1));
		entryPerState.take(10).forEach(System.out::println);

		System.out.println("=============== After Filter by State  ===============");
		entryPerState
				.filter(row -> row._1.equals("SP"))
				.collect().forEach(System.out::println);

		sc.close();
	}
}
