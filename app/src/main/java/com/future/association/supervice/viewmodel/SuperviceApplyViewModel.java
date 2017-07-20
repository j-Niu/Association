package com.future.association.supervice.viewmodel;

import android.content.Intent;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.future.association.common.MyApp;
import com.future.association.community.utils.TextUtil;
import com.future.association.databinding.ActivitySuperviceApplyBinding;
import com.future.association.login.bean.GetJsonDataUtil;
import com.future.association.login.bean.JsonBean;
import com.future.association.supervice.FullyGridLayoutManager;
import com.future.association.supervice.SupericeApi;
import com.future.association.supervice.adapter.GridImageAdapter;
import com.future.association.supervice.model.SupericeDetail;
import com.future.association.supervice.view.SuperviceApplyActivity;
import com.future.baselib.utils.CommonUtils;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.ToastUtils;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DebugUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.app.Activity.RESULT_OK;

/**
 * Created by rain on 2017/7/16.
 */

public class SuperviceApplyViewModel {
    private final ActivitySuperviceApplyBinding mBindIng;
    private final SuperviceApplyActivity activity;
    private final String type;
    public ObservableField<SupericeDetail> supericeDetail = new ObservableField<>();
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private final SupericeDetail supericeDetailBean = new SupericeDetail();
    private GridImageAdapter adapter;
    private int maxSelectNum = 5;


    public SuperviceApplyViewModel(SuperviceApplyActivity activity, ActivitySuperviceApplyBinding binding, String type) {
        this.activity = activity;
        this.mBindIng = binding;
        this.type = TextUtil.isEmpty(type)?"":type;
        initView();
        initData();
    }

    private void initView() {
        mBindIng.imgRv.setLayoutManager(new FullyGridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false));
        adapter = new GridImageAdapter(activity, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mBindIng.imgRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(activity).externalPicturePreview(position, selectList);
                            break;
                    }
                }
            }
        });
        RxView.clicks(mBindIng.submitBtn)
                .throttleFirst(1, java.util.concurrent.TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (canCommit()) {
                            SupericeDetail supericeDetail = SuperviceApplyViewModel.this.supericeDetail.get();
                            StringBuffer buffer = new StringBuffer();
                            for (int i = 0; i < selectList.size(); i++) {
                                if (i == 0) {
                                    String img = CommonUtils.getImg(selectList.get(0).getCompressPath());
                                    buffer.append(img);
                                } else {
                                    String img = CommonUtils.getImg(selectList.get(i).getCompressPath());
                                    buffer.append("," + img);
                                }
                            }
                            SupericeApi.getInstance()
                                    .publishSuperice(activity, MyApp.getUserToken(),
                                            supericeDetail.getType(), supericeDetail.getAddress(),
                                            supericeDetail.getTitle(), supericeDetail.getReason(),
                                            buffer.toString())
                                    .setListener(new HttpRequest.OnNetworkListener<SupericeDetail>() {
                                        @Override
                                        public void onSuccess(SupericeDetail response) {
                                            ToastUtils.shortToast(activity, "发布成功");
                                            activity.finish();
                                        }

                                        @Override
                                        public void onFail(String message) {

                                        }
                                    }).start(new SupericeDetail());
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

    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            PictureSelector.create(activity)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .previewImage(true)// 是否可预览图片
                    .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
//                    .enableCrop(true)// 是否裁剪
                    .compress(true)// 是否压缩
                    .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                    .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                    .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                    .isGif(false)// 是否显示gif图片
//                    .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
//                    .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                    .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//                    .openClickSound(cb_voice.isChecked())// 是否开启点击声音
                    .selectionMedia(selectList)// 是否传入已选图片
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };

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
        if (selectList == null || selectList.size() == 0) {
            ToastUtils.shortToast(activity, "请至少上传一张图片");
        }

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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    DebugUtil.i("onActivityResult:", "onActivityResult:" + selectList.toString());
                    break;
            }
        }
    }
}
