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
import struct.FunctionWithParamNoResult;

/**
 * Created by Sam on 2018-03-27.
 * E-mail:729717222@qq.com
 */

public class Fragment1 extends Fragment {
    private TextView contentView;
    private Button btnCallActivity;
    private Button btnCallFragment2;

    public static final String INTERFACE_UPDATE_CONTENT = "fragment1_update_content";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FunctionManager.getInstance().addFunction(updateContentFunction);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.layout_fragment1, null);
        contentView = view.findViewById(R.id.tv_content);
        btnCallActivity = view.findViewById(R.id.btn_show_toast_in_activity);
        btnCallFragment2 = view.findViewById(R.id.btn_invoke_fragment2);

        btnCallActivity.setOnClickListener(clickListener);
        btnCallFragment2.setOnClickListener(clickListener);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FunctionManager.getInstance().removeFunction(updateContentFunction);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_show_toast_in_activity:
                    FunctionManager.getInstance().invokeFunction(MainActivity.FUNC_SHOW_TOAST,
                            "Fragment1 invoke activity's method.");
                    break;

                case R.id.btn_invoke_fragment2:
                    String fragment2Key = FunctionManager.getInstance().invokeFunction(Fragment2.INTERFACE_GET_KEY,
                            String.class, "Fragment1 invoke: ");

                    FunctionManager.getInstance().invokeFunction(MainActivity.FUNC_SHOW_TOAST,
                            "Fragment1 invoke activity's method # show fragment2's key: "+fragment2Key);
                    break;

                default:break;
            }
        }
    };

    private FunctionWithParamNoResult<String> updateContentFunction = new FunctionWithParamNoResult<String>(INTERFACE_UPDATE_CONTENT) {
        @Override
        public void function(String s) {
            contentView.setText(s);
        }
    };
}
