package com.infnet.etapa6;

import java.util.Arrays;

import javax.sound.midi.MidiSystem;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.glassfish.hk2.api.AOPProxyCtl;

import com.ctc.wstx.dtd.DTDEnumAttr;

import static org.apache.spark.sql.functions.*;

import scala.Tuple2;

public class AnaliseCovid19 {
	public static void main(String[] args) {
		
		//Inicia o contador
		long startTime = System.currentTimeMillis();
		long startTimeInitial = System.currentTimeMillis();
		
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
				.load("/home/renoaldo/Documents/DATABASE/Infnet/ClassFiles/ProjetoDeBloco/caso_full.csv");
				//.load("hdfs://localhost:9000/user/renoaldo/database/COVID19/caso_full.csv");
		
		
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
		
		
//		System.out.println("========================== Pesquisando Cidades =====================");
//		startTime = System.currentTimeMillis();
//		df.createOrReplaceTempView("covidReport");
//		
//		System.out.println("======================================================== Quantidade de linhas em city");
//		Dataset<Row> dataset = spark.sql("SELECT city FROM covidReport");
//		dataset.write().format("text").mode("overwrite").save(wareHouse+"/All_City");
//		
////		dataset.show(false);
//		long cityCount = dataset.count();
//		System.out.println("Há "+cityCount+" linhas com valor em city no dataset");
//		timeExecution(startTime);
//		
//		System.out.println("======================================================== VALOR NULL em city");
//		dataset = spark.sql("SELECT city FROM covidReport WHERE city is null");
//		dataset.write().format("text").mode("overwrite").save(wareHouse+"/City_IS_Null");
//		
////		dataset.show(false);
//		long cityCountNull = dataset.count();
//		System.out.println("Há "+ cityCountNull +" linhas com valor NULL no dataset");
//		timeExecution(startTime);
//				
//		System.out.println("======================================================== VALOR Importados/Indefinidos em city");
//		dataset = spark.sql("SELECT city FROM covidReport WHERE city = 'Importados/Indefinidos'");
//		dataset.write().format("text").mode("overwrite").save(wareHouse+"/City_IS_Importados_Indefinidos");
//		
////		dataset.show(false);
//		long cityCountImportIndef = dataset.count();
//		System.out.println("Há "+cityCountImportIndef+" linhas com valor 'Importados/Indefinidos' no dataset");
//		timeExecution(startTime);
////		
//		System.out.println("======================================================== VALOR NÃO Importados/Indefinidos OU NÃO NULL em city == Cidade Pura");
//		dataset = spark.sql("SELECT city FROM covidReport WHERE city is null OR city = 'Importados/Indefinidos'");
//		dataset.write().format("text").mode("overwrite").save(wareHouse+"/City_IS_Null_AND_equalTo_Importados_Indefinidos");
//		
////		dataset.show(false);
//		long cityCountNullORImportIndef = dataset.count();
//		System.out.println("Há "+cityCountNullORImportIndef+" linhas com valor Importados/Indefinidos OU NULL em city no dataset");
//		timeExecution(startTime);
//		
////		dataset.createOrReplaceTempView("covidReportCityNotNull");
//		
//
//		System.out.println("======================================================== VALOR NÃO Importados/Indefinidos AND NÃO NULL em city == Cidade Pura");
//		dataset = spark.sql("SELECT city FROM covidReport WHERE city is not null AND city <> 'Importados/Indefinidos'");
//		dataset.write().format("text").mode("overwrite").save(wareHouse+"/City_ISNOT_Null_AND_different_Importados_Indefinidos");
//		
////		dataset.show(false);
//		long cityCountNoNullANDNOImportIndef = dataset.count();
//		System.out.println("Há "+cityCountNoNullANDNOImportIndef+" linhas com valor NÃO Importados/Indefinidos AND NÃO NULL em city no dataset");
//		timeExecution(startTime);
//		
////		dataset.createOrReplaceTempView("covidReportCityNotNull");
//		
//		System.out.println("=================== Análise de Registros ===================");
//		System.out.println("No total há "+cityCount+" linhas no dataset.");
//		System.out.println("Dessas, há "+cityCountNull+" linhas com valor Null e "+cityCountImportIndef+" linhas com valor Importados/Indefinidos. Totallizando "+(cityCountNull+cityCountImportIndef)+" registros dessas duas categorias.");
//		System.out.println("Os valores Null representam xxxxxxxxxxxx");
//		System.out.println("Os valores Importados/Indefinidos representam ............. ");
//		System.out.println("No filtro Null OR Importados/Indefinidos, registrou-se "+cityCountNullORImportIndef+" linhas");
//		System.out.println("Assim, a diferença entre a soma dos filtros por valores Null e valores Importados/Indefinidos e do filtro para Valores Null OR Importados/Indefinidos é: ");
//		System.out.println("("+cityCountNull+"+"+cityCountImportIndef+")"+"-"+"("+cityCountNullORImportIndef+") = ?");
//		System.out.println("("+(cityCountNull+cityCountImportIndef)+")"+"-"+"("+cityCountNullORImportIndef+") = "+(cityCountNull+cityCountImportIndef-cityCountNullORImportIndef));
//		System.out.println("Assim, há "+(cityCountNull+cityCountImportIndef-cityCountNullORImportIndef)+" registros não considerados.");
//		System.out.println();
//		System.out.println("O filtro de linhas com valores Não Null AND Não Importados/Indefinidos retornou "+cityCountNoNullANDNOImportIndef+" linhas");
//		System.out.println("Do cálculo, a quantidade de valores não Null AND Não Importados/Indefinidos somados a quantidade de valores Null e Importados/Indefinidos totaliza: " +(cityCountNoNullANDNOImportIndef+cityCountNull+cityCountImportIndef)+" linhas");
//		System.out.println("Sendo assim, ha diferença entre o total de linhas no dataset("+cityCount+") e as linhas consideradas("+(cityCountNoNullANDNOImportIndef+cityCountNull+cityCountImportIndef)+"+ é: "+((cityCountNoNullANDNOImportIndef+cityCountNull+cityCountImportIndef)-cityCount));
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//===>>>> agrupar por estado também para ver se bate a quantidade de cidades. Tem  5570 municipios segundo o ibge.
		//===>>>> Importados/Indefinidos é uma cidade. Ver quantos tem. Fazer um filtro e contar quantos tem.
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		Dataset<Row> dataset = spark.sql("select city from covidReport group by select city, city_ibge_code");
		
//		Dataset<Row> dataset = spark.sql("SELECT cast(first(city_ibge_code) as string) as city_ibge_code FROM covidReport GROUP BY city ORDER BY cast(city_ibge_code as int)");
//		dataset.write().format("text").mode("overwrite").save(wareHouse+"/City_Ibge_Code");
		
//		Dataset<Row> dataset = spark.sql("SELECT cast(city_ibge_code as string) as city_ibge_code FROM covidReport WHERE city_ibge_code is null");
//		dataset.write().format("text").mode("overwrite").save(wareHouse+"/City_Ibge_Code_IS_NULL");
//		
//		dataset.show(false);
//		System.out.println("Há "+dataset.count()+" cidades no dataset");
//		timeExecution(startTime);
		
		
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
		System.out.println("Tempo de execução total: "+((System.currentTimeMillis()-startTimeInitial)/1000)+" segundos");
		
	}
	
	private static void timeExecution(long startTime) {
		System.out.println("Tempo de Execução: " + (System.currentTimeMillis() - startTime) / 1000 + " segundos.");
	}
}
