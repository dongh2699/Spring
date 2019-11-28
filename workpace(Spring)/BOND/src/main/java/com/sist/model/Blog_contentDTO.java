package com.sist.model;

public class Blog_contentDTO {

	private int bc_No;

	private String m_Email;

	private int b_No;

	private String bc_Cont;

	private String bc_Date;

	private String bc_files[];

	private int bc_filecount;
	
	private int bc_Goods;
	
	  private String bc_Type;

	public int getBc_Goods() {
		return bc_Goods;
	}

	public void setBc_Goods(int bc_Goods) {
		this.bc_Goods = bc_Goods;
	}

	public String getBc_Type() {
		return bc_Type;
	}

	public void setBc_Type(String bc_Type) {
		this.bc_Type = bc_Type;
	}

	public void setBc_files(String[] bc_files) {
		this.bc_files = bc_files;
		setBc_filecount(bc_files.length);
	}

	public void setBc_filecount(int bc_filecount) {
		this.bc_filecount = bc_filecount;
	}

	public int getBc_No() {
		return bc_No;
	}

	public void setBc_No(int bc_No) {
		this.bc_No = bc_No;
	}

	public String getM_Email() {
		return m_Email;
	}

	public void setM_Email(String m_Email) {
		this.m_Email = m_Email;
	}

	public int getB_No() {
		return b_No;
	}

	public void setB_No(int b_No) {
		this.b_No = b_No;
	}

	public String getBc_Cont() {
		return bc_Cont;
	}

	public void setBc_Cont(String bc_Cont) {
		this.bc_Cont = bc_Cont;
	}

	public String getBc_Date() {
		return bc_Date;
	}

	public void setBc_Date(String bc_Date) {
		this.bc_Date = bc_Date;
	}

	public String[] getBc_files() {
		return bc_files;
	}

	public int getBc_filecount() {
		return bc_filecount;
	}
}
