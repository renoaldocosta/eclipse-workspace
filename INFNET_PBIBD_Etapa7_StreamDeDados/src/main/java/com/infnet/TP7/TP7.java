package com.infnet.TP7;

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
		
		
		//Questão 03 - Define esquema de dados com StructType
		StructType titanicSchema = new StructType()
				.add("PassengerId", "int")
				.add("Survived", "int")
				.add("Pclass", "long")
				//.add("Name","string")
				.add("Sex","string")
				.add("Age","long")
				.add("SibSp","long")
				.add("Parch","long")
				.add("Ticket","long")
				.add("Fare","long")
				.add("Cabin","string")
				.add("Embarked","string");
		
		Dataset<TitanicSchemaBean> inLines = spark
				.readStream()
				//.format("csv")
				//.option("header", true)
				.option("sep", ",")
				.schema(titanicSchema)
				.csv("/home/renoaldo/Documents/DATABASE/Infnet/ClassFiles/ProjetoDeBloco/titanic/Titanit/")
				.as(Encoders.bean(TitanicSchemaBean.class));
		
		inLines.createOrReplaceTempView("titanic");
		
		inLines.printSchema();
		
		String sqlQuerrySouceString = "SELECT Sex, SUM(Survived)/COUNT(*) AS SurvivalRate FROM titanic GROUP BY 1";
		//String sqlQuerrySouceString = "SELECT COUNT(*) AS SurvivalRate FROM titanic";
		
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
