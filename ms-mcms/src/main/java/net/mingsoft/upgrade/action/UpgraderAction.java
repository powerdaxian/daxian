package net.mingsoft.upgrade.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.basic.action.BasicAction;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.biz.IRoleModelBiz;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

@Controller("upgrader")
@RequestMapping({"/${managerPath}/upgrader"})
public class UpgraderAction extends BasicAction
{
  private String html = "<html><head><title>价值源自分享！</title></head><body style=\"padding-top: 5%;background-color: #ffffff;\"><center>\t<img src=\"http://cdn.mingsoft.net/global/mstore/{result/}.png\" />\t<div>\t\t<p style=\"clear: both; margin: 30px auto 20px auto; line-height: 35px; font-size: 20px; color: #7e7e7e;\">{message/}</p>\t</div></center></body></html>";

  private String MS_MSTORE_HOST = "http://mstore.mingsoft.net";

  @Autowired
  private IModelBiz modelBiz;

  @Autowired
  private IRoleModelBiz roleModelBiz;

  @ResponseBody
  @RequestMapping(value={"/sync"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public void sync(HttpServletRequest request, HttpServletResponse response)
  {
    String sync = ((HttpRequest)HttpUtil.createPost(this.MS_MSTORE_HOST + "/mstore/sync.do").header("ms", "upgrader")).execute().body();
    outJson(response, sync);
  }

  @ResponseBody
  @RequestMapping({"/setup"})
  public void setup(HttpServletRequest request, HttpServletResponse response)
    throws ClientProtocolException, IOException
  {}

  private boolean checkModel(String className) {
    try {
      Class.forName(className);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}