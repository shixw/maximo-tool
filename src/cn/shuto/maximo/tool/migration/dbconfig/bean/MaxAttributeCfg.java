package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;

public class MaxAttributeCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	private String objectname;
	private String attributename;
	private String alias;
	private String autokeyname;
	private int attributeno;
	private int canautonum;
	private String classname;
	private String columnname;
	private String defaultvalue;
	private String domainid;
	private int eauditenabled;
	private String entityname;
	private int esigenabled;
	private int isldowner;
	private int ispositive;
	private int length;
	private String maxtype;
	private int mustbe;
	private int required;
	private int persistent;
	private int primarykeycolseq;
	private String remarks;
	private String sameasattribute;
	private String sameasobject;
	private int scale;
	private String title;
	private int userdefined;
	private String changed = "I";
	private String searchtype;
	private int mlsupported;
	private int mlinuse;
	private String handlecolumnname;
	private String maxattributeid = " MAXATTRIBUTECFGSEQ.nextval";
	private int restricted;
	private int localizable;
	private String textdirection;
	private String complexexpression;

	public MaxAttributeCfg(String objectname, String attributename, String alias, String autokeyname, int attributeno,
			int canautonum, String classname, String columnname, String defaultvalue, String domainid,
			int eauditenabled, String entityname, int esigenabled, int isldowner, int ispositive, int length,
			String maxtype, int mustbe, int required, int persistent, int primarykeycolseq, String remarks,
			String sameasattribute, String sameasobject, int scale, String title, int userdefined, 
			String searchtype, int mlsupported, int mlinuse, String handlecolumnname, 
			int restricted, int localizable, String textdirection, String complexexpression) {
		super();
		this.objectname = objectname;
		this.attributename = attributename;
		this.alias = alias;
		this.autokeyname = autokeyname;
		this.attributeno = attributeno;
		this.canautonum = canautonum;
		this.classname = classname;
		this.columnname = columnname;
		this.defaultvalue = defaultvalue;
		this.domainid = domainid;
		this.eauditenabled = eauditenabled;
		this.entityname = entityname;
		this.esigenabled = esigenabled;
		this.isldowner = isldowner;
		this.ispositive = ispositive;
		this.length = length;
		this.maxtype = maxtype;
		this.mustbe = mustbe;
		this.required = required;
		this.persistent = persistent;
		this.primarykeycolseq = primarykeycolseq;
		this.remarks = remarks;
		this.sameasattribute = sameasattribute;
		this.sameasobject = sameasobject;
		this.scale = scale;
		this.title = title;
		this.userdefined = userdefined;
		this.searchtype = searchtype;
		this.mlsupported = mlsupported;
		this.mlinuse = mlinuse;
		this.handlecolumnname = handlecolumnname;
		this.restricted = restricted;
		this.localizable = localizable;
		this.textdirection = textdirection;
		this.complexexpression = complexexpression;
	}

	public String getObjectname() {
		return objectname;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	public String getAttributename() {
		return attributename;
	}

	public void setAttributename(String attributename) {
		this.attributename = attributename;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAutokeyname() {
		return autokeyname;
	}

	public void setAutokeyname(String autokeyname) {
		this.autokeyname = autokeyname;
	}

	public int getAttributeno() {
		return attributeno;
	}

	public void setAttributeno(int attributeno) {
		this.attributeno = attributeno;
	}

	public int getCanautonum() {
		return canautonum;
	}

	public void setCanautonum(int canautonum) {
		this.canautonum = canautonum;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getColumnname() {
		return columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getDomainid() {
		return domainid;
	}

	public void setDomainid(String domainid) {
		this.domainid = domainid;
	}

	public int getEauditenabled() {
		return eauditenabled;
	}

	public void setEauditenabled(int eauditenabled) {
		this.eauditenabled = eauditenabled;
	}

	public String getEntityname() {
		return entityname;
	}

	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public int getEsigenabled() {
		return esigenabled;
	}

	public void setEsigenabled(int esigenabled) {
		this.esigenabled = esigenabled;
	}

	public int getIsldowner() {
		return isldowner;
	}

	public void setIsldowner(int isldowner) {
		this.isldowner = isldowner;
	}

	public int getIspositive() {
		return ispositive;
	}

	public void setIspositive(int ispositive) {
		this.ispositive = ispositive;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getMaxtype() {
		return maxtype;
	}

	public void setMaxtype(String maxtype) {
		this.maxtype = maxtype;
	}

	public int getMustbe() {
		return mustbe;
	}

	public void setMustbe(int mustbe) {
		this.mustbe = mustbe;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public int getPersistent() {
		return persistent;
	}

	public void setPersistent(int persistent) {
		this.persistent = persistent;
	}

	public int getPrimarykeycolseq() {
		return primarykeycolseq;
	}

	public void setPrimarykeycolseq(int primarykeycolseq) {
		this.primarykeycolseq = primarykeycolseq;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSameasattribute() {
		return sameasattribute;
	}

	public void setSameasattribute(String sameasattribute) {
		this.sameasattribute = sameasattribute;
	}

	public String getSameasobject() {
		return sameasobject;
	}

	public void setSameasobject(String sameasobject) {
		this.sameasobject = sameasobject;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUserdefined() {
		return userdefined;
	}

	public void setUserdefined(int userdefined) {
		this.userdefined = userdefined;
	}

	public String getChanged() {
		return changed;
	}

	public void setChanged(String changed) {
		this.changed = changed;
	}

	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public int getMlsupported() {
		return mlsupported;
	}

	public void setMlsupported(int mlsupported) {
		this.mlsupported = mlsupported;
	}

	public int getMlinuse() {
		return mlinuse;
	}

	public void setMlinuse(int mlinuse) {
		this.mlinuse = mlinuse;
	}

	public String getHandlecolumnname() {
		return handlecolumnname;
	}

	public void setHandlecolumnname(String handlecolumnname) {
		this.handlecolumnname = handlecolumnname;
	}

	public String getMaxattributeid() {
		return maxattributeid;
	}

	public void setMaxattributeid(String maxattributeid) {
		this.maxattributeid = maxattributeid;
	}

	public int getRestricted() {
		return restricted;
	}

	public void setRestricted(int restricted) {
		this.restricted = restricted;
	}

	public int getLocalizable() {
		return localizable;
	}

	public void setLocalizable(int localizable) {
		this.localizable = localizable;
	}

	public String getTextdirection() {
		return textdirection;
	}

	public void setTextdirection(String textdirection) {
		this.textdirection = textdirection;
	}

	public String getComplexexpression() {
		return complexexpression;
	}

	public void setComplexexpression(String complexexpression) {
		this.complexexpression = complexexpression;
	}

	private static final String INSERTMAXATTRIBUTECFG = "insert into maxattributecfg ( OBJECTNAME, ATTRIBUTENAME, ALIAS, AUTOKEYNAME, ATTRIBUTENO, CANAUTONUM, CLASSNAME, COLUMNNAME, DEFAULTVALUE, DOMAINID, EAUDITENABLED, ENTITYNAME, ESIGENABLED, ISLDOWNER, ISPOSITIVE, LENGTH, MAXTYPE, MUSTBE, REQUIRED, PERSISTENT, PRIMARYKEYCOLSEQ, REMARKS, SAMEASATTRIBUTE, SAMEASOBJECT, SCALE, TITLE, USERDEFINED, CHANGED, SEARCHTYPE, MLSUPPORTED, MLINUSE, HANDLECOLUMNNAME, MAXATTRIBUTEID, RESTRICTED, LOCALIZABLE, TEXTDIRECTION, COMPLEXEXPRESSION) values ( '%s', '%s', '%s', '%s', %s, %s, '%s', '%s', '%s', '%s', %s, '%s', %s, %s, %s, %s, '%s', %s, %s, %s, %s, '%s', '%s', '%s', %s, '%s', %s,'%s', '%s', %s, %s, '%s', %s, %s, %s, '%s', '%s');";

	public String toInsertSql() {
		return String.format(INSERTMAXATTRIBUTECFG, this.objectname, this.attributename, this.alias, this.autokeyname,
				this.attributeno, this.canautonum, this.classname, this.columnname, this.defaultvalue, this.domainid,
				this.eauditenabled, this.entityname, this.esigenabled, this.isldowner, this.ispositive, this.length,
				this.maxtype, this.mustbe, this.required, this.persistent, this.primarykeycolseq, this.remarks,
				this.sameasattribute, this.sameasobject, this.scale, this.title, this.userdefined, this.changed,
				this.searchtype, this.mlsupported, this.mlinuse, this.handlecolumnname, this.maxattributeid,
				this.restricted, this.localizable, this.textdirection, this.complexexpression);
	}

}
