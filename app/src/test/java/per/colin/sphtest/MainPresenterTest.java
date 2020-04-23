package per.colin.sphtest;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

import java.util.List;

import per.colin.sphtest.db.model.DataEntity;
import per.colin.sphtest.presenter.MainPresenter;
import per.colin.sphtest.presenter.PreCallback;
import per.colin.sphtest.repo.WebRepository;
import per.colin.sphtest.viewmodel.MainViewModel;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainPresenterTest {
    @Test
    public void queryData() {
        int limit = 10;
        int offset = 0;
        WebRepository.getInstance().queryData(limit, offset, new PreCallback<List<DataEntity>>() {
            @Override
            public void call(List<DataEntity> dataEntities) {
                Log.i("结果", JSON.toJSONString(dataEntities));
                System.out.println("结束");
            }
        });
    }
}