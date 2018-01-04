package jdbcdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbcdemo.dao.mappers.ResultSetMapper;
import jdbcdemo.domain.IHaveId;


public abstract class RepositoryBase<TEntity extends IHaveId> implements Repository<TEntity>{

	protected Connection connection;
	protected Statement createTable;
	protected PreparedStatement insert;
	protected PreparedStatement selectAll;
	protected PreparedStatement update;
	protected PreparedStatement delete;

	ResultSetMapper<TEntity> mapper;

	RepositoryBase(Connection connection, ResultSetMapper<TEntity> mapper) throws SQLException {
		this.mapper = mapper;
		this.connection = connection;

		createTable = connection.createStatement();
		insert = connection.prepareStatement(insertSql());
		update = connection.prepareStatement(updateSql());
		delete = connection.prepareStatement(deleteSql());
		selectAll = connection.prepareStatement(selectAllSql());
	}

	protected abstract String insertSql();
	protected abstract String updateSql();
	protected abstract String deleteSql();
	protected abstract String selectAllSql();
	protected abstract String tableName();
	protected abstract String createTableSql();

	public void createTable(){
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

	public List<TEntity> getAll(){
		List<TEntity> result = new ArrayList<TEntity>();
		try {
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()){
				result.add(mapper.map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void delete(TEntity entity) {
		try{
			delete.setInt(1, entity.getId());
			delete.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	protected abstract void completeInsert(TEntity entity) throws SQLException;

	public void add(TEntity entity) {
		try{
			completeInsert(entity);
			insert.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	protected abstract void completeUpdate(TEntity entity) throws SQLException;

	public void update(TEntity entity) {

		try{
			completeUpdate(entity);
			update.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}


}
