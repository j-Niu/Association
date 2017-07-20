package com.future.association.common;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.future.association.R;
import com.future.association.community.CommunityFragment;
import com.future.association.news.ui.fragment.NewsFragment;
import com.future.association.personal.PersonalFragment;
import com.future.association.questionnaire.QuestionnaireFragment;
import com.future.association.supervice.SuperviceFragment;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.adapter.FragmentAdapter;
import com.future.baselib.utils.StatusUtils;
import com.future.baselib.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.rg_bottom)
    RadioGroup rgBottom;

    private List<Fragment> fragmentList;
    private FragmentAdapter adapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragment());
        fragmentList.add(new QuestionnaireFragment());
        fragmentList.add(new SuperviceFragment());
        fragmentList.add(new CommunityFragment());
        fragmentList.add(PersonalFragment.newInstance(4));

        adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        rgBottom.setOnCheckedChangeListener(this);
        ((RadioButton) rgBottom.getChildAt(0)).setChecked(true);
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_zx:
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.rb_wj:
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.rb_jd:
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.rb_sq:
                viewPager.setCurrentItem(3,false);
                break;
            case R.id.rb_wd:
                viewPager.setCurrentItem(4,false);
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
}
