package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;

public class MaxSysKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ixname;
	private String colname;
	private int colseq;
	private String ordering;
	private String changed = "Y";
	private String maxsyskeysid = "MAXSYSKEYSSEQ.nextval";

	public MaxSysKey(String ixname, String colname, int colseq, String ordering) {
		super();
		this.ixname = ixname;
		this.colname = colname;
		this.colseq = colseq;
		this.ordering = ordering;
	}

	public String getIxname() {
		return ixname;
	}

	public void setIxname(String ixname) {
		this.ixname = ixname;
	}

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public int getColseq() {
		return colseq;
	}

	public void setColseq(int colseq) {
		this.colseq = colseq;
	}

	public String getOrdering() {
		return ordering;
	}

	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}

	public String getChanged() {
		return changed;
	}

	public void setChanged(String changed) {
		this.changed = changed;
	}

	public String getMaxsyskeysid() {
		return maxsyskeysid;
	}

	public void setMaxsyskeysid(String maxsyskeysid) {
		this.maxsyskeysid = maxsyskeysid;
	}

	private static final String INSERTMAXSYSKEYES = "insert into maxsyskeys ( IXNAME, COLNAME, COLSEQ, ORDERING, CHANGED, MAXSYSKEYSID) values ( '%s', '%s', %s, '%s','%s', %s)";

	public String toInsertSql() {
		return String.format(INSERTMAXSYSKEYES, this.ixname, this.colname, this.colseq, this.ordering, this.changed,
				this.maxsyskeysid);
	}

}
