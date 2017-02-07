package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;
import java.util.List;

import cn.shuto.maximo.tool.migration.BeanInterface;

public class MaxAttributeCfg implements Serializable, BeanInterface {

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
	private String primarykeycolseq;
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

	// 关联的对象
	private List<Autokey> autokeys;

	public List<Autokey> getAutokeys() {
		return autokeys;
	}

	public void setAutokeys(List<Autokey> autokeys) {
		this.autokeys = autokeys;
	}

	public MaxAttributeCfg(String objectname, String attributename, String alias, String autokeyname, int attributeno,
			int canautonum, String classname, String columnname, String defaultvalue, String domainid,
			int eauditenabled, String entityname, int esigenabled, int isldowner, int ispositive, int length,
			String maxtype, int mustbe, int required, int persistent, String primarykeycolseq, String remarks,
			String sameasattribute, String sameasobject, int scale, String title, int userdefined, String searchtype,
			int mlsupported, int mlinuse, String handlecolumnname, int restricted, int localizable,
			String textdirection, String complexexpression) {
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

	public String getPrimarykeycolseq() {
		return primarykeycolseq;
	}

	public void setPrimarykeycolseq(String primarykeycolseq) {
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

	private static final String INSERTMAXATTRIBUTECFG = "insert into maxattributecfg ( OBJECTNAME, ATTRIBUTENAME, ALIAS, AUTOKEYNAME, ATTRIBUTENO, CANAUTONUM, CLASSNAME, COLUMNNAME, DEFAULTVALUE, DOMAINID, EAUDITENABLED, ENTITYNAME, ESIGENABLED, ISLDOWNER, ISPOSITIVE, LENGTH, MAXTYPE, MUSTBE, REQUIRED, PERSISTENT, PRIMARYKEYCOLSEQ, REMARKS, SAMEASATTRIBUTE, SAMEASOBJECT, SCALE, TITLE, USERDEFINED, CHANGED, SEARCHTYPE, MLSUPPORTED, MLINUSE, HANDLECOLUMNNAME, MAXATTRIBUTEID, RESTRICTED, LOCALIZABLE, TEXTDIRECTION, COMPLEXEXPRESSION) values ( '%s', '%s', '%s', '%s', %s, %s, '%s', '%s', '%s', '%s', %s, '%s', %s, %s, %s, %s, '%s', %s, %s, %s, '%s', '%s', '%s', '%s', %s, '%s', %s,'%s', '%s', %s, %s, '%s', %s, %s, %s, '%s', '%s')";

	public String toInsertSql() {
		return String.format(INSERTMAXATTRIBUTECFG, this.objectname, this.attributename, this.alias, this.autokeyname,
				this.attributeno, this.canautonum, this.classname, this.columnname, this.defaultvalue, this.domainid,
				this.eauditenabled, this.entityname, this.esigenabled, this.isldowner, this.ispositive, this.length,
				this.maxtype, this.mustbe, this.required, this.persistent, this.primarykeycolseq, this.remarks,
				this.sameasattribute, this.sameasobject, this.scale, this.title, this.userdefined, this.changed,
				this.searchtype, this.mlsupported, this.mlinuse, this.handlecolumnname, this.maxattributeid,
				this.restricted, this.localizable, this.textdirection, this.complexexpression);
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
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((attributename == null) ? 0 : attributename.hashCode());
		result = prime * result + ((autokeyname == null) ? 0 : autokeyname.hashCode());
		result = prime * result + ((autokeys == null) ? 0 : autokeys.hashCode());
		result = prime * result + canautonum;
		result = prime * result + ((classname == null) ? 0 : classname.hashCode());
		result = prime * result + ((columnname == null) ? 0 : columnname.hashCode());
		result = prime * result + ((complexexpression == null) ? 0 : complexexpression.hashCode());
		result = prime * result + ((defaultvalue == null) ? 0 : defaultvalue.hashCode());
		result = prime * result + ((domainid == null) ? 0 : domainid.hashCode());
		result = prime * result + eauditenabled;
		result = prime * result + ((entityname == null) ? 0 : entityname.hashCode());
		result = prime * result + esigenabled;
		result = prime * result + ((handlecolumnname == null) ? 0 : handlecolumnname.hashCode());
		result = prime * result + isldowner;
		result = prime * result + ispositive;
		result = prime * result + length;
		result = prime * result + localizable;
		result = prime * result + ((maxtype == null) ? 0 : maxtype.hashCode());
		result = prime * result + mlinuse;
		result = prime * result + mlsupported;
		result = prime * result + mustbe;
		result = prime * result + ((objectname == null) ? 0 : objectname.hashCode());
		result = prime * result + persistent;
		result = prime * result + ((primarykeycolseq == null) ? 0 : primarykeycolseq.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + required;
		result = prime * result + restricted;
		result = prime * result + ((sameasattribute == null) ? 0 : sameasattribute.hashCode());
		result = prime * result + ((sameasobject == null) ? 0 : sameasobject.hashCode());
		result = prime * result + scale;
		result = prime * result + ((searchtype == null) ? 0 : searchtype.hashCode());
		result = prime * result + ((textdirection == null) ? 0 : textdirection.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + userdefined;
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
		MaxAttributeCfg other = (MaxAttributeCfg) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (attributename == null) {
			if (other.attributename != null)
				return false;
		} else if (!attributename.equals(other.attributename))
			return false;
		if (autokeyname == null) {
			if (other.autokeyname != null)
				return false;
		} else if (!autokeyname.equals(other.autokeyname))
			return false;
		if (canautonum != other.canautonum)
			return false;
		if (classname == null) {
			if (other.classname != null)
				return false;
		} else if (!classname.equals(other.classname))
			return false;
		if (columnname == null) {
			if (other.columnname != null)
				return false;
		} else if (!columnname.equals(other.columnname))
			return false;
		if (complexexpression == null) {
			if (other.complexexpression != null)
				return false;
		} else if (!complexexpression.equals(other.complexexpression))
			return false;
		if (defaultvalue == null) {
			if (other.defaultvalue != null)
				return false;
		} else if (!defaultvalue.equals(other.defaultvalue))
			return false;
		if (domainid == null) {
			if (other.domainid != null)
				return false;
		} else if (!domainid.equals(other.domainid))
			return false;
		if (eauditenabled != other.eauditenabled)
			return false;
		if (entityname == null) {
			if (other.entityname != null)
				return false;
		} else if (!entityname.equals(other.entityname))
			return false;
		if (esigenabled != other.esigenabled)
			return false;
		if (handlecolumnname == null) {
			if (other.handlecolumnname != null)
				return false;
		} else if (!handlecolumnname.equals(other.handlecolumnname))
			return false;
		if (isldowner != other.isldowner)
			return false;
		if (ispositive != other.ispositive)
			return false;
		if (length != other.length)
			return false;
		if (localizable != other.localizable)
			return false;
		if (maxtype == null) {
			if (other.maxtype != null)
				return false;
		} else if (!maxtype.equals(other.maxtype))
			return false;
		if (mlinuse != other.mlinuse)
			return false;
		if (mlsupported != other.mlsupported)
			return false;
		if (mustbe != other.mustbe)
			return false;
		if (objectname == null) {
			if (other.objectname != null)
				return false;
		} else if (!objectname.equals(other.objectname))
			return false;
		if (persistent != other.persistent)
			return false;
		if (primarykeycolseq == null) {
			if (other.primarykeycolseq != null)
				return false;
		} else if (!primarykeycolseq.equals(other.primarykeycolseq))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (required != other.required)
			return false;
		if (restricted != other.restricted)
			return false;
		if (sameasattribute == null) {
			if (other.sameasattribute != null)
				return false;
		} else if (!sameasattribute.equals(other.sameasattribute))
			return false;
		if (sameasobject == null) {
			if (other.sameasobject != null)
				return false;
		} else if (!sameasobject.equals(other.sameasobject))
			return false;
		if (scale != other.scale)
			return false;
		if (searchtype == null) {
			if (other.searchtype != null)
				return false;
		} else if (!searchtype.equals(other.searchtype))
			return false;
		if (textdirection == null) {
			if (other.textdirection != null)
				return false;
		} else if (!textdirection.equals(other.textdirection))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userdefined != other.userdefined)
			return false;
		return true;
	}

	private static final String UPDATEMAXATTRIBUTE = "update maxattributecfg set ALIAS='%s',AUTOKEYNAME='%s',CANAUTONUM=%s,CLASSNAME='%s',COLUMNNAME='%s',DEFAULTVALUE='%s',DOMAINID='%s',EAUDITENABLED=%s,ENTITYNAME='%s',ESIGENABLED=%s,ISLDOWNER=%s,ISPOSITIVE=%s,LENGTH=%s,MAXTYPE='%s',MUSTBE=%s,REQUIRED=%s,PERSISTENT=%s,PRIMARYKEYCOLSEQ='%s',REMARKS='%s',SAMEASATTRIBUTE='%s',SAMEASOBJECT='%s',SCALE=%s,TITLE='%s',USERDEFINED=%s,CHANGED='U',SEARCHTYPE='%s',MLSUPPORTED=%s,MLINUSE=%s,HANDLECOLUMNNAME='%s',RESTRICTED=%s,LOCALIZABLE=%s,TEXTDIRECTION='%s',COMPLEXEXPRESSION='%s' where OBJECTNAME = '%s' and attributename = '%s'";

	@Override
	public String toUpdateSql() {
		return String.format(UPDATEMAXATTRIBUTE, this.alias, this.autokeyname, this.canautonum, this.classname,
				this.columnname, this.defaultvalue, this.domainid, this.eauditenabled, this.entityname,
				this.esigenabled, this.isldowner, this.ispositive, this.length, this.maxtype, this.mustbe,
				this.required, this.persistent, this.primarykeycolseq, this.remarks, this.sameasattribute,
				this.sameasobject, this.scale, this.title, this.userdefined, this.searchtype, this.mlsupported,
				this.mlinuse, this.handlecolumnname, this.restricted, this.localizable, this.textdirection,
				this.complexexpression, this.objectname, this.attributename);
	}

	@Override
	public String getImportUniqueRecordSql() {
		return "select OBJECTNAME, ATTRIBUTENAME, ALIAS, AUTOKEYNAME, ATTRIBUTENO, CANAUTONUM, CLASSNAME, COLUMNNAME, DEFAULTVALUE, DOMAINID, EAUDITENABLED, ENTITYNAME, ESIGENABLED, ISLDOWNER, ISPOSITIVE, LENGTH, MAXTYPE, MUSTBE, REQUIRED, PERSISTENT, PRIMARYKEYCOLSEQ, REMARKS, SAMEASATTRIBUTE, SAMEASOBJECT, SCALE, TITLE, USERDEFINED, CHANGED, SEARCHTYPE, MLSUPPORTED, MLINUSE, HANDLECOLUMNNAME, MAXATTRIBUTEID, RESTRICTED, LOCALIZABLE, TEXTDIRECTION, COMPLEXEXPRESSION from maxattributecfg where objectname = '"
				+ this.objectname + "' and attributename = '" + this.attributename + "'";
	}

}
