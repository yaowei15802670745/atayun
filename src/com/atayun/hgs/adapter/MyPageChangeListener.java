package com.atayun.hgs.adapter;

import java.util.List;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;

import com.atayun.hgs.activity.HomePage;
import com.atayun.hgs.activity.R;
/**
 * 轮播图片手动滑动监听
 */
public class MyPageChangeListener  implements OnPageChangeListener {
	private HomePage homePage;
	private List<ImageView> dotViewList;
	private int currentItem;
	boolean isAutoPlay = false;
	public MyPageChangeListener(HomePage homePage, int currentItem,List<ImageView> dotViewList){
		this.homePage=homePage;
		this.currentItem=currentItem;
		this.dotViewList=dotViewList;
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {

		case 1:// 手势滑动，空闲中
			isAutoPlay = false;
			break;

		case 2:// 界面切换中
			isAutoPlay = true;
			break;

		case 0:// 滑动结束，即切换完毕或者加载完毕
			break;
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageSelected(int pos) {
		// 这里面动态改变小圆点的被背景，来实现效果
		currentItem = pos;
		homePage.setCurrentItem(pos);
		for (int i = 0; i < dotViewList.size(); i++) {
			if (i == pos) {
				((View) dotViewList.get(pos))
						.setBackgroundResource(R.drawable.point_pressed);
			} else {
				((View) dotViewList.get(i))
						.setBackgroundResource(R.drawable.point_unpressed);
			}
		}
	}
}
