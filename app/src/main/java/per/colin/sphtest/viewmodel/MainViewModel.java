package per.colin.sphtest.viewmodel;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import per.colin.sphtest.AppApplication;
import per.colin.sphtest.db.model.DataEntity;
import per.colin.sphtest.http.QueryDataResp;

public class MainViewModel extends BaseViewModel {

    public MutableLiveData<List<DataEntity>> data = new MutableLiveData<>();

}
