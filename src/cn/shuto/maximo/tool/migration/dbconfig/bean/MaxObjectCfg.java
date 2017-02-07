package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;
import java.util.List;

import cn.shuto.maximo.tool.migration.BeanInterface;

public class MaxObjectCfg implements Serializable, BeanInterface {

	private static final long serialVersionUID = 1L;

	private String objectname;
	private String classname;
	private String description;
	private int eauditenabled;
	private String eauditfilter;
	private String entityname;
	private String esigfilter;
	private String extendsobject;
	private int imported;
	private int isview;
	private int persistent;
	private String servicename;
	private String siteorgtype;
	private int userdefined;
	private String changed = "I";
	private String mainobject;
	private String internal;
	private String maxobjectid = "MAXOBJECTCFGSEQ.nextval";
	private String textdirection;

	// 关联对象
	private MaxTableCfg maxTableCfg;
	private List<MaxAttributeCfg> maxAttributeCfgs;
	private List<MaxSysIndexes> maxSysIndexes;
	private List<MaxRelationship> maxRelationships;
	private List<MaxSequence> maxSequences;

	public List<MaxSequence> getMaxSequences() {
		return maxSequences;
	}

	public void setMaxSequences(List<MaxSequence> maxSequences) {
		this.maxSequences = maxSequences;
	}

	public List<MaxRelationship> getMaxRelationships() {
		return maxRelationships;
	}

	public void setMaxRelationships(List<MaxRelationship> maxRelationships) {
		this.maxRelationships = maxRelationships;
	}

	public List<MaxSysIndexes> getMaxSysIndexes() {
		return maxSysIndexes;
	}

	public void setMaxSysIndexes(List<MaxSysIndexes> maxSysIndexes) {
		this.maxSysIndexes = maxSysIndexes;
	}

	public List<MaxAttributeCfg> getMaxAttributeCfgs() {
		return maxAttributeCfgs;
	}

	public void setMaxAttributeCfgs(List<MaxAttributeCfg> maxAttributeCfgs) {
		this.maxAttributeCfgs = maxAttributeCfgs;
	}

	public MaxTableCfg getMaxTableCfg() {
		return maxTableCfg;
	}

	public void setMaxTableCfg(MaxTableCfg maxTableCfg) {
		this.maxTableCfg = maxTableCfg;
	}

	public MaxObjectCfg(String objectname, String classname, String description, int eauditenabled, String eauditfilter,
			String entityname, String esigfilter, String extendsobject, int imported, int isview, int persistent,
			String servicename, String siteorgtype, int userdefined, String mainobject, String internal,
			String textdirection) {
		super();
		this.objectname = objectname;
		this.classname = classname;
		this.description = description;
		this.eauditenabled = eauditenabled;
		this.eauditfilter = eauditfilter;
		this.entityname = entityname;
		this.esigfilter = esigfilter;
		this.extendsobject = extendsobject;
		this.imported = imported;
		this.isview = isview;
		this.persistent = persistent;
		this.servicename = servicename;
		this.siteorgtype = siteorgtype;
		this.userdefined = userdefined;
		this.mainobject = mainobject;
		this.internal = internal;
		this.textdirection = textdirection;
	}

	public String getObjectname() {
		return objectname;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEauditenabled() {
		return eauditenabled;
	}

	public void setEauditenabled(int eauditenabled) {
		this.eauditenabled = eauditenabled;
	}

	public String getEauditfilter() {
		return eauditfilter;
	}

	public void setEauditfilter(String eauditfilter) {
		this.eauditfilter = eauditfilter;
	}

	public String getEntityname() {
		return entityname;
	}

	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public String getEsigfilter() {
		return esigfilter;
	}

	public void setEsigfilter(String esigfilter) {
		this.esigfilter = esigfilter;
	}

	public String getExtendsobject() {
		return extendsobject;
	}

	public void setExtendsobject(String extendsobject) {
		this.extendsobject = extendsobject;
	}

	public int getImported() {
		return imported;
	}

	public void setImported(int imported) {
		this.imported = imported;
	}

	public int getIsview() {
		return isview;
	}

	public void setIsview(int isview) {
		this.isview = isview;
	}

	public int getPersistent() {
		return persistent;
	}

	public void setPersistent(int persistent) {
		this.persistent = persistent;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getSiteorgtype() {
		return siteorgtype;
	}

	public void setSiteorgtype(String siteorgtype) {
		this.siteorgtype = siteorgtype;
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

	public String getMainobject() {
		return mainobject;
	}

	public void setMainobject(String mainobject) {
		this.mainobject = mainobject;
	}

	public String getInternal() {
		return internal;
	}

	public void setInternal(String internal) {
		this.internal = internal;
	}

	public String getMaxobjectid() {
		return maxobjectid;
	}

	public void setMaxobjectid(String maxobjectid) {
		this.maxobjectid = maxobjectid;
	}

	public String getTextdirection() {
		return textdirection;
	}

	public void setTextdirection(String textdirection) {
		this.textdirection = textdirection;
	}

	private static final String INSERTMAXOBJECTCFG = "insert into maxobjectcfg ( OBJECTNAME, CLASSNAME, DESCRIPTION, EAUDITENABLED, EAUDITFILTER, ENTITYNAME, ESIGFILTER, EXTENDSOBJECT, IMPORTED, ISVIEW, PERSISTENT, SERVICENAME, SITEORGTYPE, USERDEFINED, CHANGED, MAINOBJECT, INTERNAL, MAXOBJECTID, TEXTDIRECTION) values ( '%s', '%s', '%s', %s , '%s', '%s', '%s', '%s', %s , %s , %s , '%s', '%s', %s ,'%s', %s , %s , %s, '%s')";

	public String toInsertSql() {
		return String.format(INSERTMAXOBJECTCFG, this.objectname, this.classname, this.description, this.eauditenabled,
				this.eauditfilter, this.entityname, this.esigfilter, this.extendsobject, this.imported, this.isview,
				this.persistent, this.servicename, this.siteorgtype, this.userdefined, this.changed, this.mainobject,
				this.internal, this.maxobjectid, this.textdirection);
	}

	private static final String UPDATEMAXOBJECTCFG = "update maxobjectcfg set CLASSNAME='%s',DESCRIPTION='%s',EAUDITENABLED=%s,EAUDITFILTER='%s',ENTITYNAME='%s',ESIGFILTER='%s',EXTENDSOBJECT='%s',IMPORTED=%s,ISVIEW=%s,PERSISTENT=%s,SERVICENAME='%s',SITEORGTYPE='%s',USERDEFINED=%s,CHANGED='U',MAINOBJECT=%s,INTERNAL=%s,TEXTDIRECTION='%s' where objectname = '%s'";

	@Override
	public String toUpdateSql() {
		return String.format(UPDATEMAXOBJECTCFG, this.classname, this.description, this.eauditenabled,
				this.eauditfilter, this.entityname, this.esigfilter, this.extendsobject, this.imported, this.isview,
				this.persistent, this.servicename, this.siteorgtype, this.userdefined, this.mainobject,
				this.internal, this.textdirection, this.objectname);
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
		result = prime * result + ((classname == null) ? 0 : classname.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + eauditenabled;
		result = prime * result + ((eauditfilter == null) ? 0 : eauditfilter.hashCode());
		result = prime * result + ((entityname == null) ? 0 : entityname.hashCode());
		result = prime * result + ((esigfilter == null) ? 0 : esigfilter.hashCode());
		result = prime * result + ((extendsobject == null) ? 0 : extendsobject.hashCode());
		result = prime * result + imported;
		result = prime * result + ((internal == null) ? 0 : internal.hashCode());
		result = prime * result + isview;
		result = prime * result + ((mainobject == null) ? 0 : mainobject.hashCode());
		result = prime * result + ((objectname == null) ? 0 : objectname.hashCode());
		result = prime * result + persistent;
		result = prime * result + ((servicename == null) ? 0 : servicename.hashCode());
		result = prime * result + ((siteorgtype == null) ? 0 : siteorgtype.hashCode());
		result = prime * result + ((textdirection == null) ? 0 : textdirection.hashCode());
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
		MaxObjectCfg other = (MaxObjectCfg) obj;
		if (classname == null) {
			if (other.classname != null)
				return false;
		} else if (!classname.equals(other.classname))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eauditenabled != other.eauditenabled)
			return false;
		if (eauditfilter == null) {
			if (other.eauditfilter != null)
				return false;
		} else if (!eauditfilter.equals(other.eauditfilter))
			return false;
		if (entityname == null) {
			if (other.entityname != null)
				return false;
		} else if (!entityname.equals(other.entityname))
			return false;
		if (esigfilter == null) {
			if (other.esigfilter != null)
				return false;
		} else if (!esigfilter.equals(other.esigfilter))
			return false;
		if (extendsobject == null) {
			if (other.extendsobject != null)
				return false;
		} else if (!extendsobject.equals(other.extendsobject))
			return false;
		if (imported != other.imported)
			return false;
		if (internal == null) {
			if (other.internal != null)
				return false;
		} else if (!internal.equals(other.internal))
			return false;
		if (isview != other.isview)
			return false;
		if (mainobject == null) {
			if (other.mainobject != null)
				return false;
		} else if (!mainobject.equals(other.mainobject))
			return false;
		if (objectname == null) {
			if (other.objectname != null)
				return false;
		} else if (!objectname.equals(other.objectname))
			return false;
		if (persistent != other.persistent)
			return false;
		if (servicename == null) {
			if (other.servicename != null)
				return false;
		} else if (!servicename.equals(other.servicename))
			return false;
		if (siteorgtype == null) {
			if (other.siteorgtype != null)
				return false;
		} else if (!siteorgtype.equals(other.siteorgtype))
			return false;
		if (textdirection == null) {
			if (other.textdirection != null)
				return false;
		} else if (!textdirection.equals(other.textdirection))
			return false;
		if (userdefined != other.userdefined)
			return false;
		return true;
	}

	@Override
	public String getImportUniqueRecordSql() {
		return "select OBJECTNAME, CLASSNAME, DESCRIPTION, EAUDITENABLED, EAUDITFILTER, ENTITYNAME, ESIGFILTER, EXTENDSOBJECT, IMPORTED, ISVIEW, PERSISTENT, SERVICENAME, SITEORGTYPE, USERDEFINED, CHANGED, MAINOBJECT, INTERNAL, MAXOBJECTID, TEXTDIRECTION from maxobjectcfg where objectname = '"
				+ this.objectname + "'";
	}

}
