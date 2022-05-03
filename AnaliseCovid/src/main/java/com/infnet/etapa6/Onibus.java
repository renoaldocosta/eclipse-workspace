package com.infnet.etapa6;

public class Onibus {

	private int code;
	private String codigoLinha;
	private String nomeLinha;
	private double latitude;
	private double longitude;

	public Onibus(int code, String codigoLinha, String nomeLinha, double latitude, double longitude) {
		super();
		this.code = code;
		this.codigoLinha = codigoLinha;
		this.nomeLinha = nomeLinha;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCodigoLinha() {
		return codigoLinha;
	}

	public void setCodigoLinha(String codigoLinha) {
		this.codigoLinha = codigoLinha;
	}

	public String getNomeLinha() {
		return nomeLinha;
	}

	public void setNomeLinha(String nomeLinha) {
		this.nomeLinha = nomeLinha;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}