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
import org.apache.spark.sql.SparkSession;

public class Exemplo3 {

	public static void main(String[] args) {

		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
// Configuração do Spark
		
		
		//Aqui nós criamos uma configuração  com execução apenas local e damos um nome.
		SparkConf conf = new SparkConf().setMaster("local").setAppName("BusProcessor");
		//criamos um spark Context e com esse spark context, nós iniciamos um sql context.
		JavaSparkContext ctx = new JavaSparkContext(conf);
		SQLContext sctx = new SQLContext(ctx);
		
		SparkSession spark = SparkSession
				.builder()
				.master("local[*]")
				.appName("BusProcessor")
				.getOrCreate();
// Carrega os dados dos ônibus e os transforma no objeto Onibus
	/*	JavaRDD<String> linhas = ctx.textFile("hdfs://localhost:9000/user/renoaldo/database/ProjetoBloco/onibus.txt");
		JavaRDD<Onibus> onibus = linhas.map(x -> x.split(" "))
				                       .map(o -> new Onibus(
				                    		   Integer.parseInt(o[0]), 
				                    		   o[1], 
				                    		   o[2],
				                    		   Double.parseDouble(o[4]), 
				                    		   Double.parseDouble(o[5])));

// Cria o DataFrame
		Dataset<Row> onibusDF = sctx.createDataFrame(onibus, Onibus.class);

		onibusDF.createOrReplaceTempView("onibus");

		// Realiza as operações nos dados utilizando SQL
		sctx.sql("SELECT code, codigoLinha, nomeLinha FROM onibus").show();

		sctx.sql("SELECT * FROM onibus WHERE codigoLinha like '715M'").show();
		
		sctx.sql("SELECT * FROM onibus WHERE codigoLinha like '715M' ORDER BY code").show();

		sctx.sql("SELECT code, count(code) FROM onibus GROUP BY code").show();
	}
*/
	}
}