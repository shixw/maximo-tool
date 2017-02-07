package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;
import java.util.List;

import cn.shuto.maximo.tool.migration.BeanInterface;

public class MaxSysIndexes implements Serializable, BeanInterface {

	private static final long serialVersionUID = 1L;

	private String name;
	private String tbname;
	private String uniquerule;
	private String changed = "Y";
	private int clusterrule;
	private String storagepartition;
	private int required;
	private int textsearch;
	private String maxsysindexesid = "MAXSYSINDEXESSEQ.nextval";

	//关联对象
	List<MaxSysKey> maxSysKeys ;
	
	
	
	
	public List<MaxSysKey> getMaxSysKeys() {
		return maxSysKeys;
	}

	public void setMaxSysKeys(List<MaxSysKey> maxSysKeys) {
		this.maxSysKeys = maxSysKeys;
	}

	public MaxSysIndexes(String name, String tbname, String uniquerule, int clusterrule,
			String storagepartition, int required, int textsearch) {
		super();
		this.name = name;
		this.tbname = tbname;
		this.uniquerule = uniquerule;
		this.clusterrule = clusterrule;
		this.storagepartition = storagepartition;
		this.required = required;
		this.textsearch = textsearch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTbname() {
		return tbname;
	}

	public void setTbname(String tbname) {
		this.tbname = tbname;
	}

	public String getUniquerule() {
		return uniquerule;
	}

	public void setUniquerule(String uniquerule) {
		this.uniquerule = uniquerule;
	}

	public String getChanged() {
		return changed;
	}

	public void setChanged(String changed) {
		this.changed = changed;
	}

	public int getClusterrule() {
		return clusterrule;
	}

	public void setClusterrule(int clusterrule) {
		this.clusterrule = clusterrule;
	}

	public String getStoragepartition() {
		return storagepartition;
	}

	public void setStoragepartition(String storagepartition) {
		this.storagepartition = storagepartition;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public int getTextsearch() {
		return textsearch;
	}

	public void setTextsearch(int textsearch) {
		this.textsearch = textsearch;
	}

	public String getMaxsysindexesid() {
		return maxsysindexesid;
	}

	public void setMaxsysindexesid(String maxsysindexesid) {
		this.maxsysindexesid = maxsysindexesid;
	}

	private static final String INSERTMAXSYSINDEXES = "insert into maxsysindexes ( NAME, TBNAME, UNIQUERULE, CHANGED, CLUSTERRULE, STORAGEPARTITION, REQUIRED, TEXTSEARCH, MAXSYSINDEXESID) values ( '%s', '%s', '%s','%s', %s, '%s', %s, %s, %s)";

	public String toInsertSql() {
		return String.format(INSERTMAXSYSINDEXES, this.name, this.tbname, this.uniquerule, this.changed,
				this.clusterrule, this.storagepartition, this.required, this.textsearch, this.maxsysindexesid);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clusterrule;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + required;
		result = prime * result + ((storagepartition == null) ? 0 : storagepartition.hashCode());
		result = prime * result + ((tbname == null) ? 0 : tbname.hashCode());
		result = prime * result + textsearch;
		result = prime * result + ((uniquerule == null) ? 0 : uniquerule.hashCode());
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
		MaxSysIndexes other = (MaxSysIndexes) obj;
		if (clusterrule != other.clusterrule)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (required != other.required)
			return false;
		if (storagepartition == null) {
			if (other.storagepartition != null)
				return false;
		} else if (!storagepartition.equals(other.storagepartition))
			return false;
		if (tbname == null) {
			if (other.tbname != null)
				return false;
		} else if (!tbname.equals(other.tbname))
			return false;
		if (textsearch != other.textsearch)
			return false;
		if (uniquerule == null) {
			if (other.uniquerule != null)
				return false;
		} else if (!uniquerule.equals(other.uniquerule))
			return false;
		return true;
	}

	@Override
	public String toUpdateSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImportUniqueRecordSql() {
		return "select  NAME, TBNAME, UNIQUERULE, CHANGED, CLUSTERRULE, STORAGEPARTITION, REQUIRED, TEXTSEARCH, MAXSYSINDEXESID from maxsysindexes where name = '"+this.name+"'";
	}

}
