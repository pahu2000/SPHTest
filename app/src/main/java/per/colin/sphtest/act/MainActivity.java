package per.colin.sphtest.act;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import per.colin.sphtest.R;
import per.colin.sphtest.act.adapter.BaseRecyclerAdapter;
import per.colin.sphtest.act.layoutmanager.XLinearLayoutManager;
import per.colin.sphtest.act.viewholder.BaseViewHolder;
import per.colin.sphtest.act.widget.LoadMoreRecyclerView;
import per.colin.sphtest.db.model.DataEntity;
import per.colin.sphtest.presenter.MainPresenter;
import per.colin.sphtest.viewmodel.MainViewModel;

/**
 * @author pahu2000
 */
public class MainActivity extends BaseActivity {

    LoadMoreRecyclerView mRecyclerView;
    DataAdapter mAdapter;

    MainPresenter presenter;
    MainViewModel viewModel;

    private int limit = 5;
    private int offset = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setXLayoutManager(new XLinearLayoutManager(this));

        mAdapter = new DataAdapter(this, R.layout.data_item, new ArrayList<>());
        mAdapter.showLoadMoreFooter(true);
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setOnLoadMoreListener(() -> {
            if(mAdapter.getCount() == 0) {
                presenter.queryData(limit, offset);
            } else {
                // 多取前一次一条，用于判断相对于上个月是否有下降
                presenter.queryData(limit, offset - 1);
            }
        });

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.data.observe(this, resp -> {
            int useCount = 0;
            for(DataEntity e : resp) {
                useCount += e.count;
            }
            offset = offset + useCount;
            updateUI(resp);
            if(resp.size() == 0) {
                mRecyclerView.stopLoadMore();
                mRecyclerView.enableLoadMore(false);
            } else {
                mRecyclerView.stopLoadMore();
                mAdapter.showLoadMoreFooter(true);
            }


        } );

        presenter = new MainPresenter(viewModel);

        mRecyclerView.enableLoadMore(true);


//        presenter.queryData(url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    void updateUI(List<DataEntity> data) {
        if (mRecyclerView.getAdapter() != null && data != null) {
//            mAdapter.getData()
            mAdapter.addAll(data);
        }
    }

    public void onItemClick(View v) {
        Toast.makeText(this, "今年有流量减少", Toast.LENGTH_SHORT).show();
    }

    class DataAdapter extends BaseRecyclerAdapter<DataEntity> {

        private String year;

        public DataAdapter(Context context, int layoutResId, List<DataEntity> data) {
            super(context, layoutResId, data);
        }

        @Override
        protected void onBindData(BaseViewHolder holder, DataEntity item, int position) {
            TextView mYear = holder.getView().findViewById(R.id.year);
            TextView mQ1 = holder.getView().findViewById(R.id.label1);
            TextView mQ2 = holder.getView().findViewById(R.id.label2);
            TextView mQ3 = holder.getView().findViewById(R.id.label3);
            TextView mQ4 = holder.getView().findViewById(R.id.label4);
            View mImage = holder.getView().findViewById(R.id.image);

            mYear.setText(item.year);
            mQ1.setText(String.format("季度1\n %s", item.q1 != null ? item.q1 : "0"));
            mQ2.setText(String.format("季度2\n %s", item.q2 != null ? item.q2 : "0"));
            mQ3.setText(String.format("季度3\n %s", item.q3 != null ? item.q3 : "0"));
            mQ4.setText(String.format("季度4\n %s", item.q4 != null ? item.q4 : "0"));

            if(item.m1 || item.m2 || item.m3 || item.m4) {
                mImage.setVisibility(View.VISIBLE);
            } else {
                mImage.setVisibility(View.GONE);
            }
        }

        @Override
        public void addAll(List<DataEntity> data) {
            super.addAll(data);
        }
    }
}
