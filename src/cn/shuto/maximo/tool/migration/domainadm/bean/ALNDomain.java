package cn.shuto.maximo.tool.migration.domainadm.bean;

import java.io.Serializable;

public class ALNDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	private String domainid;
	private String value;
	private String description;
	private String siteid;
	private String orgid;
	private String alndomainid = "alndomainseq.nextval";
	private String valueid;

	public ALNDomain(String domainid, String value, String description, String siteid, String orgid, String valueid) {
		super();
		this.domainid = domainid;
		this.value = value;
		this.description = description;
		this.siteid = siteid;
		this.orgid = orgid;
		this.valueid = valueid;
	}

	public String getDomainid() {
		return domainid;
	}

	public void setDomainid(String domainid) {
		this.domainid = domainid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getAlndomainid() {
		return alndomainid;
	}

	public void setAlndomainid(String alndomainid) {
		this.alndomainid = alndomainid;
	}

	public String getValueid() {
		return valueid;
	}

	public void setValueid(String valueid) {
		this.valueid = valueid;
	}

	private static final String INSERTALNDOMAIN = "insert into ALNDOMAIN (DOMAINID, VALUE, DESCRIPTION, SITEID, ORGID, ALNDOMAINID, VALUEID) values ('%s', '%s', '%s', '%s', '%s', %s, '%s')";

	public String toInsertSql() {
		return String.format(INSERTALNDOMAIN, this.domainid, this.value, this.description, this.siteid, this.orgid,
				this.alndomainid, this.valueid);
	}

}
