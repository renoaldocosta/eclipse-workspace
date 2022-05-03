package com.infnet.etapa6;

public class RegistroCovid {
	private String city;
	private String city_ibge_code;
	private String date;
	private Integer epidemiological_week;
	private String estimated_population;
	private String estimated_population_2019;
	private String is_last;
	private String is_repeated;
	private Integer last_available_confirmed;
	private String last_available_confirmed_per_100k_inhabitants;
	private String last_available_date;
	private Double last_available_death_rate;
	private Integer last_available_deaths;
	private Integer order_for_place;
	private String place_type;
	private String state;
	private Integer new_confirmed;
	private Integer new_deaths;
	
	
	public RegistroCovid(String city, String city_ibge_code, String date, Integer epidemiological_week,
			String estimated_population, String estimated_population_2019, String is_last, String is_repeated,
			Integer last_available_confirmed, String last_available_confirmed_per_100k_inhabitants,
			String last_available_date, Double last_available_death_rate, Integer last_available_deaths,
			Integer order_for_place, String place_type, String state, Integer new_confirmed, Integer new_deaths) {
		super();
		this.city = city;
		this.city_ibge_code = city_ibge_code;
		this.date = date;
		this.epidemiological_week = epidemiological_week;
		this.estimated_population = estimated_population;
		this.estimated_population_2019 = estimated_population_2019;
		this.is_last = is_last;
		this.is_repeated = is_repeated;
		this.last_available_confirmed = last_available_confirmed;
		this.last_available_confirmed_per_100k_inhabitants = last_available_confirmed_per_100k_inhabitants;
		this.last_available_date = last_available_date;
		this.last_available_death_rate = last_available_death_rate;
		this.last_available_deaths = last_available_deaths;
		this.order_for_place = order_for_place;
		this.place_type = place_type;
		this.state = state;
		this.new_confirmed = new_confirmed;
		this.new_deaths = new_deaths;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity_ibge_code() {
		return city_ibge_code;
	}
	public void setCity_ibge_code(String city_ibge_code) {
		this.city_ibge_code = city_ibge_code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getEpidemiological_week() {
		return epidemiological_week;
	}
	public void setEpidemiological_week(Integer epidemiological_week) {
		this.epidemiological_week = epidemiological_week;
	}
	public String getEstimated_population() {
		return estimated_population;
	}
	public void setEstimated_population(String estimated_population) {
		this.estimated_population = estimated_population;
	}
	public String getEstimated_population_2019() {
		return estimated_population_2019;
	}
	public void setEstimated_population_2019(String estimated_population_2019) {
		this.estimated_population_2019 = estimated_population_2019;
	}
	public String getIs_last() {
		return is_last;
	}
	public void setIs_last(String is_last) {
		this.is_last = is_last;
	}
	public String getIs_repeated() {
		return is_repeated;
	}
	public void setIs_repeated(String is_repeated) {
		this.is_repeated = is_repeated;
	}
	public Integer getLast_available_confirmed() {
		return last_available_confirmed;
	}
	public void setLast_available_confirmed(Integer last_available_confirmed) {
		this.last_available_confirmed = last_available_confirmed;
	}
	public String getLast_available_confirmed_per_100k_inhabitants() {
		return last_available_confirmed_per_100k_inhabitants;
	}
	public void setLast_available_confirmed_per_100k_inhabitants(String last_available_confirmed_per_100k_inhabitants) {
		this.last_available_confirmed_per_100k_inhabitants = last_available_confirmed_per_100k_inhabitants;
	}
	public String getLast_available_date() {
		return last_available_date;
	}
	public void setLast_available_date(String last_available_date) {
		this.last_available_date = last_available_date;
	}
	public Double getLast_available_death_rate() {
		return last_available_death_rate;
	}
	public void setLast_available_death_rate(Double last_available_death_rate) {
		this.last_available_death_rate = last_available_death_rate;
	}
	public Integer getLast_available_deaths() {
		return last_available_deaths;
	}
	public void setLast_available_deaths(Integer last_available_deaths) {
		this.last_available_deaths = last_available_deaths;
	}
	public Integer getOrder_for_place() {
		return order_for_place;
	}
	public void setOrder_for_place(Integer order_for_place) {
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
	public Integer getNew_confirmed() {
		return new_confirmed;
	}
	public void setNew_confirmed(Integer new_confirmed) {
		this.new_confirmed = new_confirmed;
	}
	public Integer getNew_deaths() {
		return new_deaths;
	}
	public void setNew_deaths(Integer new_deaths) {
		this.new_deaths = new_deaths;
	}
	
	
}
