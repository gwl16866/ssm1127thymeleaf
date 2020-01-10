package com.huayu.ssm.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MyTagLib extends SimpleTagSupport {
    private String url;
    private String param;

    private Integer currentPage;
    private Integer totalPage;

    private String myPageSize;
    private Integer myPageSizeVal;


    private String param1;
    private String param1Value;

    private String param2;
    private String param2Value;

    private String param3;
    private String param3Value;

    private String param4;
    private String param4Value;

    @Override
    public void doTag() throws JspException, IOException {

    Integer lastPage=currentPage-1>0? currentPage-1:currentPage;
    Integer nextPage=currentPage+1>totalPage? currentPage:currentPage+1;
    StringBuffer url1=new StringBuffer(url+"?"+param+"="+lastPage+"&"+myPageSize+"="+myPageSizeVal);
    StringBuffer url2=new StringBuffer(url+"?"+param+"="+nextPage+"&"+myPageSize+"="+myPageSizeVal);
            if(param1Value !=null && !param1Value.equals("")){
                url1.append("&"+param1+"="+param1Value);
                url2.append("&"+param1+"="+param1Value);
            }
            if(param2Value !=null && !param2Value.equals("")){
                url1.append("&"+param2+"="+param2Value);
                url2.append("&"+param2+"="+param2Value);
            }
            if(param3Value !=null && !param3Value.equals("")){
                url1.append("&"+param3+"="+param3Value);
                url2.append("&"+param3+"="+param3Value);
            }
            if(param4Value !=null && !param4Value.equals("")){
                url1.append("&"+param4+"="+param4Value);
                url2.append("&"+param4+"="+param4Value);
            }

    StringBuilder sb=new StringBuilder();
    String str="";
    sb.append("<a href='"+url1+"'>上一页</a>&nbsp;");
/*     for(int i=1;i<=totalPage;i++){
         String middle=url+"?"+param+"="+i+"&"+param1+"="+param1Value+"&"+param2+"="+param2Value+"&"+param3+"="+param3Value;
         str="<a href='"+middle+"'>"+ i +"</a> &nbsp;";
         sb.append(str);
     }*/
        sb.append("<a href='"+url2+"'>下一页</a>&nbsp;");
        getJspContext().getOut().write(sb.toString());


    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam1Value() {
        return param1Value;
    }

    public void setParam1Value(String param1Value) {
        this.param1Value = param1Value;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam2Value() {
        return param2Value;
    }

    public void setParam2Value(String param2Value) {
        this.param2Value = param2Value;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam3Value() {
        return param3Value;
    }

    public void setParam3Value(String param3Value) {
        this.param3Value = param3Value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getMyPageSize() {
        return myPageSize;
    }

    public void setMyPageSize(String myPageSize) {
        this.myPageSize = myPageSize;
    }

    public Integer getMyPageSizeVal() {
        return myPageSizeVal;
    }

    public void setMyPageSizeVal(Integer myPageSizeVal) {
        this.myPageSizeVal = myPageSizeVal;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam4Value() {
        return param4Value;
    }

    public void setParam4Value(String param4Value) {
        this.param4Value = param4Value;
    }
}
