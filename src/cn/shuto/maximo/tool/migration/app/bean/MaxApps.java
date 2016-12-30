package cn.shuto.maximo.tool.migration.app.bean;

import java.io.Serializable;
import java.util.List;

public class MaxApps implements Serializable {

	private static final long serialVersionUID = 1L;

	private String app;
	private String description;
	private String apptype;
	private String restrictions;
	private String orderby;
	private String originalapp;
	private String custapptype;
	private String maintbname;
	private String maxappsid = "MAXAPPSSEQ.nextval";
	private String ismobile;

	//关联 
	private List<MaxMenu> maxMenus;
	private List<Sigoption> sigoptions;
	private List<Maxlabels> maxlabels;
	
	private MaxPresentation maxPresentation;
	
	public MaxPresentation getMaxPresentation() {
		return maxPresentation;
	}

	public void setMaxPresentation(MaxPresentation maxPresentation) {
		this.maxPresentation = maxPresentation;
	}

	public List<Maxlabels> getMaxlabels() {
		return maxlabels;
	}

	public void setMaxlabels(List<Maxlabels> maxlabels) {
		this.maxlabels = maxlabels;
	}

	public List<Sigoption> getSigoptions() {
		return sigoptions;
	}

	public void setSigoptions(List<Sigoption> sigoptions) {
		this.sigoptions = sigoptions;
	}

	public List<MaxMenu> getMaxMenus() {
		return maxMenus;
	}

	public void setMaxMenus(List<MaxMenu> maxMenus) {
		this.maxMenus = maxMenus;
	}

	public MaxApps(String app, String description, String apptype, String restrictions, String orderby,
			String originalapp, String custapptype, String maintbname, String ismobile) {
		super();
		this.app = app;
		this.description = description;
		this.apptype = apptype;
		this.restrictions = restrictions;
		this.orderby = orderby;
		this.originalapp = originalapp;
		this.custapptype = custapptype;
		this.maintbname = maintbname;
		this.ismobile = ismobile;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getOriginalapp() {
		return originalapp;
	}

	public void setOriginalapp(String originalapp) {
		this.originalapp = originalapp;
	}

	public String getCustapptype() {
		return custapptype;
	}

	public void setCustapptype(String custapptype) {
		this.custapptype = custapptype;
	}

	public String getMaintbname() {
		return maintbname;
	}

	public void setMaintbname(String maintbname) {
		this.maintbname = maintbname;
	}

	public String getMaxappsid() {
		return maxappsid;
	}

	public void setMaxappsid(String maxappsid) {
		this.maxappsid = maxappsid;
	}

	public String getIsmobile() {
		return ismobile;
	}

	public void setIsmobile(String ismobile) {
		this.ismobile = ismobile;
	}

	private static final String INSERTMAXAPPS = "insert into maxapps ( APP, DESCRIPTION, APPTYPE, RESTRICTIONS, ORDERBY, ORIGINALAPP, CUSTAPPTYPE, MAINTBNAME, MAXAPPSID, ISMOBILE) values ( '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s, '%s')";

	public String toInsertSql() {
		return String.format(INSERTMAXAPPS, this.app, this.description, this.apptype, this.restrictions, this.orderby,
				this.originalapp, this.custapptype, this.maintbname, this.maxappsid, this.ismobile);
	}

}
