package struct;

/**
 * Created by Sam on 2018-03-27.
 * E-mail:729717222@qq.com
 */
public abstract class FunctionWithParamAndResult<Param, Result> extends Function {

    public FunctionWithParamAndResult(String functionName) {
        super(functionName);
    }

    public abstract Result function(Param param);
}
