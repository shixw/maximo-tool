package cn.shuto.maximo.tool.migration.app.bean;

import java.io.Serializable;
import java.util.List;

public class MaxModules implements Serializable {

	private static final long serialVersionUID = 1L;

	private String module;
	private String description;
	private String maxmodulesid = "MAXMODULESSEQ.nextval";
	
	//关联的 表
	private List<MaxMenu> maxMenus;

	
	public List<MaxMenu> getMaxMenus() {
		return maxMenus;
	}

	public void setMaxMenus(List<MaxMenu> maxMenus) {
		this.maxMenus = maxMenus;
	}

	public MaxModules(String module, String description) {
		super();
		this.module = module;
		this.description = description;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxmodulesid() {
		return maxmodulesid;
	}

	public void setMaxmodulesid(String maxmodulesid) {
		this.maxmodulesid = maxmodulesid;
	}

	private static final String INSERTMAXMODULES = "insert into maxmodules ( MODULE, DESCRIPTION, MAXMODULESID) values ( '%s', '%s', %s)";

	public String toInsertSql() {
		return String.format(INSERTMAXMODULES, this.module, this.description, this.maxmodulesid);
	}

}
