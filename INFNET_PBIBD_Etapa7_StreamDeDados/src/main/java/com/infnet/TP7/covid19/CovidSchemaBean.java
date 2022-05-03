package com.infnet.TP7.covid19;

import java.io.Serializable;

import scala.annotation.meta.setter;

//Questão 04 - Implementando um JavaBean para serializar objetos com o esquema de dados definido
public class CovidSchemaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String city;
	private int city_ibge_code;
	private String date;
	private int epidemiological_week;
	private int estimated_population;
	private int estimated_population_2019;
	private boolean is_last;
	private boolean is_repeated;
	private int last_available_confirmed;
	private double last_available_confirmed_per_100k_inhabitants;
	private String last_available_date;
	private double last_available_death_rate;
	private int last_available_deaths;
	private int order_for_place;
	private String place_type;
	private String state;
	private int new_confirmed;
	private int new_deaths;
	
	

	

	public CovidSchemaBean() {}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCity_ibge_code() {
		return city_ibge_code;
	}

	public void setCity_ibge_code(int city_ibge_code) {
		this.city_ibge_code = city_ibge_code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getEpidemiological_week() {
		return epidemiological_week;
	}

	public void setEpidemiological_week(int epidemiological_week) {
		this.epidemiological_week = epidemiological_week;
	}

	public int getEstimated_population() {
		return estimated_population;
	}

	public void setEstimated_population(int estimated_population) {
		this.estimated_population = estimated_population;
	}

	public int getEstimated_population_2019() {
		return estimated_population_2019;
	}

	public void setEstimated_population_2019(int estimated_population_2019) {
		this.estimated_population_2019 = estimated_population_2019;
	}

	public boolean isIs_last() {
		return is_last;
	}

	public void setIs_last(boolean is_last) {
		this.is_last = is_last;
	}

	public boolean isIs_repeated() {
		return is_repeated;
	}

	public void setIs_repeated(boolean is_repeated) {
		this.is_repeated = is_repeated;
	}

	public int getLast_available_confirmed() {
		return last_available_confirmed;
	}

	public void setLast_available_confirmed(int last_available_confirmed) {
		this.last_available_confirmed = last_available_confirmed;
	}

	public double getLast_available_confirmed_per_100k_inhabitants() {
		return last_available_confirmed_per_100k_inhabitants;
	}

	public void setLast_available_confirmed_per_100k_inhabitants(double last_available_confirmed_per_100k_inhabitants) {
		this.last_available_confirmed_per_100k_inhabitants = last_available_confirmed_per_100k_inhabitants;
	}

	public String getLast_available_date() {
		return last_available_date;
	}

	public void setLast_available_date(String last_available_date) {
		this.last_available_date = last_available_date;
	}

	public double getLast_available_death_rate() {
		return last_available_death_rate;
	}

	public void setLast_available_death_rate(double last_available_death_rate) {
		this.last_available_death_rate = last_available_death_rate;
	}

	public int getLast_available_deaths() {
		return last_available_deaths;
	}

	public void setLast_available_deaths(int last_available_deaths) {
		this.last_available_deaths = last_available_deaths;
	}

	public int getOrder_for_place() {
		return order_for_place;
	}

	public void setOrder_for_place(int order_for_place) {
		this.order_for_place = order_for_place;
	}

	public String getPlace_type() {
		return place_type;
	}

	public void setPlace_type(String place_type) {
		this.place_type = place_type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getNew_confirmed() {
		return new_confirmed;
	}

	public void setNew_confirmed(int new_confirmed) {
		this.new_confirmed = new_confirmed;
	}

	public int getNew_deaths() {
		return new_deaths;
	}

	public void setNew_deaths(int new_deaths) {
		this.new_deaths = new_deaths;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
	
	
	
	
	
}
