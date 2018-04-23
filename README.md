# inesvlibrary
宏强科技
   1.使用方法
    gradle  compile 'com.inesv.library:inesvlibrary:1.0.1' 
   2.changebutton属性
     
        <com.inesv.ce.view.ChangeButton
            android:id="@+id/login_login_cb"
            style="@style/base_btn_sty"
            android:layout_marginLeft="@dimen/common_dimen_15"
            android:layout_marginRight="@dimen/common_dimen_15"
            android:layout_marginTop="@dimen/common_dimen_40"
            android:text="@string/common_login"
            lumia:fillColor="@color/main_color"
            lumia:strokeWidth="1"
            lumia:textColor="@color/base_white"
            lumia:unstrokeColor="@color/base_text_light_gray"
            lumia:unstrokeWidth="1"
            lumia:untextColor="@color/base_text_light_gray" />
   3.countdownview属性        
      <com.inesv.ce.view.CountDownView
                android:id="@+id/register_code_cv"
                android:layout_width="@dimen/common_dimen_90"
                android:layout_height="@dimen/common_dimen_30"
                android:layout_alignParentRight="true"
                android:layout_centerVertical="true"
                android:gravity="center"
                android:text="@string/register_getcode"
                android:textColor="@color/base_text_light_gray"
                android:textSize="@dimen/common_size_12"
                lumia:countdownNumber="60"
                lumia:fillColor="@color/main_color"
                lumia:roundRadius="3"
                lumia:textColor="@color/base_white"
                lumia:unfillColor="@color/base_white"
                lumia:unstrokeColor="@color/base_text_light_gray"
                lumia:unstrokeWidth="1"
                lumia:untextColor="@color/base_text_light_gray" />
     4.工具类
       md5、shapeutil、toastutil、textutil
