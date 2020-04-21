package per.colin.sphtest.http;

public interface NetCallBack<T extends BaseResponse> {

    public void call(T response);

}
