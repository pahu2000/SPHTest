package per.colin.sphtest.repo;

import androidx.activity.OnBackPressedCallback;

import java.util.ArrayList;
import java.util.List;

import per.colin.sphtest.db.model.DataEntity;
import per.colin.sphtest.http.NetCallBack;
import per.colin.sphtest.http.NetHelper;
import per.colin.sphtest.http.QueryDataResp;
import per.colin.sphtest.presenter.PreCallback;
import per.colin.sphtest.utils.ThreadPoolUtil;

public class WebRepository {

    private static WebRepository instance;

    private String host = "https://data.gov.sg";

    private WebRepository() {

    }

    public static WebRepository getInstance() {
        if(instance == null) {
            instance = new WebRepository();
        }
        return instance;
    }

    public void queryData(int limit, int offset, PreCallback<List<DataEntity>> callback) {

        String url = "/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=" + limit + "&offset=" + offset;
        NetHelper.get(host + url, null, new NetCallBack<QueryDataResp>() {
            @Override
            public void call(QueryDataResp response) {
                List<DataEntity> entities = new ArrayList<>();
                List<QueryDataResp.ResultBean.RecordsBean> datas = response.result.records;
                QueryDataResp.ResultBean.RecordsBean lastData = null;
                DataEntity entity = new DataEntity();
                for (int i = 0; i < datas.size(); i++) {
                    // 计数实际消耗的记录
                    if (i == 0 && offset != 0) {
                        lastData = datas.get(0);
                        continue;
                    }
                    entity.count++;
                    QueryDataResp.ResultBean.RecordsBean record = datas.get(i);
                    String[] yearAndQu = record.quarter.split("-");
                    if (lastData == null) {
                        entity.year = yearAndQu[0];
                        switch (yearAndQu[1]) {
                            case "Q1":
                                entity.q1 = record.volumeOfMobileData;
                                break;
                            case "Q2":
                                entity.q2 = record.volumeOfMobileData;
                                break;
                            case "Q3":
                                entity.q3 = record.volumeOfMobileData;
                                break;
                            case "Q4":
                                entity.q4 = record.volumeOfMobileData;
                                break;
                            default:
                                break;
                        }
                    } else {
                        String[] lastYearAndQu = lastData.quarter.split("-");
                        entity.year = yearAndQu[0];
                        switch (yearAndQu[1]) {
                            case "Q1":
                                entity.q1 = record.volumeOfMobileData;
                                entity.m1 = record.getVolumeOfMobileData() < lastData.getVolumeOfMobileData();
                                break;
                            case "Q2":
                                entity.q2 = record.volumeOfMobileData;
                                entity.m2 = record.getVolumeOfMobileData() < lastData.getVolumeOfMobileData();
                                break;
                            case "Q3":
                                entity.q3 = record.volumeOfMobileData;
                                entity.m3 = record.getVolumeOfMobileData() < lastData.getVolumeOfMobileData();
                                break;
                            case "Q4":
                                entity.q4 = record.volumeOfMobileData;
                                entity.m4 = record.getVolumeOfMobileData() < lastData.getVolumeOfMobileData();
                                entities.add(entity);
                                entity = new DataEntity();
                                break;
                            default:
                                break;
                        }
                    }
                    lastData = record;
                }
                if(1 < datas.size() && datas.size() < limit) {
                    entities.add(entity);
                }
                callback.call(entities);
            }

        });
    }
}
