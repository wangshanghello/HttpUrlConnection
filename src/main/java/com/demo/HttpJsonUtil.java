package com.demo;

/**
* 创建人：qiujian
* 创建时间：2016年3月9日 下午3:20:22
* 版本： 1.0
* 类描述：
**/

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpJsonUtil {
  private static final int CONNECT_TIMEOUT = 30000;
  private static final int READ_TIMEOUT = 30000;
  private static final String encoding = "UTF-8";
   /**
   * 发送http请求，内容为json格式；返回json响应
   * @param urlStr
   * @param jsonStr
   * @return
   */
  public static String jsonPost(String urlStr,String jsonStr) {
    BufferedReader br = null;
    HttpURLConnection urlconn = null;
    OutputStream out=null;
    try {
      URL url = new URL(urlStr);
      urlconn = (HttpURLConnection) url.openConnection();
      urlconn.setRequestProperty("Content-type", "application/json;charset="+encoding);
      urlconn.setConnectTimeout(CONNECT_TIMEOUT);
      urlconn.setReadTimeout(READ_TIMEOUT);
      urlconn.setRequestMethod("POST");
      urlconn.setUseCaches(false);
      urlconn.setDoOutput(true);
      urlconn.setDoInput(true);
      //发送请求
       {
           out = urlconn.getOutputStream();
           if(jsonStr!=null && jsonStr.length()>0)
         out.write(jsonStr.getBytes("utf-8"));
           out.flush();
           out.close();
           out=null;
       }

      //处理响应
       char[] charBuf = new char[1024];
       int readChars = -1;
       StringBuffer res = new StringBuffer();
      InputStream inStrm = urlconn.getInputStream();
      br = new BufferedReader(new InputStreamReader(inStrm,encoding));
      while ((readChars=br.read(charBuf)) != -1){
          res.append(charBuf,0, readChars);
      }
      br.close();
      urlconn.disconnect();
      urlconn=null;
      return res.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
        try { if(out!=null){out.close();}  } catch (IOException e) {e.printStackTrace();}
        try { if(br!=null){br.close();}  } catch (IOException e) {e.printStackTrace();}
         if(urlconn!=null) urlconn.disconnect();
    }
  }

    /**
     * 发送请求时，可以设置响应等待时间
     * @param urlStr 请求地址
     * @param jsonStr 参数
     * @param readTimeOut 数据传输超时时间，单位毫秒
     * @return
     */
    public static String jsonPost(String urlStr,String jsonStr,int readTimeOut) {
        BufferedReader br = null;
        HttpURLConnection urlconn = null;
        OutputStream out=null;
        try {
            URL url = new URL(urlStr);
            urlconn = (HttpURLConnection) url.openConnection();
            urlconn.setRequestProperty("Content-type", "application/json;charset="+encoding);
            urlconn.setConnectTimeout(CONNECT_TIMEOUT);
            urlconn.setReadTimeout(readTimeOut);
            urlconn.setRequestMethod("POST");
            urlconn.setUseCaches(false);
            urlconn.setDoOutput(true);
            urlconn.setDoInput(true);
            //发送请求
            {
                out = urlconn.getOutputStream();
                if(jsonStr!=null && jsonStr.length()>0)
                    out.write(jsonStr.getBytes("utf-8"));
                out.flush();
                out.close();
                out=null;
            }

            //处理响应
            char[] charBuf = new char[1024];
            int readChars = -1;
            StringBuffer res = new StringBuffer();
            InputStream inStrm = urlconn.getInputStream();
            br = new BufferedReader(new InputStreamReader(inStrm,encoding));
            while ((readChars=br.read(charBuf)) != -1){
                res.append(charBuf,0, readChars);
            }
            br.close();
            urlconn.disconnect();
            urlconn=null;
            return res.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try { if(out!=null){out.close();}  } catch (IOException e) {e.printStackTrace();}
            try { if(br!=null){br.close();}  } catch (IOException e) {e.printStackTrace();}
            if(urlconn!=null) urlconn.disconnect();
        }
    }

  /**
   * 向数据库Post数据
   * @param urlStr 服务地址
   * @param postData 发送数据内容
   * @param headers 请求头
   * @return
   */
  public static String postData(String urlStr,String postData,Map<String,String> headers) {
    BufferedReader br = null;
    HttpURLConnection urlconn = null;
    OutputStream out=null;
    try {
      URL url = new URL(urlStr);
      urlconn = (HttpURLConnection) url.openConnection();
      if(headers!=null && !headers.isEmpty()){
        for(String key : headers.keySet()){
          urlconn.setRequestProperty(key,headers.get(key));
        }
      }
      urlconn.setConnectTimeout(CONNECT_TIMEOUT);
      urlconn.setReadTimeout(READ_TIMEOUT);
      urlconn.setRequestMethod("POST");
      urlconn.setUseCaches(false);
      urlconn.setDoOutput(true);
      urlconn.setDoInput(true);
      //发送请求
       {
           out = urlconn.getOutputStream();
           if(postData!=null && postData.length()>0)
             out.write(postData.getBytes("utf-8"));
           out.flush();
           out.close();
           out=null;
       }

      //处理响应
       char[] charBuf = new char[1024];
       int readChars = -1;
       StringBuffer res = new StringBuffer();
      InputStream inStrm = urlconn.getInputStream();
      br = new BufferedReader(new InputStreamReader(inStrm,encoding));
      while ((readChars=br.read(charBuf)) != -1){
          res.append(charBuf,0, readChars);
      }
      br.close();
      urlconn.disconnect();
      urlconn=null;
      return res.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
        try { if(out!=null){out.close();}  } catch (IOException e) {e.printStackTrace();}
        try { if(br!=null){br.close();}  } catch (IOException e) {e.printStackTrace();}
         if(urlconn!=null) urlconn.disconnect();
    }
  }

  public static String postData(String urlStr,String json) throws UnsupportedEncodingException{
    String  jsonData ="jsondata="+URLEncoder.encode(json,"UTF-8");
    return postData(urlStr,jsonData,null);
  }
}

