package exception;

import com.lv.girl.enums.ResultEnum;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 21:44
 * @Description
 **/
public class GirlException extends RuntimeException {
    private Integer code;


    public GirlException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

    public GirlException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
