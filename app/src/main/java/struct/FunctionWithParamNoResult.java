package struct;

/**
 * Created by Sam on 2018-03-27.
 * E-mail:729717222@qq.com
 */
public abstract class FunctionWithParamNoResult<Param> extends Function {

    public FunctionWithParamNoResult(String functionName) {
        super(functionName);
    }


    public abstract void function(Param param);
}
