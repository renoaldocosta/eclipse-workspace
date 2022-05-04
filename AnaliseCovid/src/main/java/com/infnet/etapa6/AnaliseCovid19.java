package com.infnet.etapa6;

import java.util.Arrays;

import javax.sound.midi.MidiSystem;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.ctc.wstx.dtd.DTDEnumAttr;

import static org.apache.spark.sql.functions.*;

import scala.Tuple2;

public class AnaliseCovid19 {
	public static void main(String[] args) {
		
		//Inicia o contador
		long startTime = System.currentTimeMillis();
		
		//Seta WareHouse
		String wareHouse = "/home/renoaldo/Documents/DATABASE/Infnet/ClassFiles/DesenvolvimentoJava/WareHouseCovid19";

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
		
		
		//Pesquisando valores genéricos em todas as colunas e trazendo quantidade.
//		startTime = System.currentTimeMillis();
//		System.out.println("========================== Análise de Valores Null =====================");
//		System.out.println(""); 		
//		Dataset<Row> dfRow;
////		for (String c : df.columns()) {
////			System.out.println("============ Coluna "+c.toUpperCase()+" ============");
////			
////			dfRow = df
////			.withColumn(col(c)+"_is_Null",col(c).isNull());
////			
////			dfRow
////			.filter(col(col(c)+"_is_Null").equalTo(true))
////			.show(5);
////			
////			dfRow
////			.groupBy(col(col(c)+"_is_Null"))
////			.count()
////			.show();
////		}
//		timeExecution(startTime);
		
		
		System.out.println("========================== Pesquisando Cidades =====================");
		startTime = System.currentTimeMillis();
		df.createOrReplaceTempView("covidReport");
//		Dataset<Row> dataset = spark.sql("select city from covidReport group by select city, city_ibge_code");
		Dataset<Row> dataset = spark.sql("SELECT city FROM covidReport GROUP BY city ORDER BY city");
		dataset.write().format("text").save(wareHouse);
		dataset.show(false);
		System.out.println("Há "+dataset.count()+" cidades no dataset");
		timeExecution(startTime);
		
		===>>>> agrupar por estado também para ver se bate a quantidade de cidades. Tem  5570 municipios segundo o ibge.
		===>>>> Importados/Indefinidos é uma cidade. Ver quantos tem. Fazer um filtro e contar quantos tem.
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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

		
		
		
		
		
		
		
		spark.stop();
		
		
	}
	
	private static void timeExecution(long startTime) {
		System.out.println("Tempo de Execução: " + (System.currentTimeMillis() - startTime) / 1000 + " segundos.");
	}
}
