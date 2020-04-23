package per.colin.sphtest.act.layoutmanager;


import androidx.recyclerview.widget.RecyclerView;

import per.colin.sphtest.act.adapter.BaseRecyclerAdapter;

/**
 * Created by holenzhou on 16/5/11.
 * 为了避免强转以及if else的混乱，这里使用策略模式对原生LayoutManager进行一下处理
 */
public interface ILayoutManager {

    int getFirstVisibleItemPosition();

    int getLastVisibleItemPosition();

    RecyclerView.LayoutManager getLayoutManager();

    void setRecyclerAdapter(BaseRecyclerAdapter adapter);

    boolean isScrollToFooter(int itemCount);

}
