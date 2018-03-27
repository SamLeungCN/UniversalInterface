package struct;

import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Sam on 2018-03-27.
 * E-mail:729717222@qq.com
 */
public class FunctionManager {
    private final static String TAG = "FunctionManger";

    private static FunctionManager INSTANCE;
    private static Object LOCK = new Object();

    private HashMap<String, FunctionNoParamNoResult> noParamNoResultMap;
    private HashMap<String, FunctionNoParamWithResult> noParamWithResultMap;
    private HashMap<String, FunctionWithParamNoResult> withParamNoResultMap;
    private HashMap<String, FunctionWithParamAndResult> withParamAndResultMap;

    private FunctionManager(){
        noParamNoResultMap = new HashMap<>();
        noParamWithResultMap = new HashMap<>();
        withParamNoResultMap = new HashMap<>();
        withParamAndResultMap = new HashMap<>();
    }

    public static FunctionManager getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null){
                    INSTANCE = new FunctionManager();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private void checkIfNull(Function function){
        if (function == null){
            throw  new NullPointerException("Function can not be null.");
        }

        if (function.getName() == null ){
            throw  new NullPointerException("Function name can not be null.");
        }
    }

    public FunctionManager addFunction(FunctionNoParamNoResult function){
        checkIfNull(function);
        noParamNoResultMap.put(function.getName(), function);
        return this;
    }

    public void invokeFunction(String name){
        if (name == null || !noParamNoResultMap.containsKey(name)){
            return;
        }
        noParamNoResultMap.get(name).function();
    }

    public FunctionManager addFunction(FunctionNoParamWithResult function){
        checkIfNull(function);
        noParamWithResultMap.put(function.getName(), function);
        return this;
    }

    public <Result> Result invokeFunction(String name, Class<Result> result){
        if (name == null || !noParamWithResultMap.containsKey(name)){
            return null;
        }

        return  result.cast(noParamWithResultMap.get(name).function());
    }

    public FunctionManager addFunction(FunctionWithParamNoResult function){
        checkIfNull(function);
        withParamNoResultMap.put(function.getName(), function);
        return this;
    }

    public <Param> void invokeFunction(String name, Param param){
        if (name == null || !withParamNoResultMap.containsKey(name)){
            return;
        }

        withParamNoResultMap.get(name).function(param);
    }

    public FunctionManager addFunction(FunctionWithParamAndResult function){
        checkIfNull(function);
        withParamAndResultMap.put(function.getName(), function);
        return this;
    }

    public <Param, Result> Result invokeFunction(String name,
                                                 Class<Result> result,
                                                 Param param){
        if (name == null || !withParamAndResultMap.containsKey(name)){
            return null;
        }

        return  result.cast(withParamAndResultMap.get(name).function(param));
    }


    public FunctionManager removeFunction(Function function){
        if (function == null
                || TextUtils.isEmpty(function.getName())){
            return this;
        }

        String functionName = function.getName();

        if (function instanceof FunctionNoParamNoResult){
            if (noParamNoResultMap.containsKey(functionName)){
                noParamNoResultMap.remove(functionName);
            }
        } else if (function instanceof FunctionNoParamWithResult){
            if (noParamWithResultMap.containsKey(functionName)){
                noParamWithResultMap.remove(functionName);
            }
        } else if (function instanceof FunctionWithParamNoResult){
            if (withParamNoResultMap.containsKey(functionName)){
                withParamNoResultMap.remove(functionName);
            }
        } else if (function instanceof  FunctionWithParamAndResult){
            if (withParamAndResultMap.containsKey(functionName)){
                withParamAndResultMap.remove(functionName);
            }
        }

        return this;
    }

    public FunctionManager removeAllNoParamNoResultFunctions(){
        noParamNoResultMap.clear();
        return this;
    }

    public FunctionManager removeAllNoParamWithResultFunctions(){
        noParamWithResultMap.clear();
        return this;
    }

    public FunctionManager removeAllWithParamNoResultFunctions(){
        withParamNoResultMap.clear();
        return this;
    }

    public FunctionManager removeAllWithParamAndResultFunctions(){
        withParamAndResultMap.clear();
        return this;
    }

    public FunctionManager removeAllFunctions(){
        removeAllNoParamNoResultFunctions();
        removeAllNoParamWithResultFunctions();
        removeAllWithParamNoResultFunctions();
        removeAllWithParamAndResultFunctions();
        return this;
    }
}
