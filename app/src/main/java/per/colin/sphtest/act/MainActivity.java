package per.colin.sphtest.act;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.tpa.client.tina.Tina;

import per.colin.sphtest.R;
import per.colin.sphtest.http.BaseResponse;
import per.colin.sphtest.http.NetCallBack;
import per.colin.sphtest.http.NetHelper;
import per.colin.sphtest.http.QueryDataResp;
import per.colin.sphtest.viewmodel.MainViewModel;

/**
 * @author pahu2000
 */
public class MainActivity extends BaseActivity {

    RecyclerView mRecyclerView;
    MainViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        String url = "https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=5";
//        String url = "https://www.baidu.com";
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        NetHelper.get(url, null, (NetCallBack<QueryDataResp>) response -> {
            runOnUiThread(() -> {
                Toast.makeText(this, "111111", Toast.LENGTH_SHORT).show();
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView = findViewById(R.id.recycler_view);
    }
}
