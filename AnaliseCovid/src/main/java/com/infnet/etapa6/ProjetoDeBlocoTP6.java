package com.infnet.etapa6;

import java.util.Arrays;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class ProjetoDeBlocoTP6 {

	private static JavaSparkContext ctx;

	public static void main(String[] args) {

		Logger.getLogger("org.apache").setLevel(Level.WARN);

		//Configura o Spark
		SparkConf conf = new SparkConf().setMaster("local").setAppName("Covid19Analyser");
		JavaSparkContext ctx = new JavaSparkContext(conf);
		SQLContext sctx = new SQLContext(ctx);

		//Limpa os dados pelo tamanho
		String pathfile = new String("hdfs://localhost:9000/user/renoaldo/database/COVID19/casoFull2Estados.csv");
		JavaRDD<String> covidRawData = ctx.textFile(pathfile)
				.filter(row -> Arrays.asList(row.split(",")).size() == 18)
				.cache();
		
		//Retira Cabeçalho
		String head = covidRawData.first();
		covidRawData = covidRawData.filter(row -> !row.contentEquals(head));
		
		//Transforma o RDD em um objeto Registro
		JavaRDD<RegistroCovid> registros = covidRawData
				    .map(row -> row.split(","))
					.map(x -> new RegistroCovid(
							x[0], 
							x[1], 
							x[2], 
							Integer.parseInt(x[3]), 
							x[4], 
							x[5], 
							x[6], 
							x[7], 
							Integer.parseInt(x[8]), 
							x[9], 
							x[10], 
							Double.parseDouble(x[11]), 
							Integer.parseInt(x[12]), 
							Integer.parseInt(x[13]), 
							x[14], 
							x[15], 
							Integer.parseInt(x[16]), 
							Integer.parseInt(x[17])));
		
		//Cria o DataFrama
		Dataset<Row> registrosDF = sctx.createDataFrame(registros, RegistroCovid.class);
		
		registrosDF.show();
		
		
		
		
		

		ctx.close();
	}
}
