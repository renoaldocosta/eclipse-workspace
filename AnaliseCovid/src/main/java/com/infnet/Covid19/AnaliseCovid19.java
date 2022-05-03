package com.infnet.Covid19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class AnaliseCovid19 {

	private static JavaSparkContext sc;

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("AnaliseCovid").setMaster("local");
		sc = new JavaSparkContext(conf);

		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);

		//String pathfile = new String("hdfs://localhost:9000/user/renoaldo/database/COVID19/caso_full.csv");
		String pathfile = new String("hdfs://localhost:9000/user/renoaldo/database/COVID19/casoFull4Estados.csv");
		//String pathfile = new String("hdfs://localhost:9000/user/renoaldo/database/COVID19/casoFull2Estados.csv");
		JavaRDD<String> covidRawData = sc.textFile(pathfile).cache();

		System.out.println("===== EXPLORATION =====");
		System.out.println("Dataset Size: " + covidRawData.count());
		
//		System.out.println("===== The Dataset =====");
//		covidRawData.collect().forEach(System.out::println);

		System.out.println("===== REMOVE HEAD =====");
		List<String> head = (covidRawData.take(1));
		JavaRDD<String> covidDataNoHead = covidRawData.filter(row -> true);//!row.equals(head.get(0))
		System.out.println("Dataset Size: " + covidDataNoHead.count());
// 		covidData.collect().forEach(System.out::println);
		
		
		System.out.println("===== The Columns =====");
		List<String> headList = Arrays.asList(head.get(0).split(","));
		int columnsSize = headList.size();
		
		for (int i = 0; i < headList.size(); i++) {
			System.out.println("Índice " + i + " - " + headList.get(i));
		}
		System.out.println("===================================\nColumns Total: "+ columnsSize);
		
		covidDataNoHead.cache();
		covidRawData.unpersist();
		// =======================================================================================

		// Filter by the size
		System.out.println("===== Filter by the size =====");
		JavaRDD<String> covidData = covidDataNoHead.filter(row -> Arrays.asList(row.split(",")).size() == columnsSize);
		System.out.println("before filter: " + covidDataNoHead.count());
		System.out.println("Only 18 size colums after filter: " + covidData.count());
		
		System.out.println("==================================>>> Printing 10");
		covidData.take(10).forEach(System.out::println);
		
		System.out.println("==================================>>> Filtering raw data");
		covidData.filter(row -> Arrays.asList(row.split(",")).get(7).equals("True"))
				 .take(10)
				 .forEach(System.out::println);

		
		System.out.println("===== Filter by State, MAP and Reduce =====");
		String placeType = "state";
		
		//Filter by State, Map with state and 
		JavaPairRDD<String, Long> covidNumberRowsByState = covidData
				.filter(row -> Arrays.asList(row.split(",")).get(14).contentEquals("state"))
				.mapToPair(row -> new Tuple2<String, Long>(Arrays.asList(row.split(",")).get(15), 1L))
				.reduceByKey((value1, value2) -> value1 + value2);
		covidNumberRowsByState.collect().forEach(System.out::println);
		
		System.out.println("===== States and Rows Data");
		covidNumberRowsByState.foreach(t -> System.out.println("State: " + t._1 + "\t" + "line amount: " + t._2));
		
		sc.close();
	}
}

