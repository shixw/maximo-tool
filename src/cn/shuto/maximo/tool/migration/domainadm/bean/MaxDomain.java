package cn.shuto.maximo.tool.migration.domainadm.bean;

import java.io.Serializable;
import java.util.List;

import cn.shuto.maximo.tool.system.SystemEnvironmental;

public class MaxDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	private String domainid;
	private String description;
	private String domaintype;
	private String maxtype;
	private int length;
	private int scale;
	private String maxdomainid = "maxdomainseq.nextval";
	private int internal;

	//关联的表
	private List<ALNDomain> alnDomains;
	
	
	
	public List<ALNDomain> getAlnDomains() {
		return alnDomains;
	}

	public void setAlnDomains(List<ALNDomain> alnDomains) {
		this.alnDomains = alnDomains;
	}

	public MaxDomain(String domainid, String description, String domaintype, String maxtype, int length, int scale,
			int internal) {
		super();
		this.domainid = domainid;
		this.description = description;
		this.domaintype = domaintype;
		this.maxtype = maxtype;
		this.length = length;
		this.scale = scale;
		this.internal = internal;
	}

	public String getDomainid() {
		return domainid;
	}

	public void setDomainid(String domainid) {
		this.domainid = domainid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomaintype() {
		return domaintype;
	}

	public void setDomaintype(String domaintype) {
		this.domaintype = domaintype;
	}

	public String getMaxtype() {
		return maxtype;
	}

	public void setMaxtype(String maxtype) {
		this.maxtype = maxtype;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getMaxdomainid() {
		return maxdomainid;
	}

	public void setMaxdomainid(String maxdomainid) {
		this.maxdomainid = maxdomainid;
	}

	public int getInternal() {
		return internal;
	}

	public void setInternal(int internal) {
		this.internal = internal;
	}

	private static final String INSERTMAXDOMAIN75 = "insert into MAXDOMAIN (DOMAINID, DESCRIPTION, DOMAINTYPE, MAXTYPE, LENGTH, SCALE, MAXDOMAINID, INTERNAL) values ('%s', '%s', '%s', '%s', %s, %s, %s, %s)";
	
	private static final String INSERTMAXDOMAIN76 = "insert into MAXDOMAIN (DOMAINID, DESCRIPTION, DOMAINTYPE, MAXTYPE, LENGTH, SCALE, MAXDOMAINID, INTERNAL, NEVERCACHE) values ('%s', '%s', '%s', '%s', %s, %s, %s, %s, 0)";

	public String toInsertSql() {
		String targetVersion = SystemEnvironmental.getInstance().getStringParam("-targetVersion");
		if(!targetVersion.isEmpty()&&"7.6".equals(targetVersion)){
			return String.format(INSERTMAXDOMAIN76, this.domainid, this.description, this.domaintype, this.maxtype,
					this.length, this.scale, this.maxdomainid, this.internal);
		}
		return String.format(INSERTMAXDOMAIN75, this.domainid, this.description, this.domaintype, this.maxtype,
				this.length, this.scale, this.maxdomainid, this.internal);
	}

}
