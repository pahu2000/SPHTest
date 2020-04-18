package per.colin.sphtest.act;

import android.os.Bundle;

import androidx.annotation.Nullable;

import per.colin.sphtest.R;

/**
 * @author pahu2000
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }
}
