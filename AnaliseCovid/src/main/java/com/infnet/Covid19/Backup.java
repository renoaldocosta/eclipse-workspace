package com.infnet.Covid19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Backup {

	private static JavaSparkContext sc;

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("AnaliseCovid").setMaster("local");
		sc = new JavaSparkContext(conf);
		
		//Logger rootLogger = Logger.getRootLogger();
		//rootLogger.setLevel(Level.ERROR);

		//String pathfile = new String("hdfs://localhost:9000/user/infnet/COVID19/caso_full.csv");
		String pathfile = new String("/home/infnet/Documentos/caso_full_SAMPLE/caso_full_sample.csv");
		JavaRDD<String> covidRawData = sc.textFile(pathfile).cache();
		
		
		//System.out.println("=====  =====");
		System.out.println("===== EXPLORATION =====");
		System.out.println("Size of the dataset: "+covidRawData.count());
		System.out.println("===== The Dataset =====");
//		covidRawData.collect().forEach(System.out::println);
		/*
		System.out.println("===== REMOVE HEAD =====");
		List<String> head = (covidRawData.take(1));
		JavaRDD<String> covidData = covidRawData.filter(row -> !row.equals(head.get(0)));
		System.out.println("Size of the dataset: "+covidData.count());
//		covidData.collect().forEach(System.out::println);
		*/
		System.out.println("Amostra>>>>>>>>>>>>>>>>>>");
		//JavaRDD<String> newDataset = sc.parallelize(covidRawData.take(10000));
		JavaRDD<String> newDataset = sc.parallelize(covidRawData.takeSample(false, 10000));
		newDataset.saveAsTextFile("/home/infnet/Documentos/caso_full_SAMPLE2");
		
		/*
		
		System.out.println("===== Showing the Columns =====");
		List<String> headList = Arrays.asList(head.get(0).split(","));
		
		for (int i = 0; i< headList.size(); i++) {
			System.out.println("Índice "+i+" - "+headList.get(i));
		}
		covidData.cache();
		
		/*System.out.println("===== Remove the null =====");
		JavaRDD<String> covidDataWithoutNull = covidData.map(row -> {
			if (row.startsWith(","))
				return "X"+row;
			else
				return row;
		});
		covidDataWithoutNull.collect().forEach(System.out::println);
		covidDataWithoutNull.collect().forEach(row -> System.out.println(Arrays.asList(row.split(",")).get(3)));
		
		*
		*
		*
		*\/
		
		System.out.println("===== Showing the Epidemiological Weeks =====");
		JavaRDD<Integer> epidemicalWeek = covidData.map(row -> Integer.parseInt(Arrays.asList(row.split(",")).get(3)));
//		epidemicalWeek.collect().forEach(System.out::println);
		
		//202012
		System.out.println("===== Filtering the week =====");
		int focusWeek = 202012;
		JavaRDD<String> covidDataFiltered = covidData.filter(row -> Integer.parseInt(Arrays.asList(row.split(",")).get(3)) == focusWeek);
//		covidDataFiltered.collect().forEach(System.out::println);
		System.out.println("Size of the dataset Before: "+covidData.count());
		System.out.println("Size of the dataset After: "+covidDataFiltered.count());
		
		
		
		
		/*
		String head = covidRawData.first();
		JavaRDD<String> covidData = covidRawData.filter(row -> row == head);
		System.out.println(covidData);
		
		System.out.println("======= EXPLORATION =======");
		
		System.out.println("Quantity of lines on Raw RDD: "+ covidRawData.count());
		//System.out.println("Quantity of lines on clear RDD: "+ covidData.count());
		//System.out.println(covidRawData.take(2));
		System.out.println("======= Filter =======");
		
		
		
		
		//System.out.println("Quantidade de Linhas: "+ covidRawData.count());
		//syso
		
		//JavaRDD<Integer> week = covidRawData.map(linha -> Integer.parseInt(Arrays.asList(linha.split(",")).get(3)));
		
		//System.out.println("Quantidade de semanas: "+week.take(10));
		 *
		 */
		sc.close();
	}
}
