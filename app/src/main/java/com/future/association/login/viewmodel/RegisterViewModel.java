package com.future.association.login.viewmodel;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.future.association.R;
import com.future.association.databinding.ActivityRegisterBinding;
import com.future.association.login.MyToast;
import com.future.association.login.RegisterActivity;
import com.future.association.login.RegisterSuccessActivity;
import com.future.association.login.UserApi;
import com.future.association.login.WebActivity;
import com.future.association.login.bean.CityResponse;
import com.future.association.login.bean.JsonBean;
import com.future.association.login.bean.VerifyResponse;
import com.future.association.login.util.CommonUtil;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.IdcardUtil;
import com.future.baselib.utils.PatternUtils;
import com.future.baselib.utils.ToastUtils;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.trello.rxlifecycle2.android.ActivityEvent;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.future.association.login.util.CommonUtil.mobilePattern;
import static com.future.association.login.util.CommonUtil.passwordPattern;
import static com.future.association.login.util.CommonUtil.verifyPattern;

/**
 * Created by Mwh on 2017/7/4.
 */

public class RegisterViewModel {
    UserApi userApi;
    private OptionsPickerView pvCustomOptions;
    private ArrayList<String> classifyList;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private boolean isParserOver = false;
    private RegisterActivity activity;
    private ActivityRegisterBinding binding;
    private Dialog errorDialog;
    public ObservableField<String> phoneNumber = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> smsCode = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> location = new ObservableField<>();
    public ObservableField<String> idcard = new ObservableField<>();
    public ObservableField<String> education = new ObservableField<>();
    public String userType = "1";
    public ObservableBoolean clearPhonenumberFlag = new ObservableBoolean(false);

    public RegisterViewModel(RegisterActivity activity, ActivityRegisterBinding binding) {
        this.activity = activity;
        this.binding = binding;
        userApi = new UserApi();
    }

    //region linstener
    public void initLinstener() {
        binding.userType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.xfz) {
                    userType = "1";
//                    binding.cardLayout.setVisibility(View.VISIBLE);
                } else {
                    userType = "2";
//                    binding.cardLayout.setVisibility(View.GONE);
                }
            }
        });
        RxTextView
                .textChangeEvents(binding.registerPhonenumber)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) o;
                        String inputNumber = textViewTextChangeEvent.text().toString();
                        clearPhonenumberFlag.set(!TextUtils.isEmpty(inputNumber));
                        if (!TextUtils.isEmpty(inputNumber) && !mobilePattern(inputNumber)) {
                            errorMessage.set("请输入正确电话号码");
                        } else if (!TextUtils.isEmpty(smsCode.get()) && !verifyPattern(smsCode.get())) {
                            errorMessage.set("请输入正确验证码");
                        } else if (!TextUtils.isEmpty(password.get()) && !passwordPattern(password.get())) {
                            errorMessage.set("请输入正确密码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });

        RxTextView
                .textChangeEvents(binding.registerPassword)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) o;
                        String password = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(password) && !passwordPattern(password)) {
                            errorMessage.set("请输入正确密码");
                        } else if (!TextUtils.isEmpty(phoneNumber.get()) && !mobilePattern(phoneNumber.get())) {
                            errorMessage.set("请输入正确电话号码");
                        } else if (!TextUtils.isEmpty(smsCode.get()) && !verifyPattern(smsCode.get())) {
                            errorMessage.set("请输入正确验证码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });

        RxTextView
                .textChangeEvents(binding.registerVerifyCode)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) o;
                        String code = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(code) && !verifyPattern(code)) {
                            errorMessage.set("请输入正确验证码");
                        } else if (!TextUtils.isEmpty(phoneNumber.get()) && !mobilePattern(phoneNumber.get())) {
                            errorMessage.set("请输入正确电话号码");
                        } else if (!TextUtils.isEmpty(password.get()) && !passwordPattern(password.get())) {
                            errorMessage.set("请输入正确密码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });

        RxView
                .clicks(binding.registerSendVerifyCode)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.mobilePattern(activity.toast, phoneNumber.get())) {
                            CommonUtil.getVerify(binding.registerSendVerifyCode, activity);
                            HttpRequest request = userApi
                                    .getRegisterVerifyCode(activity, phoneNumber.get())
                                    .setListener(new HttpRequest.OnNetworkListener<VerifyResponse>() {
                                        @Override
                                        public void onSuccess(VerifyResponse response) {//请求成功回调
                                            MyToast.makeText(activity, "" + response.info, Toast.LENGTH_SHORT, 50).show();
                                        }

                                        @Override
                                        public void onFail(String message) {
                                            //请求失败回调
                                            MyToast.makeText(activity, message, Toast.LENGTH_SHORT, 50).show();
                                            //初始化
                                            CommonUtil.cancleOi(activity, binding.registerSendVerifyCode);
                                        }
                                    });
                            request.start(new VerifyResponse());
                        }

                    }
                });
        RxView
                .clicks(binding.registerClearPhonenumber)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        phoneNumber.set("");
                    }
                });
//        RxView
//                .clicks(binding.registerNext)
//                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
//                .throttleFirst(1, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(@NonNull Object o) throws Exception {
//                        if (PatternUtils.mobilePattern(activity.toast, phoneNumber.get())
//                                && PatternUtils.passwordPattern(activity.toast, password.get())
//                                && CommonUtil.verifyPattern(activity.toast, smsCode.get())) {
//                            Intent intent = new Intent(activity, PerfectInformationActivity.class);
//                            //phoneNumber  code  password
//                            intent.putExtra("phoneNumber", phoneNumber.get());
//                            intent.putExtra("code", smsCode.get());
//                            intent.putExtra("password", password.get());
//                            activity.startActivity(intent);
//                        }
//                    }
//                });

        RxView
                .clicks(binding.agreementDetail)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        HttpRequest request = userApi.userAgreement(activity).setListener(new HttpRequest.OnNetworkListener() {
                            @Override
                            public void onSuccess(BaseResponse response) {
                                Intent intent = new Intent(activity, WebActivity.class);
                                String url = response.info;
                                if (!TextUtils.isEmpty(url)) {
                                    intent.putExtra("url", url);
                                    activity.startActivity(intent);
                                } else {
                                    activity.toast.show("url获取失败");
                                }
                            }

                            @Override
                            public void onFail(String message) {
                                activity.toast.show("" + message);
                            }
                        });
                        request.start(new VerifyResponse());
                    }

                });
        RxView
                .clicks(binding.informationEducation)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
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
                        activity.hideSoftInputFromWindow(binding.informationEducation);
                        showEducationPicker();
                    }
                });
        RxView
                .clicks(binding.informationLocation)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        initCitys();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        activity.hideSoftInputFromWindow(binding.informationLocation);
                        if (options1Items.size() > 0) {
                            showLocationPicker();
                        } else if (options1Items.size() == 0 && !isParserOver) {
                            ToastUtils.shortToast(activity, "请等待数据解析完成，稍后重试");
                        } else if (options1Items.size() == 0 && isParserOver) {
                            ToastUtils.shortToast(activity, "地址数据初始化失败,请等待重新解析");
                            initCitys();
                        }
                    }
                });
        RxView
                .clicks(binding.infomationCommit)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        //执行注册，注册成功之后直接跳转到注册页面
                        //执行null检测
//                        if (!TextUtil.isEmpty(userName.get())
//                                && !TextUtil.isEmpty(idcard.get())
//                                && !TextUtil.isEmpty(location.get())
//                                && !TextUtil.isEmpty(education.get())
//                                && !TextUtil.isEmpty(smsCode.get())) {
//                            activity.showLoadingDialog();
//                        }
//                        表单验证
                        boolean verify = verify();
                        if (verify) {
                            activity.showLoadingDialog();
                        } else {
                            return;
                        }
                        //执行注册
                        String idCard = idcard.get();
                        HttpRequest registerRequest = userApi
                                .register(activity, phoneNumber.get(), smsCode.get(), password.get(), userName.get(), location.get(), IdcardUtil.getGenderByIdCard(idCard),
                                        education.get(), String.valueOf(IdcardUtil.getAgeByIdCard(idCard)),userType,idCard)
                                .setListener(new HttpRequest.OnNetworkListener() {
                                    @Override
                                    public void onSuccess(BaseResponse response) {
                                        CommonUtil.startActivity(activity, RegisterSuccessActivity.class);
                                        activity.dissmissLoadingDialog();
                                    }

                                    @Override
                                    public void onFail(String message) {
                                        activity.toast.show("" + message);
                                        activity.dissmissLoadingDialog();
                                    }
                                });
                        registerRequest.start(new VerifyResponse());
                    }
                });

    }

    /*
     * 表单验证
     * */
    private boolean verify() {
        if (TextUtils.isEmpty(userName.get())) {
            activity.toast.show("姓名不能为空");
            return false;
        } else if (TextUtils.isEmpty(idcard.get())) {
            activity.toast.show("身份证号不能为空");
            return false;
        } else if (userType.equals("1") && !IdcardUtil.isValidatedAllIdcard(idcard.get())) {
            activity.toast.show("请输入正确的身份证号");
            return false;
        } else if (TextUtils.isEmpty(location.get())) {
            activity.toast.show("请选择地区");
            return false;
        } else if (TextUtils.isEmpty(education.get())) {
            activity.toast.show("请选择学历");
            return false;
        } else if (TextUtils.isEmpty(phoneNumber.get())) {
            activity.toast.show("请输入手机号码");
            return false;
        } else if (TextUtils.isEmpty(smsCode.get())) {
            activity.toast.show("请输入验证码");
            return false;
        } else if (TextUtils.isEmpty(password.get())) {
            activity.toast.show("请输入密码");
            return false;
        }else if (!binding.agreementCb.isChecked()){
            activity.toast.show("请勾选注册协议");
            return false;
        }
        return true;
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
        classifyList.add("专科");
        classifyList.add("本科");
        classifyList.add("硕士研究生");
        classifyList.add("博士研究生");
        initEducationPicker();
    }

    public void showEducationPicker() {
        if (pvCustomOptions != null) {
            pvCustomOptions.show();
        }
    }

    //region location picker
    public void showLocationPicker() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(activity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + "," +
                        options2Items.get(options1).get(options2) + "," +
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

    public void initJsonData(String JsonData) {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
//        String JsonData = new GetJsonDataUtil().getJson(activity, "province.json");//获取assets目录下的json文件数据

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
        isParserOver = true;
    }

    public void initCitys() {
        isParserOver = false;
        HttpRequest cityRequest = userApi.getCitys(activity).setListener(new HttpRequest.OnNetworkListener<CityResponse>() {
            @Override
            public void onSuccess(CityResponse cityResponse) {
                initJsonData(cityResponse.jsonData);
            }

            @Override
            public void onFail(String message) {
                ToastUtils.shortToast(activity, "" + message);
                initJsonData("");
//                isParserOver = true;
            }

        });
        cityRequest.start(new CityResponse());
    }

    private ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                try {
                    JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                    detail.add(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
//region get set method

    public ObservableField<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(ObservableField<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
    }

    public ObservableField<String> getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(ObservableField<String> smsCode) {
        this.smsCode = smsCode;
    }

    public ObservableField<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ObservableField<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ObservableBoolean getClearPhonenumberFlag() {
        return clearPhonenumberFlag;
    }

    public void setClearPhonenumberFlag(ObservableBoolean clearPhonenumberFlag) {
        this.clearPhonenumberFlag = clearPhonenumberFlag;
    }

    //endregion


}
