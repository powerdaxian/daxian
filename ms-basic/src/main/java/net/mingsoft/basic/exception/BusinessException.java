package net.mingsoft.basic.exception;

/**
 * 
 * 业务异常处理
 * @author 铭飞开源团队-Administrator  
 * @date 2018年7月6日
 */
public class BusinessException  extends RuntimeException {

	private String bizCode,bizMsg;
    public BusinessException(String msg) {
        super(msg);
    }
    
    /**
     * 自定义异常信息
     * @param code 错误码
     * @param msg 错误信息
     */
    public BusinessException(String code,String msg) {
        super(msg);
        this.bizCode = code;
        this.bizMsg = msg;
    }
    
	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getBizMsg() {
		return bizMsg;
	}

	public void setBizMsg(String bizMsg) {
		this.bizMsg = bizMsg;
	}

    
}
