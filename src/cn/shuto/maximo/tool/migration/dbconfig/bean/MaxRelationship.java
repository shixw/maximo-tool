package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;

public class MaxRelationship implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String parent;
	private String child;
	private String whereclause;
	private String remarks;
	private String maxrelationshipid = "MAXRELATIONSHIPSEQ.nextval";
	private String cardinality;
	private int dbjoinrequired;

	public MaxRelationship(String name, String parent, String child, String whereclause, String remarks,
			String cardinality, int dbjoinrequired) {
		super();
		this.name = name;
		this.parent = parent;
		this.child = child;
		setWhereclause(whereclause);
		this.remarks = remarks;
		this.cardinality = cardinality;
		this.dbjoinrequired = dbjoinrequired;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public String getWhereclause() {
		return whereclause;
	}

	public void setWhereclause(String whereclause) {
		this.whereclause = whereclause.replace("'", "''");
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMaxrelationshipid() {
		return maxrelationshipid;
	}

	public void setMaxrelationshipid(String maxrelationshipid) {
		this.maxrelationshipid = maxrelationshipid;
	}

	public String getCardinality() {
		return cardinality;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

	public int getDbjoinrequired() {
		return dbjoinrequired;
	}

	public void setDbjoinrequired(int dbjoinrequired) {
		this.dbjoinrequired = dbjoinrequired;
	}

	private static final String INSERTMAXRELATIONSHIP = "insert into maxrelationship ( NAME, PARENT, CHILD, WHERECLAUSE, REMARKS, MAXRELATIONSHIPID, CARDINALITY, DBJOINREQUIRED) values ( '%s', '%s', '%s', '%s', '%s', %s, '%s', %s)";

	public String toInsertSql() {
		return String.format(INSERTMAXRELATIONSHIP, this.name, this.parent, this.child, this.whereclause, this.remarks,
				this.maxrelationshipid, this.cardinality, this.dbjoinrequired);
	}

}
