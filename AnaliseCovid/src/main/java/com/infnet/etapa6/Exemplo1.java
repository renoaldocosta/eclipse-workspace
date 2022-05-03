package com.infnet.etapa6;

import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class Exemplo1 {

	public static void main(String[] args) {

		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
// Configuração do Spark
		//Aqui nós criamos uma configuração  com execução apenas local e damos um nome.
		SparkConf conf = new SparkConf().setMaster("local").setAppName("BusProcessor");
		//criamos um spark Context e com esse spark context, nós iniciamos um sql context.
		JavaSparkContext ctx = new JavaSparkContext(conf);
		SQLContext sctx = new SQLContext(ctx);

// Carrega os dados dos ônibus e os transforma no objeto Onibus
		JavaRDD<String> linhas = ctx.textFile("hdfs://localhost:9000/user/renoaldo/database/ProjetoBloco/onibus.txt");
		JavaRDD<Onibus> onibus = linhas.map(x -> x.split(" "))
				                       .map(o -> new Onibus(
				                    		   Integer.parseInt(o[0]), 
				                    		   o[1], 
				                    		   o[2],
				                    		   Double.parseDouble(o[4]), 
				                    		   Double.parseDouble(o[5])));

// Cria o DataFrame
		Dataset<Row> onibusDF = sctx.createDataFrame(onibus, Onibus.class);

// Mostra os dados do DataFrame
		onibusDF.show();
		
		onibusDF.select("codigoLinha").show();
		
		onibusDF.groupBy("codigoLinha").count().show();
		
		onibusDF.orderBy("codigoLinha").show();

		onibusDF.filter(onibusDF.col("codigoLinha").equalTo("715M-10")).show();
		
		
	}

}