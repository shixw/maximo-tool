package cn.shuto.maximo.tool.migration.app.bean;

import java.io.Serializable;

public class MaxMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	private String menutype;
	private String moduleapp;
	private int position;
	private int subposition;
	private String elementtype;
	private String keyvalue;
	private String headerdescription;
	private String url;
	private int visible;
	private String image;
	private String accesskey;
	private String tabdisplay;
	private String maxmenuid = "MAXMENUSEQ.nextval";

	public MaxMenu(String menutype, String moduleapp, int position, int subposition, String elementtype,
			String keyvalue, String headerdescription, String url, int visible, String image, String accesskey,
			String tabdisplay) {
		super();
		this.menutype = menutype;
		this.moduleapp = moduleapp;
		this.position = position;
		this.subposition = subposition;
		this.elementtype = elementtype;
		this.keyvalue = keyvalue;
		this.headerdescription = headerdescription;
		this.url = url;
		this.visible = visible;
		this.image = image;
		this.accesskey = accesskey;
		this.tabdisplay = tabdisplay;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public String getModuleapp() {
		return moduleapp;
	}

	public void setModuleapp(String moduleapp) {
		this.moduleapp = moduleapp;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getSubposition() {
		return subposition;
	}

	public void setSubposition(int subposition) {
		this.subposition = subposition;
	}

	public String getElementtype() {
		return elementtype;
	}

	public void setElementtype(String elementtype) {
		this.elementtype = elementtype;
	}

	public String getKeyvalue() {
		return keyvalue;
	}

	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}

	public String getHeaderdescription() {
		return headerdescription;
	}

	public void setHeaderdescription(String headerdescription) {
		this.headerdescription = headerdescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getTabdisplay() {
		return tabdisplay;
	}

	public void setTabdisplay(String tabdisplay) {
		this.tabdisplay = tabdisplay;
	}

	public String getMaxmenuid() {
		return maxmenuid;
	}

	public void setMaxmenuid(String maxmenuid) {
		this.maxmenuid = maxmenuid;
	}

	private static final String INSERTMAXMENU = "insert into maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( '%s', '%s', %s, %s, '%s', '%s', '%s', '%s', %s, '%s', '%s', '%s', %s)";

	public String toInsertSql() {
		return String.format(INSERTMAXMENU, this.menutype, this.moduleapp, this.position, this.subposition,
				this.elementtype, this.keyvalue, this.headerdescription, this.url, this.visible, this.image,
				this.accesskey, this.tabdisplay, this.maxmenuid);
	}

}
