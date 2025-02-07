package per.colin.sphtest.act.layoutmanager;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import per.colin.sphtest.act.adapter.BaseRecyclerAdapter;

/**
 * Created by holenzhou on 16/5/11.
 */
public class XLinearLayoutManager extends LinearLayoutManager implements ILayoutManager {

    public XLinearLayoutManager(Context context) {
        super(context);
    }

    public XLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public XLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getFirstVisibleItemPosition() {
        return this.findFirstVisibleItemPosition();
    }

    @Override
    public int getLastVisibleItemPosition() {
        return this.findLastVisibleItemPosition();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public void setRecyclerAdapter(BaseRecyclerAdapter adapter) {

    }

    @Override
    public boolean isScrollToFooter(int itemCount) {
        int position = findLastVisibleItemPosition();
        return position == itemCount - 1;
    }
}
