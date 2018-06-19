package com.inesv.library;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.inesv.library.util.GlideUtil;
import com.inesv.library.util.HttpUtil;
import com.inesv.library.util.JsonUtil;
import com.inesv.library.util.LogUtil;
import com.inesv.library.util.ShapeUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> map = new HashMap<>();
        HttpUtil.getDataFromServer(map, "http://www.baidu.com", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonUtil.json2Bean(response.body().toString(),Call.class);
                //LogUtil.e("MainActivity","response:"+response.body().string());
            }
        });
        TextView tv = findViewById(R.id.tv);
        //4是左下角  3右下角  2 右上角  1 左上角
        float[] r = {5, 5, 5, 0};
        tv.setBackground(ShapeUtil.commonShape(this, r, -1, R.color.colorAccent, -1));
        ImageView iv = findViewById(R.id.iv);
        GlideUtil.loadCircleImage(this, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529547806&di=be9375221f76750b813a98af8ad074ca&imgtype=jpg&er=1&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Faec379310a55b319905cccbc46a98226cefc1755.jpg", iv);
    }
}
