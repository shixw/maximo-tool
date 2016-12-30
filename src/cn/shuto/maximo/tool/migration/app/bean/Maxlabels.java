package cn.shuto.maximo.tool.migration.app.bean;

import java.io.Serializable;

public class Maxlabels implements Serializable {

	private static final long serialVersionUID = 1L;

	private String app;
	private String id;
	private String property;
	private String value;
	private String maxlabelsid = "MAXLABELSSEQ.nextval";

	public Maxlabels(String app, String id, String property, String value) {
		super();
		this.app = app;
		this.id = id;
		this.property = property;
		this.value = value;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMaxlabelsid() {
		return maxlabelsid;
	}

	public void setMaxlabelsid(String maxlabelsid) {
		this.maxlabelsid = maxlabelsid;
	}

	private static final String INSERTMAXLABELS = "insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( '%s', '%s', '%s', '%s', %s)";

	public String toInsertSql() {
		return String.format(INSERTMAXLABELS, this.app, this.id, this.property, this.value, this.maxlabelsid);
	}

}
