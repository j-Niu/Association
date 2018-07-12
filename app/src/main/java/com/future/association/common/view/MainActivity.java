package com.future.association.common.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.community.CommunitNewFragmnet;
import com.future.association.news.entity.CheckResponse;
import com.future.association.news.ui.fragment.NewsFragment;
import com.future.association.personal.ui.fragment.PersonalFragment;
import com.future.association.questionnaire.QuestionnaireFragment;
import com.future.association.supervice.SuperviceFragment;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.adapter.FragmentAdapter;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.JLog;
import com.future.baselib.utils.StatusUtils;
import com.future.baselib.view.IosAlertDialog;
import com.future.baselib.view.NoScrollViewPager;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.rg_bottom)
    RadioGroup rgBottom;
    @BindView(R.id.rb_zx)
    RadioButton rbZx;
    @BindView(R.id.rb_wj)
    RadioButton rbWj;
    @BindView(R.id.rb_jd)
    RadioButton rbJd;
    @BindView(R.id.rb_sq)
    RadioButton rbSq;
    @BindView(R.id.rb_wd)
    RadioButton rbWd;

    private static final String APK_PATH = Environment.getExternalStorageDirectory() + "/Association" + File.separator;

    private List<Fragment> fragmentList;
    private FragmentAdapter adapter;
    private boolean islogin;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
         islogin = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("islogin", false);
        MyApp.getApp().registerObserver(0x898, new MyApp.ObserverListener() {
            @Override
            public void notifyChange(Bundle bundle, Object object) {
                finish();
            }
        });

        System.out.println("token=="+MyApp.getUserToken());
        setAlias(MyApp.getUserToken());
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragment());
        fragmentList.add(new QuestionnaireFragment());
        fragmentList.add(new SuperviceFragment());
//        fragmentList.add(new CommunityFragment());
        fragmentList.add(new CommunitNewFragmnet());
        fragmentList.add(PersonalFragment.newInstance(4));

        adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        rgBottom.setOnCheckedChangeListener(this);
        ((RadioButton) rgBottom.getChildAt(0)).setChecked(true);
    }

    @Override
    protected void initLogic() {
        checkVersion();
    }

    @SuppressWarnings("unchecked")
    private void checkVersion() {
        if (isWifi() && islogin) {
            new HttpRequest<CheckResponse>()
                    .with(this)
                    .addParam("apiCode","_app_update_001")
                    .addParam("userToken",MyApp.getUserToken())
                    .addParam("platform","101")
                    .setListener(new HttpRequest.OnNetworkListener<CheckResponse>() {
                        @Override
                        public void onSuccess(final CheckResponse response) {
                            int versionCode = getVersionCode();
                            if (response.versionCode != 0 && versionCode != 0 && versionCode != response.versionCode) {
                                //弹框下载
                                new IosAlertDialog(MainActivity.this).builder().setTitle("提示").setMsg("已有新版本，是否更新？")
                                        .setNegativeButton("取消",null)
                                        .setPositiveButton("确定", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                toast("开始后台下载,地址："+APK_PATH);
                                                FileDownloader.setup(MainActivity.this);
                                                FileDownloader
                                                        .getImpl()
                                                        .create(response.apk)
                                                        .setPath(APK_PATH + response.apk.substring(response.apk.lastIndexOf(File.separator)))
                                                        .setListener(new FileDownloadLargeFileListener() {
                                                            @Override
                                                            protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {

                                                            }

                                                            @Override
                                                            protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                                                                JLog.e("progress","当前:"+soFarBytes+"    总共："+totalBytes);
                                                            }

                                                            @Override
                                                            protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {

                                                            }

                                                            @Override
                                                            protected void completed(BaseDownloadTask task) {
                                                                toast("下载完成");
                                                                Intent install = new Intent();
                                                                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);install.setAction(android.content.Intent.ACTION_VIEW);
//                                                                install.setDataAndType(Uri.fromFile(new File(APK_PATH)),"application/vnd.android.package-archive");
                                                                install.setDataAndType(FileProvider.getUriForFile(MainActivity.this,getPackageName()+".fileprovider",new File(APK_PATH)),"application/vnd.android.package-archive");
                                                                startActivity(install);
                                                            }

                                                            @Override
                                                            protected void error(BaseDownloadTask task, Throwable e) {
                                                                JLog.e("DownloadError",e.getMessage());
                                                            }

                                                            @Override
                                                            protected void warn(BaseDownloadTask task) {

                                                            }
                                                        }).start();
                                            }
                                        }).show();
                            }
                        }

                        @Override
                        public void onFail(String message) {
                        }
                    }).start(new CheckResponse());
        }
    }

    private boolean isWifi() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo.isConnected();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_zx:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.rb_wj:
                if (islogin) {
                    viewPager.setCurrentItem(1, false);
                }else {
                    toast("游客模式禁止访问");
                    rbWj.setChecked(false);
                }
                break;
            case R.id.rb_jd:
                if (islogin) {
                    viewPager.setCurrentItem(2, false);
                }else {
                    toast("游客模式禁止访问");
                    rbJd.setChecked(false);
                }
                break;
            case R.id.rb_sq:
                if (islogin) {
                    viewPager.setCurrentItem(3, false);
                }else {
                    toast("游客模式禁止访问");
                    rbSq.setChecked(false);
                }
                break;
            case R.id.rb_wd:
                if (islogin) {
                    viewPager.setCurrentItem(4, false);
                }else {
                    toast("游客模式禁止访问");
                    rbWd.setChecked(false);
                }
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((RadioButton) rgBottom.getChildAt(position)).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public int getVersionCode(){
        try {
            PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    public void setAlias(String alias){
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @SuppressWarnings("deprecation")
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(MainActivity.this,
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
            }
        }
    };

}
