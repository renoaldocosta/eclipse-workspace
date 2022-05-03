package com.infnet.TP7.covid19;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class TestaCovid {

	public static void main(String[] args) {
		
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		SparkSession spark = SparkSession
				.builder()
				.master("local[*]")
				.appName("BeanCovid")
				.getOrCreate();
		
		Dataset<Row> dataset = spark.read()
		.format("com.databricks.spark.csv")
		.option("header", true)
		.option("inferschema", true)
		.option("delimiter", ",")
		.csv("/home/renoaldo/Documents/DATABASE/Infnet/ClassFiles/ProjetoDeBloco/TP7/PRODUCTION_DataCovid/caso_full.csv");
		
		dataset.printSchema();
	}

}
