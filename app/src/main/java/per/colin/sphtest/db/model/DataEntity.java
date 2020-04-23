package per.colin.sphtest.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataEntity {

    public int count = 0;

    @PrimaryKey
    @NonNull
    public String key;

    @ColumnInfo(name = "year")
    public String year;

    @ColumnInfo(name = "q1")
    public String q1;
    @ColumnInfo(name = "q2")
    public String q2;
    @ColumnInfo(name = "q3")
    public String q3;
    @ColumnInfo(name = "q4")
    public String q4;

    @ColumnInfo(name = "m1")
    public Boolean m1 = false;
    @ColumnInfo(name = "m2")
    public Boolean m2 = false;
    @ColumnInfo(name = "m3")
    public Boolean m3 = false;
    @ColumnInfo(name = "m4")
    public Boolean m4 = false;
}
