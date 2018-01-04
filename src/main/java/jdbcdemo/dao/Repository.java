package jdbcdemo.dao;

import java.util.List;
import jdbcdemo.domain.IHaveId;

public interface Repository<TEntity extends IHaveId> {
	public void delete(TEntity entity);

	public List<TEntity> getAll();

	public void add(TEntity person);

	public void update(TEntity person);

	public void createTable();
}