package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;

import cn.shuto.maximo.tool.migration.BeanInterface;

public class MaxRelationship implements Serializable, BeanInterface {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardinality == null) ? 0 : cardinality.hashCode());
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + dbjoinrequired;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((whereclause == null) ? 0 : whereclause.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaxRelationship other = (MaxRelationship) obj;
		if (cardinality == null) {
			if (other.cardinality != null)
				return false;
		} else if (!cardinality.equals(other.cardinality))
			return false;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		if (dbjoinrequired != other.dbjoinrequired)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (whereclause == null) {
			if (other.whereclause != null)
				return false;
		} else if (!whereclause.equals(other.whereclause))
			return false;
		return true;
	}

	private static final String UPDATEMAXRELATIONSHIP = "update maxrelationship set CHILD='%s',WHERECLAUSE='%s',REMARKS='%s',CARDINALITY='%s',DBJOINREQUIRED=%s where parent = '%s' and name = '%s'";

	@Override
	public String toUpdateSql() {
		return String.format(UPDATEMAXRELATIONSHIP, this.child, this.whereclause, this.remarks, this.cardinality,
				this.dbjoinrequired, this.parent, this.name);
	}

	@Override
	public String getImportUniqueRecordSql() {
		return "select NAME, PARENT, CHILD, WHERECLAUSE, REMARKS, MAXRELATIONSHIPID, CARDINALITY, DBJOINREQUIRED from maxrelationship where parent = '"
				+ this.parent + "' and name='" + this.name + "'";
	}

}
