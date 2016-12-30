package cn.shuto.maximo.tool.migration.app.bean;

import java.io.Serializable;

public class MaxPresentation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String app;
	private String presentation;
	private String maxpresentationid = "maxpresentationseq.nextval";

	public MaxPresentation(String app, String presentation) {
		super();
		this.app = app;
		this.presentation = presentation;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getMaxpresentationid() {
		return maxpresentationid;
	}

	public void setMaxpresentationid(String maxpresentationid) {
		this.maxpresentationid = maxpresentationid;
	}

	private static final String INSERTMAXPRESENTATION = "insert into maxpresentation (app, maxpresentationid, presentation) Values ('%s',%s,EMPTY_CLOB())";

	public String toInsertSql() {
		return String.format(INSERTMAXPRESENTATION, this.app,this.maxpresentationid);
	}

}
