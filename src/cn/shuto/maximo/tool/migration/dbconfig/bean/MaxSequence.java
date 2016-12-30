package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;

public class MaxSequence implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tbname;
	private String name;
	private int maxreserved;
	private int maxvalue;
	private int range;
	private String sequencename;
	private String maxsequenceid = "MAXSEQUENCESEQ.nextval";

	public MaxSequence(String tbname, String name, int maxreserved, int maxvalue, int range, String sequencename) {
		super();
		this.tbname = tbname;
		this.name = name;
		this.maxreserved = maxreserved;
		this.maxvalue = maxvalue;
		this.range = range;
		this.sequencename = sequencename;
	}

	public String getTbname() {
		return tbname;
	}

	public void setTbname(String tbname) {
		this.tbname = tbname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxreserved() {
		return maxreserved;
	}

	public void setMaxreserved(int maxreserved) {
		this.maxreserved = maxreserved;
	}

	public int getMaxvalue() {
		return maxvalue;
	}

	public void setMaxvalue(int maxvalue) {
		this.maxvalue = maxvalue;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public String getSequencename() {
		return sequencename;
	}

	public void setSequencename(String sequencename) {
		this.sequencename = sequencename;
	}

	public String getMaxsequenceid() {
		return maxsequenceid;
	}

	public void setMaxsequenceid(String maxsequenceid) {
		this.maxsequenceid = maxsequenceid;
	}

	private static final String INSERTMAXSEQUENCE = "insert into maxsequence ( TBNAME, NAME, MAXRESERVED, MAXVALUE, RANGE, SEQUENCENAME, MAXSEQUENCEID) values ( '%s', '%s', %s, %s, %s, '%s', %s)";

	public String toInsertSql() {
		return String.format(INSERTMAXSEQUENCE, this.tbname, this.name, this.maxreserved, this.maxvalue, this.range,
				this.sequencename, this.maxsequenceid);
	}

}
