package per.colin.sphtest.presenter;

import java.util.List;

import per.colin.sphtest.db.model.DataEntity;
import per.colin.sphtest.repo.WebRepository;
import per.colin.sphtest.utils.ThreadPoolUtil;
import per.colin.sphtest.viewmodel.MainViewModel;

public class MainPresenter extends BasePresenter {

    private MainViewModel viewModel;
    private WebRepository repository;

    public MainPresenter(MainViewModel viewModel) {
        this.viewModel = viewModel;
        this.repository = WebRepository.getInstance();
    }

    public void queryData(int limit, int offset) {
        ThreadPoolUtil.execute( () -> {
            repository.queryData(limit, offset, new PreCallback<List<DataEntity>>() {
                @Override
                public void call(List<DataEntity> dataEntities) {
                    viewModel.data.postValue(dataEntities);
                }
            });

        });

    }
}
