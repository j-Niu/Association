package com.future.association.personal;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.future.association.R;
import com.future.association.common.Contants;
import com.future.association.community.utils.TextUtil;
import com.future.association.personal.gallerypick.GlideImageLoader;
import com.future.baselib.utils.ToastUtils;
import com.future.baselib.view.ActionSheetDialog;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/j-Niu/Association
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends MyBaseFragment {
    private final String TAG = "123";
    private CircleImageView header;
    private ActionSheetDialog sheetDialog;
    private LinearLayout myLevel;
    private RelativeLayout myJianDu, myHuiYing, myTieZi, myWenJuan, myXiaoXi, myTongZhi, myMore;

    //照片
    public List<String> picPath = new ArrayList<>();
    private GalleryConfig galleryConfig;
    private IHandlerCallBack iHandlerCallBack;
    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;
    private final int PERMISSIONS_REQUEST_WRITE_CONTACTS = 9;
    private static final int CHANGE_HEADER = 0x561;

    public PersonalFragment() {
        // Required empty public constructor
    }

    public static PersonalFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(APR_POSITION, position);
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        setTitle("我的");
        header = (CircleImageView) view.findViewById(R.id.cirimgMy);
        myLevel = (LinearLayout) view.findViewById(R.id.linearMy2);
        myJianDu = (RelativeLayout) view.findViewById(R.id.myJianDu);
        myHuiYing = (RelativeLayout) view.findViewById(R.id.myHuiYing);
        myTieZi = (RelativeLayout) view.findViewById(R.id.myTieZi);
        myWenJuan = (RelativeLayout) view.findViewById(R.id.myWenJuan);
        myXiaoXi = (RelativeLayout) view.findViewById(R.id.myXiaoXi);
        myTongZhi = (RelativeLayout) view.findViewById(R.id.myTongZhi);
        myMore = (RelativeLayout) view.findViewById(R.id.myMore);

        initGalleyCallBack();
        initGalley();
        initClick();
    }

    private void initClick() {
        myLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyLevelActivity.class);
            }
        });
        myJianDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyJianDuActivity.class);
            }
        });
        myHuiYing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyResponseActivity.class);
            }
        });
        myTieZi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyTieziActivity.class);
            }
        });
        myWenJuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyWenJuanActivity.class);
            }
        });
        myXiaoXi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyNoticeActivity.class);
            }
        });
        myTongZhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyTongZhi.class);
            }
        });
        myMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyMoreActivity.class);
            }
        });
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), LoginActivity.class));
                sheetDialog = new ActionSheetDialog(getActivity());
                sheetDialog.builder().setTitle("选取头像")
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
//                                Toast.makeText(getActivity(), "拍照" + which, Toast.LENGTH_SHORT).show();
                                //如果已经设置好了 GalleryConfig 可以使用单项参数修改的方法来开启相机。
//                                galleryConfig.getBuilder().isOpenCamera(true).build();// 直接开启相机的标识位
//                                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(getActivity());
                                //如果已配置好  galleryConfig 不想修改：
                                GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(getActivity());
                            }
                        })
                        .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
//                                Toast.makeText(getActivity(), "相册" + which, Toast.LENGTH_SHORT).show();
                                galleryConfig.getBuilder().maxSize(1).build();
                                galleryConfig.getBuilder().isOpenCamera(false).build();
                                initPermissions();
                            }
                        });
                sheetDialog.show();
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String img = "";
            Bitmap bitmap = null;
            if (msg.what == CHANGE_HEADER) {
                img = (String) msg.obj;
                bitmap = BitmapUtils.scaleBitmap(img);
                header.setImageBitmap(bitmap);
//                Glide.with(mContext)
//                        .load(Uri.parse(img))
//                        .centerCrop()
//                        .into(header);
            }
        }
    };

    //==========================================照片Start================================================//
    public void initGalley() {
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.future.association.fileprovider")   // provider(必填)
//                .pathList(picPath)         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 1)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
//                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(false)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(false, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(false)                     // 是否显示相机按钮  默认：false
                .filePath(Contants.APP_IMG_PATH)        // 图片存放路径   "/Vitek/jcoa/pic"
                .imageLoader(new GlideImageLoader())    // 设置图片加载框架
                .build();
    }

    public void initGalleyCallBack() {
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i("123", "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                Log.i("123", "onSuccess: 返回数据");
                for (String path : photoList) {
                    Log.i("123", "onSuccess  路径    " + path);
                }
                if (!TextUtil.isEmpty(photoList.get(0))) {
                    Message msg = handler.obtainMessage(CHANGE_HEADER);
                    msg.obj = photoList.get(0);
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onCancel() {
                Log.i("123", "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                Log.i("123", "onFinish: 结束");
            }

            @Override
            public void onError() {
                Log.i("123", "onError: 出错");
            }
        };
    }

    // 授权管理
    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "需要授权 ");
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                Log.i(TAG, "拒绝过了");
                ToastUtils.shortToast(getActivity(), "请在 设置-应用管理 中开启此应用的储存授权。");
            } else {
//                Log.i(TAG, "进行授权");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_WRITE_CONTACTS);
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);

            }
        } else {
//            Log.i(TAG, "不需要授权 ");
            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(getActivity());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_WRITE_CONTACTS || requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.i(TAG, "同意授权");
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(getActivity());
            } else {
//                Log.i(TAG, "拒绝授权");
            }
        }
    }
    //==========================================照片End================================================//
}