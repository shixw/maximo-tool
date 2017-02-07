package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;

import cn.shuto.maximo.tool.migration.BeanInterface;

public class MaxSysKey implements Serializable, BeanInterface  {

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colname == null) ? 0 : colname.hashCode());
		result = prime * result + colseq;
		result = prime * result + ((ixname == null) ? 0 : ixname.hashCode());
		result = prime * result + ((ordering == null) ? 0 : ordering.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		MaxSysKey other = (MaxSysKey) obj;
		if (colname == null) {
			if (other.colname != null)
				return false;
		} else if (!colname.equals(other.colname))
			return false;
		if (colseq != other.colseq)
			return false;
		if (ixname == null) {
			if (other.ixname != null)
				return false;
		} else if (!ixname.equals(other.ixname))
			return false;
		if (ordering == null) {
			if (other.ordering != null)
				return false;
		} else if (!ordering.equals(other.ordering))
			return false;
		return true;
	}

	@Override
	public String toUpdateSql() {
		return null;
	}

	@Override
	public String getImportUniqueRecordSql() {
		return "select IXNAME, COLNAME, COLSEQ, ORDERING, CHANGED, MAXSYSKEYSID from maxsyskeys  where IXNAME = '"+this.ixname+"' and COLNAME = '"+this.colname+"'";
	}

}
