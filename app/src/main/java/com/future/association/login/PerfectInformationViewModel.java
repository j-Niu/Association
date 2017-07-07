package com.future.association.login;

import android.app.Activity;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bigkoo.pickerview.OptionsPickerView;
import com.future.association.R;
import com.future.association.databinding.ActivityPerfectInformationBinding;
import com.future.association.databinding.DialogSelectSexBinding;
import com.future.association.login.bean.GetJsonDataUtil;
import com.future.association.login.bean.JsonBean;
import com.future.association.login.util.CommonUtil;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/7/4.
 */

public class PerfectInformationViewModel {
    private ActivityPerfectInformationBinding binding;
    private Activity activity;
    private OptionsPickerView pvCustomOptions;
    private ArrayList<String> classifyList;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private DialogSelectSexBinding sexBinding;
    private Dialog sexDialog;

    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> location = new ObservableField<>();
    public ObservableField<String> education = new ObservableField<>();
    public ObservableBoolean sex = new ObservableBoolean(true);
    public ObservableField<String> age = new ObservableField<>();

    public PerfectInformationViewModel(Activity activity, ActivityPerfectInformationBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }


    //region sex dialog
    public void showSexDialog() {
        sexBinding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.dialog_select_sex, null, false);
        sexDialog = new AlertDialog.Builder(activity).setView(sexBinding.getRoot()).create();
        sexDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  // 有白色背景，加这句代码
//        errorDialog.setCancelable(false);
        sexDialog.setCanceledOnTouchOutside(false);
        sexDialog.show();
        Window window = sexDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        //设置监听事件
        RxView
                .clicks(sexBinding.sexMan)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (sexDialog != null) {
                            sexDialog.dismiss();
                            sex.set(true);
                        }
                    }
                });

        RxView
                .clicks(sexBinding.sexWoman)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (sexDialog != null) {
                            sexDialog.dismiss();
                            sex.set(false);
                        }
                    }
                });
    }
    //endregion

    //region location picker
    public void showLocationPicker() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(activity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                binding.informationLocation.setText(tx);
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
    //endregion

    //region education picker
    private void initEducationPicker() {
        //条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerView.Builder(activity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = classifyList.get(options1);
                binding.informationEducation.setText(tx);
            }
        })
                .setTitleText("学历")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(ContextCompat.getColor(activity, R.color.color_dcdcdc))//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(ContextCompat.getColor(activity, R.color.color_ffffff))
                .setTitleBgColor(ContextCompat.getColor(activity, R.color.color_f8f8f8))
                .setTitleColor(ContextCompat.getColor(activity, R.color.color_000000))
                .setCancelColor(ContextCompat.getColor(activity, R.color.color_007aff))
                .setSubmitColor(ContextCompat.getColor(activity, R.color.color_007aff))
                .setTextColorCenter(ContextCompat.getColor(activity, R.color.color_000000))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(ContextCompat.getColor(activity, R.color.color_00000000)) //设置外部遮罩颜色
                .build();

        pvCustomOptions.setPicker(classifyList);//添加数据

    }

    public void initEducation() {
        classifyList = new ArrayList<>();
        classifyList.add("小学");
        classifyList.add("初中");
        classifyList.add("中专/高中");
        classifyList.add("专科/本科");
        classifyList.add("硕士研究生");
        classifyList.add("博士研究生");
        initEducationPicker();
    }

    public void showEducationPicker() {
        if (pvCustomOptions != null) {
            pvCustomOptions.show();
        }
    }
    //endregion

    //region linstener
    public void initLinstener() {
        RxView
                .clicks(binding.informationEducation)
                .throttleFirst(1, TimeUnit.SECONDS)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        initEducation();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        showEducationPicker();
                    }
                });

        RxView
                .clicks(binding.informationLocation)
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

        RxView
                .clicks(binding.informationSex)
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        showSexDialog();
                    }
                });

        RxView
                .clicks(binding.infomationCommit)
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        //执行注册，注册成功之后直接跳转到注册页面
                        //执行null检测
                        CommonUtil.startActivity(activity, RegisterSuccessActivity.class);
                        Log.e("ttttt", "data---->" + "usernam:" + userName.get() + "--sex:" + sex.get() + "--location:" + location.get() + "--education" + education.get() + "--age:" + age.get());
                    }
                });
    }

    //endregion

    //region get set method
    public ObservableField<String> getUserName() {
        return userName;
    }

    public void setUserName(ObservableField<String> userName) {
        this.userName = userName;
    }

    public ObservableField<String> getLocation() {
        return location;
    }

    public void setLocation(ObservableField<String> location) {
        this.location = location;
    }

    public ObservableField<String> getEducation() {
        return education;
    }

    public void setEducation(ObservableField<String> education) {
        this.education = education;
    }

    public ObservableBoolean getSex() {
        return sex;
    }

    public void setSex(ObservableBoolean sex) {
        this.sex = sex;
    }

    public ObservableField<String> getAge() {
        return age;
    }

    public void setAge(ObservableField<String> age) {
        this.age = age;
    }

//endregion

}
