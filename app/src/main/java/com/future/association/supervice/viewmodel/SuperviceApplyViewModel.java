package com.future.association.supervice.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.future.association.common.MyApp;
import com.future.association.community.utils.TextUtil;
import com.future.association.databinding.ActivitySuperviceApplyBinding;
import com.future.association.login.bean.GetJsonDataUtil;
import com.future.association.login.bean.JsonBean;
import com.future.association.supervice.SupericeApi;
import com.future.association.supervice.model.SupericeDetail;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.ToastUtils;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by rain on 2017/7/16.
 */

public class SuperviceApplyViewModel {
    private final ActivitySuperviceApplyBinding mBindIng;
    private final Activity activity;
    private final String type;
    public ObservableField<SupericeDetail> supericeDetail = new ObservableField<>();
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private final SupericeDetail supericeDetailBean = new SupericeDetail();

    public SuperviceApplyViewModel(Activity activity, ActivitySuperviceApplyBinding binding,String type) {
        this.activity = activity;
        this.mBindIng = binding;
        this.type = TextUtil.isEmpty(type)?"":type;
        initView();
        initData();
    }

    private void initView() {
        RxView.clicks(mBindIng.submitBtn)
                .throttleFirst(1, java.util.concurrent.TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (canCommit()) {
                            SupericeDetail supericeDetail = SuperviceApplyViewModel.this.supericeDetail.get();
                            String[] imgs = supericeDetail.getImage();
                            StringBuffer buffer = new StringBuffer();
                            //TODO 测试
                            buffer.append("http://img5.iqilu.com/c/u/2012/1016/1350352494909.jpg,http://img5.iqilu.com/c/u/2012/1016/1350352494909.jpg");
//                            for (int i = 0; i < imgs.length; i++) {
//                                if (i == 0) {
//                                    buffer.append(imgs[0]);
//                                } else {
//                                    buffer.append("," + imgs[i]);
//                                }
//                            }
                            SupericeApi.getInstance().publishSuperice(activity, MyApp.getUserToken(), supericeDetail.getType(),
                                    supericeDetail.getAddress(), supericeDetail.getTitle(), supericeDetail.getReason(), buffer.toString())
                                    .setListener(new HttpRequest.OnNetworkListener() {
                                        @Override
                                        public void onSuccess(BaseResponse response) {
                                            ToastUtils.shortToast(activity, "发布成功");
                                            activity.finish();
                                        }

                                        @Override
                                        public void onFail(String message) {

                                        }
                                    }).start(new BaseResponse() {
                                @Override
                                public void parseInfo(String content) throws JSONException {

                                }
                            });
                        }
                    }
                });

        RxView.clicks(mBindIng.address)
                .throttleFirst(1, TimeUnit.SECONDS)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        initJsonData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        showLocationPicker();
                    }
                });
    }

    private boolean canCommit() {
        SupericeDetail info = supericeDetail.get();
        if (TextUtil.isEmpty(info.getType())) {
            ToastUtils.shortToast(activity, "请选择类型");
            return false;
        }
        if (TextUtil.isEmpty(info.getAddress())) {
            ToastUtils.shortToast(activity, "请选择区域");
            return false;
        }
        if (TextUtil.isEmpty(info.getTitle())) {
            ToastUtils.shortToast(activity, "标题不能为空");
            return false;
        }
        if (TextUtil.isEmpty(info.getReason())) {
            ToastUtils.shortToast(activity, "请填写监督事由");
            return false;
        }
//        if (supericeDetail.getImage() == null || supericeDetail.getImage().length == 0) {
//            ToastUtils.shortToast(activity, "请至少上传一张图片");
//        }

        return true;
    }

    private void initData() {
        supericeDetailBean.setType(type);
        supericeDetail.set(supericeDetailBean);
        mBindIng.setSupericeDetail(supericeDetailBean);
    }

    //region location picker
    public void showLocationPicker() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(activity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                supericeDetailBean.setAddress(tx);
                mBindIng.address.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    public void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(activity, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }


    private ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}
