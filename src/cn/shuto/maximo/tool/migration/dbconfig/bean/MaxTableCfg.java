package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;

import cn.shuto.maximo.tool.system.SystemEnvironmental;

public class MaxTableCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tablename;
	private int addrowstamp;
	private String eaudittbname;
	private int isaudittable;
	private int restoredata;
	private String storagepartition;
	private int textsearchenabled;
	private String langtablename;
	private String langcolumnname;
	private String uniquecolumnname;
	private int islangtable;
	private String maxtableid = "MAXTABLECFGSEQ.nextval";
	private String altixname;
	private String trigroot;
	private String contentattribute;


	public MaxTableCfg(String tablename, int addrowstamp, String eaudittbname, int isaudittable, int restoredata,
			String storagepartition, int textsearchenabled, String langtablename, String langcolumnname,
			String uniquecolumnname, int islangtable, String altixname, String trigroot,
			String contentattribute) {
		super();
		this.tablename = tablename;
		this.addrowstamp = addrowstamp;
		this.eaudittbname = eaudittbname;
		this.isaudittable = isaudittable;
		this.restoredata = restoredata;
		this.storagepartition = storagepartition;
		this.textsearchenabled = textsearchenabled;
		this.langtablename = langtablename;
		this.langcolumnname = langcolumnname;
		this.uniquecolumnname = uniquecolumnname;
		this.islangtable = islangtable;
		this.altixname = altixname;
		this.trigroot = trigroot;
		this.contentattribute = contentattribute;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public int getAddrowstamp() {
		return addrowstamp;
	}

	public void setAddrowstamp(int addrowstamp) {
		this.addrowstamp = addrowstamp;
	}

	public String getEaudittbname() {
		return eaudittbname;
	}

	public void setEaudittbname(String eaudittbname) {
		this.eaudittbname = eaudittbname;
	}

	public int getIsaudittable() {
		return isaudittable;
	}

	public void setIsaudittable(int isaudittable) {
		this.isaudittable = isaudittable;
	}

	public int getRestoredata() {
		return restoredata;
	}

	public void setRestoredata(int restoredata) {
		this.restoredata = restoredata;
	}

	public String getStoragepartition() {
		return storagepartition;
	}

	public void setStoragepartition(String storagepartition) {
		this.storagepartition = storagepartition;
	}

	public int getTextsearchenabled() {
		return textsearchenabled;
	}

	public void setTextsearchenabled(int textsearchenabled) {
		this.textsearchenabled = textsearchenabled;
	}

	public String getLangtablename() {
		return langtablename;
	}

	public void setLangtablename(String langtablename) {
		this.langtablename = langtablename;
	}

	public String getLangcolumnname() {
		return langcolumnname;
	}

	public void setLangcolumnname(String langcolumnname) {
		this.langcolumnname = langcolumnname;
	}

	public String getUniquecolumnname() {
		return uniquecolumnname;
	}

	public void setUniquecolumnname(String uniquecolumnname) {
		this.uniquecolumnname = uniquecolumnname;
	}

	public int getIslangtable() {
		return islangtable;
	}

	public void setIslangtable(int islangtable) {
		this.islangtable = islangtable;
	}

	public String getMaxtableid() {
		return maxtableid;
	}

	public void setMaxtableid(String maxtableid) {
		this.maxtableid = maxtableid;
	}

	public String getAltixname() {
		return altixname;
	}

	public void setAltixname(String altixname) {
		this.altixname = altixname;
	}

	public String getTrigroot() {
		return trigroot;
	}

	public void setTrigroot(String trigroot) {
		this.trigroot = trigroot;
	}

	public String getContentattribute() {
		return contentattribute;
	}

	public void setContentattribute(String contentattribute) {
		this.contentattribute = contentattribute;
	}

	private static final String INSERTMAXTABLECFG75 = "insert into maxtablecfg ( TABLENAME, ADDROWSTAMP, EAUDITTBNAME, ISAUDITTABLE, RESTOREDATA, STORAGEPARTITION, TEXTSEARCHENABLED, LANGTABLENAME, LANGCOLUMNNAME, UNIQUECOLUMNNAME, ISLANGTABLE, MAXTABLEID, ALTIXNAME, TRIGROOT, CONTENTATTRIBUTE) values ( '%S', %S, '%S', %S, %S, '%S', %S, '%S', '%S', '%S', %S, %S, '%S', '%S', '%S')";
	
	private static final String INSERTMAXTABLECFG76 = "insert into maxtablecfg ( TABLENAME, ADDROWSTAMP, EAUDITTBNAME, ISAUDITTABLE, RESTOREDATA, STORAGEPARTITION, TEXTSEARCHENABLED, LANGTABLENAME, LANGCOLUMNNAME, UNIQUECOLUMNNAME, ISLANGTABLE, MAXTABLEID, ALTIXNAME, TRIGROOT, CONTENTATTRIBUTE , EXTTABLENAME , STORAGETYPE) values ( '%S', %S, '%S', %S, %S, '%S', %S, '%S', '%S', '%S', %S, %S, '%S', '%S', '%S' ,'' ,0)";

	public String toInsertSql() {
		String targetVersion = SystemEnvironmental.getInstance().getStringParam("-targetVersion");
		if(!targetVersion.isEmpty()&&"7.6".equals(targetVersion)){
			return String.format(INSERTMAXTABLECFG76, this.tablename, this.addrowstamp, this.eaudittbname, this.isaudittable,
					this.restoredata, this.storagepartition, this.textsearchenabled, this.langtablename,
					this.langcolumnname, this.uniquecolumnname, this.islangtable, this.maxtableid, this.altixname,
					this.trigroot, this.contentattribute);
		}
		return String.format(INSERTMAXTABLECFG75, this.tablename, this.addrowstamp, this.eaudittbname, this.isaudittable,
				this.restoredata, this.storagepartition, this.textsearchenabled, this.langtablename,
				this.langcolumnname, this.uniquecolumnname, this.islangtable, this.maxtableid, this.altixname,
				this.trigroot, this.contentattribute);
	}
}
