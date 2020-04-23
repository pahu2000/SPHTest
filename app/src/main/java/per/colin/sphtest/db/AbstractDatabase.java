package per.colin.sphtest.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import per.colin.sphtest.db.dao.DataDao;
import per.colin.sphtest.db.model.DataEntity;

@Database(entities = {DataEntity.class}, version = 1, exportSchema = false)
public abstract class AbstractDatabase extends RoomDatabase {
    /**
     * @return
     */
    public abstract DataDao dataDao();
}