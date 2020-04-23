package per.colin.sphtest.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import per.colin.sphtest.db.model.DataEntity;

@Dao
public interface DataDao {

    @Query("SELECT * FROM dataentity")
    LiveData<List<DataEntity>> getAll();

    @Query("SELECT * FROM dataentity WHERE `key` = :key")
    LiveData<DataEntity> loadByKey(String key);

    @Insert
    void insertAll(DataEntity... dataEntities);

    @Delete
    void delete(DataEntity dataEntity);
}
