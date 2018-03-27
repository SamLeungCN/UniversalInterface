package struct;

/**
 * Created by Sam on 2018-03-27.
 * E-mail:729717222@qq.com
 */
public abstract class FunctionNoParamWithResult<Result> extends Function {

    public FunctionNoParamWithResult(String functionName) {
        super(functionName);
    }

    public abstract Result function();
}
