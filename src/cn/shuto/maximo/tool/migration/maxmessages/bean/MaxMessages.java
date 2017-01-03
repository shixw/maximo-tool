package cn.shuto.maximo.tool.migration.maxmessages.bean;

import java.io.Serializable;

public class MaxMessages implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msgkey;
	private String msggroup;
	private String value;
	private String title;
	private String displaymethod;
	private int options;
	private String buttontext;
	private String maxmessagesid = "maxmessagesseq.nextval";
	private String msgid;
	private String explanation;
	private String adminresponse;
	private String operatorresponse;
	private String systemaction;
	private int prefix;

	public MaxMessages(String msgkey, String msggroup, String value, String title, String displaymethod, int options,
			String buttontext, String msgid, String explanation, String adminresponse, String operatorresponse,
			String systemaction, int prefix) {
		super();
		this.msgkey = msgkey;
		this.msggroup = msggroup;
		this.value = value;
		this.title = title;
		this.displaymethod = displaymethod;
		this.options = options;
		this.buttontext = buttontext;
		this.msgid = msgid;
		this.explanation = explanation;
		this.adminresponse = adminresponse;
		this.operatorresponse = operatorresponse;
		this.systemaction = systemaction;
		this.prefix = prefix;
	}

	public String getMsgkey() {
		return msgkey;
	}

	public void setMsgkey(String msgkey) {
		this.msgkey = msgkey;
	}

	public String getMsggroup() {
		return msggroup;
	}

	public void setMsggroup(String msggroup) {
		this.msggroup = msggroup;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDisplaymethod() {
		return displaymethod;
	}

	public void setDisplaymethod(String displaymethod) {
		this.displaymethod = displaymethod;
	}

	public int getOptions() {
		return options;
	}

	public void setOptions(int options) {
		this.options = options;
	}

	public String getButtontext() {
		return buttontext;
	}

	public void setButtontext(String buttontext) {
		this.buttontext = buttontext;
	}

	public String getMaxmessagesid() {
		return maxmessagesid;
	}

	public void setMaxmessagesid(String maxmessagesid) {
		this.maxmessagesid = maxmessagesid;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getAdminresponse() {
		return adminresponse;
	}

	public void setAdminresponse(String adminresponse) {
		this.adminresponse = adminresponse;
	}

	public String getOperatorresponse() {
		return operatorresponse;
	}

	public void setOperatorresponse(String operatorresponse) {
		this.operatorresponse = operatorresponse;
	}

	public String getSystemaction() {
		return systemaction;
	}

	public void setSystemaction(String systemaction) {
		this.systemaction = systemaction;
	}

	public int getPrefix() {
		return prefix;
	}

	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	private static final String INSERTMESSAGES = "insert into maxmessages (MSGKEY, MSGGROUP, VALUE, TITLE, DISPLAYMETHOD, OPTIONS, BUTTONTEXT, MAXMESSAGESID, MSGID, EXPLANATION, ADMINRESPONSE, OPERATORRESPONSE, SYSTEMACTION, PREFIX) values ('%s', '%s', '%s', '%s', '%s', %s, '%s', %s, '%s', '%s', '%s', '%s', '%s', %s)";

	public String toInsertSql() {
		return String.format(INSERTMESSAGES, this.msgkey, this.msggroup, this.value, this.title, this.displaymethod,
				this.options, this.buttontext, this.maxmessagesid, this.msgid, this.explanation, this.adminresponse,
				this.operatorresponse, this.systemaction, this.prefix);
	}
}
