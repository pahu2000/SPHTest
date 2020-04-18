package per.colin.sphtest.http;

import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求工具类
 */
public class NetHelper {

    public static <T> void get(String url, BaseRequest params, Callback callback) {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
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

            }
        });
    }
}
