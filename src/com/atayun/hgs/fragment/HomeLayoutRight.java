package com.atayun.hgs.fragment;

import com.atayun.hgs.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeLayoutRight extends Fragment {
    
    private View layout = null;
    private ImageView ModelImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.layout = inflater.inflate(R.layout.home_main_right, null);
        
        
        ModelImage = (ImageView)this.layout.findViewById(R.id.model_image);
        //设置模拟图
        ModelImage.setImageResource(R.drawable.ic_launcher);        
        return this.layout;
    }
    
}
