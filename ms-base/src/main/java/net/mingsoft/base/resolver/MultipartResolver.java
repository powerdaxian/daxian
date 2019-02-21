/**
 * The MIT License (MIT) * Copyright (c) 2018 铭飞科技(mingsoft.net)

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.mingsoft.base.resolver;
import javax.servlet.http.HttpServletRequest;  
import org.springframework.web.multipart.commons.CommonsMultipartResolver;  

/**
 * 
 * @ClassName:  MultipartResolver   
 * @Description:TODO(同时使用了MultipartResolver 和ServletFileUpload 的冲突解决)   
 * @author: 铭飞开发团队
 * @date:   2018年3月19日 下午3:46:38   
 *     
 * @Copyright: 2018 www.mingsoft.net Inc. All rights reserved.
 */
public class MultipartResolver extends CommonsMultipartResolver {  
    private String excludeUrls;     
    private String[] excludeUrlArray;  
      
    public String getExcludeUrls() {  
        return excludeUrls;  
    }  
    public void setExcludeUrls(String excludeUrls) {  
        this.excludeUrls = excludeUrls;  
        this.excludeUrlArray = excludeUrls.split(",");  
    }  
  
    /** 
     * 这里是处理Multipart http的方法。如果这个返回值为true,那么Multipart http body就会MyMultipartResolver 消耗掉.如果这里返回false 
     * 那么就会交给后面的自己写的处理函数处理例如刚才ServletFileUpload 所在的函数 
     * @see org.springframework.web.multipart.commons.CommonsMultipartResolver#isMultipart(javax.servlet.http.HttpServletRequest) 
     */  
    @Override  
    public boolean isMultipart(HttpServletRequest request) {  
        for (String url: excludeUrlArray) {  
            // 这里可以自己换判断  
            if (request.getRequestURI().contains(url)) {  
                return false;  
            }  
        }  
        return super.isMultipart(request);  
    }  
       
}  
