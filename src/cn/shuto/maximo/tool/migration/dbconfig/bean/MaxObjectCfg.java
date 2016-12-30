package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;
import java.util.List;

public class MaxObjectCfg implements Serializable {

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
	
	//关联对象
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
}
