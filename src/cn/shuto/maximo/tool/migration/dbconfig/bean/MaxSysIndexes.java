package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;
import java.util.List;

public class MaxSysIndexes implements Serializable {

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

}
