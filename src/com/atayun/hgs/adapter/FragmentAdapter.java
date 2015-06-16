package com.atayun.hgs.adapter;

import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

	//����Ǵ������Fragment�����飬����MainActivity�д�����������
    private ArrayList<Fragment> fragmentArray;
    
    //�Լ����һ�����캯���MainActivity�н������Fragment����
    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArray) {
        this(fm);
        this.fragmentArray = fragmentArray;
    }
    
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    
    //�������������ǵ��л�����arg0��ҳ���ʱ����á�
    @Override
    public Fragment getItem(int arg0) {
        return this.fragmentArray.get(arg0);
    }

    @Override
    public int getCount() {
        return this.fragmentArray.size();
    }

}
