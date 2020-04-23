package per.colin.sphtest.http;

import android.util.Log;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import per.colin.sphtest.utils.ThreadPoolUtil;

/**
 * 请求工具类
 */
public class NetHelper {

    private static OkHttpClient okHttpClient;

    public static <T> void get(String url, BaseRequest params, NetCallBack callback) {
        //1.创建OkHttpClient对象
        if(okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        final Call call = okHttpClient.newCall(request);

//        String data = "{\"result\": {\"resource_id\":\"a807b7ab-6cad-4aa6-87d0-e283a7353a0f\",\"fields\":[{\"type\":\"int4\",\"id\":\"_id\"},{\"type\":\"text\",\"id\":\"quarter\"},{\"type\":\"numeric\",\"id\":\"volume_of_mobile_data\"}],\"records\":[{\"volume_of_mobile_data\":\"0.000384\",\"quarter\":\"2004-Q3\",\"_id\":1},{\"volume_of_mobile_data\":\"0.000543\",\"quarter\":\"2004-Q4\",\"_id\":2},{\"volume_of_mobile_data\":\"0.00062\",\"quarter\":\"2005-Q1\",\"_id\":3},{\"volume_of_mobile_data\":\"0.000634\",\"quarter\":\"2005-Q2\",\"_id\":4},{\"volume_of_mobile_data\":\"0.000718\",\"quarter\":\"2005-Q3\",\"_id\":5}],\"_links\":{\"start\":\"/api/action/datastore_search?limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\",\"next\":\"/api/action/datastore_search?offset=5&limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\"},\"limit\":5,\"total\":59}}";
//        QueryDataResp response = JSON.parseObject(data, QueryDataResp.class);
//        callback.call(response);

        //4.请求加入调度，重写回调方法
        ThreadPoolUtil.execute( () -> call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(@Nullable Call call, @Nullable IOException e) {
                if (e != null) {
                    e.printStackTrace();
                }
            }

            //请求成功执行的方法
            @Override
            public void onResponse(@Nullable Call call, @Nullable Response response) throws IOException {
                final String rtn = response != null ? response.body().string() : null;
                QueryDataResp result = JSON.parseObject(rtn, QueryDataResp.class);
                Log.i("network", JSON.toJSONString(result));
                callback.call(result);
            }
        }));

    }
}
