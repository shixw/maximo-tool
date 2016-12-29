package cn.shuto.maximo.tool.migration.dbconfig.bean;

import java.io.Serializable;

public class Autokey implements Serializable {

	private static final long serialVersionUID = 1L;

	private String prefix;
	private int seed;
	private String orgid;
	private String siteid;
	private String autokeyname;
	private String setid;
	private String langcode;
	private String autokeyid = "AUTOKEYSEQ.nextval";

	public Autokey(String prefix, int seed, String orgid, String siteid, String autokeyname, String setid,
			String langcode) {
		super();
		this.prefix = prefix;
		this.seed = seed;
		this.orgid = orgid;
		this.siteid = siteid;
		this.autokeyname = autokeyname;
		this.setid = setid;
		this.langcode = langcode;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getAutokeyname() {
		return autokeyname;
	}

	public void setAutokeyname(String autokeyname) {
		this.autokeyname = autokeyname;
	}

	public String getSetid() {
		return setid;
	}

	public void setSetid(String setid) {
		this.setid = setid;
	}

	public String getLangcode() {
		return langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

	public String getAutokeyid() {
		return autokeyid;
	}

	public void setAutokeyid(String autokeyid) {
		this.autokeyid = autokeyid;
	}

	private static final String INSERTAUTOKEY = "insert into autokey ( PREFIX, SEED, ORGID, SITEID, AUTOKEYNAME, SETID, LANGCODE, AUTOKEYID) values ( '%s', %s, '%s', '%s', '%s', '%s', '%s', %s);";

	public String toInsertSql() {
		return String.format(INSERTAUTOKEY, this.prefix, this.seed, this.orgid, this.siteid, this.autokeyname,
				this.setid, this.langcode, this.autokeyid);
	}

}
