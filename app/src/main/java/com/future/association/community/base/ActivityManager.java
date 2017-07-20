package com.future.association.community.base;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

public class ActivityManager {

    private static ActivityManager mInstence;
    private List<Activity> mList = new LinkedList<Activity>();

    private ActivityManager() {
    }

    public synchronized static ActivityManager getInstance() {
        if (mInstence == null) {
            mInstence = new ActivityManager();
        }
        return mInstence;
    }

    public void addActivity(Activity activity) {
        if (mList.indexOf(activity) >= 0) {
            mList.get(mList.indexOf(activity)).finish() ;
        }
        mList.add(activity);
    }

    public void exitApplicaion() {
        int count = mList.size();
        for (int index = 0; index < count; index++) {
            mList.get(index).finish();
        }
    }
    /**
     * 退出指定Activity
     * @param clazz
     */
    public void finishActivityForClass(Class clazz){
        if(clazz != null){
            for (int i = 0; i < mList.size(); i++) {
                if(mList.get(i).getClass().equals(clazz)){
                    mList.get(i).finish() ;
                }
            }
        }else{
            throw new RuntimeException("clazz cannot be null ！") ;
        }
    }
    /**
     * 判断某个activity是否在栈里面
     * @param clazz
     */
    public boolean activityIsExist(Class clazz){
        if(clazz != null){
            for (int i = 0; i < mList.size(); i++) {
                if(mList.get(i).getClass().equals(clazz)){
                    return true ;
                }
            }
        }else{
            throw new RuntimeException("clazz cannot be null ！") ;
        }
        return false ;
    }
    /**
     * 保留主activity 关闭其他activity
     */
    public void remainMainActivityFinishOther(Class clazz){
        for (int i = 0; i < mList.size(); i++) {
            if(!mList.get(i).getClass().equals(clazz)){
                mList.get(i).finish() ;
            }
        }
    }
    public void finishForActivity(Activity activity) {
        if (mList.indexOf(activity) >= 0) {
            activity.finish();
        }
    }

}
