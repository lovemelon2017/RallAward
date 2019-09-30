package com.yh.rollingaward;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yh.rollingaward.wv.AbstractWheelAdapter;
import com.yh.rollingaward.wv.OnWheelScrollListener;
import com.yh.rollingaward.wv.WheelView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvStart;
    WheelView wheelView;
    EditText etTime;
    int times = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvStart = findViewById(R.id.start_tv);
        wheelView = findViewById(R.id.wheel_view);
        etTime = findViewById(R.id.start_time_et);

        initView();

        for (int i = 0; i < 200; i++) {
            Random ra = new Random();
            int num = ra.nextInt(9);
            Log.e("han", "摇奖随机数==" + num);
        }
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random ra = new Random();
                int num = ra.nextInt(9);
                Log.e("han", "摇奖随机数==" + num);

                String s = etTime.getText().toString();
                if (!TextUtils.isEmpty(s)) {
                    times = Integer.valueOf(s) * 1000;
                }
                wheelView.scroll(90 + num, times);
            }
        });

        wheelView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                int currentItem = wheel.getCurrentItem();
                Toast.makeText(MainActivity.this, "当前数字==" + (currentItem + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        wheelView.setViewAdapter(new SlotMachineAdapter());
        wheelView.setVisibleItems(1);
        wheelView.setCyclic(true);
        // wheelView.setEnabled(false);
        wheelView.setDrawShadows(true);
    }


    /**
     * 适配器
     */
    private class SlotMachineAdapter extends AbstractWheelAdapter {
        @Override
        public int getItemsCount() {
            return 9;
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view;
            if (cachedView != null) {
                view = cachedView;
            } else {
                view = View.inflate(MainActivity.this, R.layout.item_dialog_tiger_img, null);
            }
            ImageView img = (ImageView) view.findViewById(R.id.iv_dialog_home_tiger);
            switch (index) {

                case 0:
                    img.setImageResource(R.mipmap.ic_roll1);
                    break;

                case 1:
                    img.setImageResource(R.mipmap.ic_roll2);
                    break;

                case 2:
                    img.setImageResource(R.mipmap.ic_roll3);
                    break;

                case 3:
                    img.setImageResource(R.mipmap.ic_roll4);
                    break;

                case 4:
                    img.setImageResource(R.mipmap.ic_roll5);
                    break;

                case 5:
                    img.setImageResource(R.mipmap.ic_roll6);
                    break;

                case 6:
                    img.setImageResource(R.mipmap.ic_roll7);
                    break;

                case 7:
                    img.setImageResource(R.mipmap.ic_roll8);
                    break;

                case 8:
                    img.setImageResource(R.mipmap.ic_roll9);
                    break;

            }

            return view;
        }
    }
}
