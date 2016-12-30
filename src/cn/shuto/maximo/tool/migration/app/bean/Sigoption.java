package cn.shuto.maximo.tool.migration.app.bean;

import java.io.Serializable;

public class Sigoption implements Serializable {

	private static final long serialVersionUID = 1L;

	private String app;
	private String optionname;
	private String description;
	private int esigenabled;
	private int visible;
	private String alsogrants;
	private String alsorevokes;
	private String prerequisite;
	private String sigoptionid = "SIGOPTIONSEQ.nextval";
	private String langcode;
	private int hasld;

	public Sigoption(String app, String optionname, String description, int esigenabled, int visible, String alsogrants,
			String alsorevokes, String prerequisite, String langcode, int hasld) {
		super();
		this.app = app;
		this.optionname = optionname;
		this.description = description;
		this.esigenabled = esigenabled;
		this.visible = visible;
		this.alsogrants = alsogrants;
		this.alsorevokes = alsorevokes;
		this.prerequisite = prerequisite;
		this.langcode = langcode;
		this.hasld = hasld;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getOptionname() {
		return optionname;
	}

	public void setOptionname(String optionname) {
		this.optionname = optionname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEsigenabled() {
		return esigenabled;
	}

	public void setEsigenabled(int esigenabled) {
		this.esigenabled = esigenabled;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public String getAlsogrants() {
		return alsogrants;
	}

	public void setAlsogrants(String alsogrants) {
		this.alsogrants = alsogrants;
	}

	public String getAlsorevokes() {
		return alsorevokes;
	}

	public void setAlsorevokes(String alsorevokes) {
		this.alsorevokes = alsorevokes;
	}

	public String getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}

	public String getSigoptionid() {
		return sigoptionid;
	}

	public void setSigoptionid(String sigoptionid) {
		this.sigoptionid = sigoptionid;
	}

	public String getLangcode() {
		return langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

	public int getHasld() {
		return hasld;
	}

	public void setHasld(int hasld) {
		this.hasld = hasld;
	}

	private static final String INSERTSIGOPTION = "insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD) values ( '%s', '%s', '%s', %s, %s, '%s', '%s', '%s', %s, '%s', %s)";

	public String toInsertSql() {
		return String.format(INSERTSIGOPTION, this.app, this.optionname, this.description, this.esigenabled,
				this.visible, this.alsogrants, this.alsorevokes, this.prerequisite, this.sigoptionid, this.langcode,
				this.hasld);
	}
}
