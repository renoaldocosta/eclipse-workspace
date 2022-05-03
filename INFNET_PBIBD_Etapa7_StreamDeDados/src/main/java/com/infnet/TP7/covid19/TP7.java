package com.infnet.TP7.covid19;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.ProcessingTime;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;

public class TP7 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		//Questão 02 - Declara Spark Session
		SparkSession spark = SparkSession
				.builder()
				.master("local[*]")
				.appName("AppdeStream")
				.getOrCreate();
		
		Logger.getLogger("org.apache").setLevel(Level.ERROR);
		
		runStreamingFromDir(spark);
		
		System.out.println("Execution Time: "+(System.currentTimeMillis() - startTime)/1000 + " seconds");
		spark.stop();
		
	}
	
	private static void runStreamingFromDir(SparkSession spark) {
		
		
	
//		StructType titanicSchema = new StructType()
//				.add("PassengerId", "int")
//				.add("Survived", "int")
//				.add("Pclass", "long")
//				//.add("Name","string")
//				.add("Sex","string")
//				.add("Age","long")
//				.add("SibSp","long")
//				.add("Parch","long")
//				.add("Ticket","long")
//				.add("Fare","long")
//				.add("Cabin","string")
//				.add("Embarked","string");
		
		//Questão 03 - Define esquema de dados com StructType
		StructType covidSchema = new StructType()
			.add("city","String")
			.add("city_ibge_code","int")
			.add("date","String")
			.add("epidemiological_week","int")
			.add("estimated_population","int")
			.add("estimated_population_2019","int")
			.add("is_last","boolean")
			.add("is_repeated","boolean")
			.add("last_available_confirmed","int")
			.add("last_available_confirmed_per_100k_inhabitants","double")
			.add("last_available_date","String")
			.add("last_available_death_rate","double")
			.add("last_available_deaths","int")
			.add("order_for_place","int")
			.add("place_type","String")
			.add("state","String")
			.add("new_confirmed","int")
			.add("new_deaths","int");
				
		
		Dataset<CovidSchemaBean> inLines = spark
				.readStream()
				//.format("csv")
				//.option("header", true)
				.option("sep", ",")
				.schema(covidSchema)
				.csv("/home/renoaldo/Documents/DATABASE/Infnet/ClassFiles/ProjetoDeBloco/TP7/PRODUCTION_DataCovid")
				.as(Encoders.bean(CovidSchemaBean.class));
		
		inLines.createOrReplaceTempView("covidReport");
		
		inLines.printSchema();
		
		//String sqlQuerrySouceString = "SELECT Sex, SUM(Survived)/COUNT(*) AS SurvivalRate FROM covidReport GROUP BY 1";
		//String sqlQuerrySouceString = "SELECT COUNT(*) AS SurvivalRate FROM covidReport";
		String sqlQuerrySouceString = "SELECT state,city, SUM(last_available_deaths) FROM covidReport where is_last = 1 GROUP BY city,state" ;
		Dataset<Row> sqlDFSource = spark.sql(sqlQuerrySouceString);
		
		StreamingQuery querySourcCounts = sqlDFSource.writeStream()
		.outputMode("complete")
		.format("console")
		//.trigger(ProcessingTime.create(10, TimeUnit.SECONDS))
		.trigger(ProcessingTime.create("3 seconds"))
		.start();
		
		try {
			querySourcCounts.awaitTermination();
		} catch (StreamingQueryException e) {
			e.printStackTrace();
		}
		
		//Podemos fazer querries SQL em nossa tabela, agoa que temos nossa 
		
		
		
	}

}
