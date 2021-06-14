package com.example.mytransaction;

public class Transaction {
	private int num;
	private Double nominal;
	private String tanggal;
	private String jenis;
	private String keterangan;
	public void setNum(int num){
		this.num = num;
	}
	public int getNum(){
		return this.num;
	}
	public void setNominal(Double nominal){
		this.nominal = nominal;
	}
	public Double getNominal(){
		return this.nominal;
	}
	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}
	public String getTanggal(){
		return this.tanggal;
	}
	public void setJenis(String jenis){
		this.jenis = jenis;
	}
	public String getJenis(){
		return this.jenis;
	}
	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}
	public String getKeterangan(){
		return this.keterangan;
	}
}
