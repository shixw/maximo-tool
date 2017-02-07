package cn.shuto.maximo.tool.migration;

public interface BeanInterface {

	public String toInsertSql();

	public String toUpdateSql();

	public String getImportUniqueRecordSql();
}
