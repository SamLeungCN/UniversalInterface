package com.android.sam.universal_interface;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import struct.Function;
import struct.FunctionManager;
import struct.FunctionWithParamAndResult;
import struct.FunctionWithParamNoResult;

/**
 * Created by Sam on 2018-03-27.
 * E-mail:729717222@qq.com
 */
public class Fragment2 extends Fragment {
    private TextView contentView;
    private Button btnCallActivity;

    public static final String INTERFACE_UPDATE_CONTENT = "fragment2_update_content";
    public static final String INTERFACE_GET_KEY = "fragment2_get_key";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FunctionManager.getInstance().addFunction(updateContentFunction);
        FunctionManager.getInstance().addFunction(getKeyFunction);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.layout_fragment2, null);
        contentView = view.findViewById(R.id.tv_content);
        btnCallActivity = view.findViewById(R.id.btn_show_toast_in_activity);
        btnCallActivity.setOnClickListener(clickListener);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        FunctionManager.getInstance().removeFunction(getKeyFunction);
        FunctionManager.getInstance().removeFunction(updateContentFunction);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_show_toast_in_activity:
                    FunctionManager.getInstance().invokeFunction(MainActivity.FUNC_SHOW_TOAST,
                            "Fragment2 invoke activity's method.");
                    break;
                default:break;
            }
        }
    };

    private FunctionWithParamAndResult<String, String> getKeyFunction = new FunctionWithParamAndResult<String, String>(INTERFACE_GET_KEY) {
        @Override
        public String function(String s) {
            return s + "fragment2-key-hello";
        }
    };

    private FunctionWithParamNoResult<String> updateContentFunction = new FunctionWithParamNoResult<String>(INTERFACE_UPDATE_CONTENT) {
        @Override
        public void function(String s) {
            contentView.setText(s);
        }
    };
}
