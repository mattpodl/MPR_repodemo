package jdbcdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbcdemo.domain.IHaveId;

public abstract class RepositoryBase<TEntity extends IHaveId> {

	protected Connection connection;
	protected Statement createTable;
	protected PreparedStatement insert;
	protected PreparedStatement selectAll;
	protected PreparedStatement update;
	protected PreparedStatement delete;

	RepositoryBase(){
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "SA","");
			createTable = connection.createStatement();
			insert = connection.prepareStatement(insertSql());
			update = connection.prepareStatement(updateSql());
			delete = connection.prepareStatement(deleteSql());
			selectAll = connection.prepareStatement(selectAllSql());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected abstract String insertSql();
	protected abstract String updateSql();
	protected abstract String deleteSql();
	protected abstract String selectAllSql();

	protected abstract String tableName();
	protected abstract String createTableSql();

	protected void createTable(){
		try {

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase(tableName())){
					tableExists=true;
					break;
				}
			}
			if(!tableExists)
				createTable.executeUpdate(createTableSql());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
