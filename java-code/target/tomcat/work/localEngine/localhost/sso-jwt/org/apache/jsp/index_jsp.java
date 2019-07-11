package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("function login(){\r\n");
      out.write("\tvar username = $(\"#username\").val();\r\n");
      out.write("\tvar password = $(\"#password\").val();\r\n");
      out.write("\tvar params = \"username=\"+username+\"&password=\"+password;\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\t'url' : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login',\r\n");
      out.write("\t\t'data' : params,\r\n");
      out.write("\t\t'success' : function(data){\r\n");
      out.write("\t\t\tif(data.code == 200){\r\n");
      out.write("\t\t\t\tvar token = data.token;\r\n");
      out.write("\t\t\t\t// web storage的查看 - 在浏览器的开发者面板中的application中查看。\r\n");
      out.write("\t\t\t\t// local storage - 本地存储的数据。 长期有效的。\r\n");
      out.write("\t\t\t\t// session storage - 会话存储的数据。 一次会话有效。\r\n");
      out.write("\t\t\t\tvar localStorage = window.localStorage; // 浏览器提供的存储空间。 根据key-value存储数据。\r\n");
      out.write("\t\t\t\tlocalStorage.token = token;\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\talert(data.msg);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function setHeader(xhr){ // XmlHttpRequest\r\n");
      out.write("\txhr.setRequestHeader(\"Authorization\",window.localStorage.token);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function testLocalStorage(){\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\t'url' : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/testAll',\r\n");
      out.write("\t\t'success' : function(data){\r\n");
      out.write("\t\t\tif(data.code == 200){\r\n");
      out.write("\t\t\t\twindow.localStorage.token = data.token;\r\n");
      out.write("\t\t\t\talert(data.data);\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\talert(data.msg);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\t'beforeSend' : setHeader\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body >\r\n");
      out.write("\t<center>\r\n");
      out.write("\t\t<table>\r\n");
      out.write("\t\t\t<caption>登录测试</caption>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td style=\"text-align: right; padding-right: 5px\">\r\n");
      out.write("\t\t\t\t登录名：\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td style=\"text-align: left; padding-left: 5px\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" name=\"username\" id=\"username\"/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td style=\"text-align: right; padding-right: 5px\">\r\n");
      out.write("\t\t\t\t密码：\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td style=\"text-align: left; padding-left: 5px\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" name=\"password\" id=\"password\"/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td style=\"text-align: right; padding-right: 5px\" colspan=\"2\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" value=\"登录\" onclick=\"login();\" />\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</center>\r\n");
      out.write("\t<input type=\"button\" value=\"testLocalStorage\" onclick=\"testLocalStorage();\"/>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
