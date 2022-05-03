package com.infnet.etapa6;

import java.util.Arrays;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.*;

import scala.Tuple2;

public class AnaliseCovid19 {
	public static void main(String[] args) {
		
		//Inicia o contador
		long startTime = System.currentTimeMillis();

		// Configura o nível de log para o nível de aviso.
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		//Cria a SparkSession
		SparkSession spark = SparkSession
				.builder()
				.master("local[*]")
				.appName("Meu App Pessoas")
				.getOrCreate();

		//Lê o arquivo
		Dataset<Row> df = spark
				.read()
				.format("com.databricks.spark.csv")
				.option("inferschema", true)
				.option("header", true)
				//.load("hdfs://localhost:9000/user/renoaldo/database/COVID19/casoFull2Estados.csv");
				.load("hdfs://localhost:9000/user/renoaldo/database/COVID19/caso_full.csv");
		/*
		//
		System.out.println("===== EXPLORATION =====");
		System.out.println("Linhas: "+df.count());
		System.out.println("Colunas: "+ df.columns().length);
		System.out.print("Schema do DataSet:");
		df.printSchema();
		System.out.println("================================");
		
		/*
		//Filter by State, Map with state and 
				JavaPairRDD<String, Long> covidNumberRowsByState = covidData
						.filter(row -> Arrays.asList(row.split(",")).get(14).contentEquals("state"))
						.mapToPair(row -> new Tuple2<String, Long>(Arrays.asList(row.split(",")).get(15), 1L))
						.reduceByKey((value1, value2) -> value1 + value2);
				covidNumberRowsByState.collect().forEach(System.out::println);
				
				System.out.println("===== States and Rows Data");
				covidNumberRowsByState.foreach(t -> System.out.println("State: " + t._1 + "\t" + "line amount: " + t._2));
			* /
		df
		.groupBy("state")
		.count()
		.show();
		
		df.filter(col("state")
		  .isNull())
		  .show();
		*/
		/*
		for (String c : df.columns()) {
			df
			.withColumn(col(c)+"_is_Null",col(c).isNull())
			.filter(col(col(c)+"_is_Null").equalTo(true))
			.show(5);
		}
		*/
		Dataset<Row> dfRow;
		for (String c : df.columns()) {
			System.out.println("============ Coluna "+c.toUpperCase()+" ============");
			
			dfRow = df
			.withColumn(col(c)+"_is_Null",col(c).isNull());
			
			dfRow
			.filter(col(col(c)+"_is_Null").equalTo(true))
			.show(5);
			
			dfRow
			.groupBy(col(col(c)+"_is_Null"))
			.count()
			.show();
		}
		
		
		startTime = System.currentTimeMillis();
		df.createOrReplaceTempView("covidReport");
		spark.sql("select city, city_ibge_code from covidReport group by select city, city_ibge_code");
		
		
		System.out.println("Tempo de Execução: "+ (System.currentTimeMillis() - startTime)/1000+" segundos.");
		spark.stop();
		
		
	}
}
