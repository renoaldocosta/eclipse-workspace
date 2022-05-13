package com.infnet.etapa6;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
public class ProjetoDeBlocoTP {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		//Configura o nível de log para o nível de aviso. 
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		//==================== Questão 1 ====================
		SparkSession spark = SparkSession
				.builder()
				.master("local[*]")
				.appName("App Projeto Bloco")
				.getOrCreate();
		
		
		Dataset<Row> df = spark
				.read()
				.format("com.databricks.spark.csv")
				.option("inferschema", true)
				.option("header", true)
				.load("hdfs://localhost:9000/user/renoaldo/database/COVID19/caso_full.csv");
		
		System.out.println("==================== Questão 2 ====================");
		df.printSchema();
		
		System.out.println("==================== Questão 3 ====================");
		df.createOrReplaceTempView("covidReport");
		Dataset<Row> dataset = spark.sql("select date, place_type, city, state, last_available_deaths from covidReport order by last_available_deaths desc");
		dataset.show(10);
		
		System.out.println("==================== Questão 4 ====================");
		dataset = spark.sql("select state, avg(new_deaths) from covidReport where place_type = 'state' group by state order by avg(new_deaths) desc");
		dataset.show();
		
		System.out.println("==================== Questão 5 ====================");
		dataset = spark.sql("select distinct(state) from covidReport");
		dataset.show(100);
		System.out.println("Número de Estados: "+dataset.count());
		
		System.out.println("Tempo de execução: "+((System.currentTimeMillis() - startTime)/1000)+ " segundos");
		spark.stop();
	}
}







