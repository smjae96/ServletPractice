/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.86
 * Generated at: 2024-03-08 01:13:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class _03_005fdirectiveInclude_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

 
		public void test() {
			System.out.println("test 메소드를 정의했고, 누군가 불렀다..");	
		}
	
  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/views/01_ScriptingElement.jsp", Long.valueOf(1709781137595L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Directive Include</title>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("	h1 {color: red;'}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<h1>include 지시어</h1>\r\n");
      out.write("	\r\n");
      out.write("	<div style=\"border: 1px solid magenta\">\r\n");
      out.write("		");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>01 Scripting Element</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<h1>스크립팅 원소</h1>\r\n");
      out.write("	<!-- HTML 주석 : 개발자도구 창에서 소스(Elements)에 표시됨. -->\r\n");
      out.write("	");
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	");

		// 스크립틀릿 : 이 영역에서는 일반적인 자바 코드 작성 (변수 선언/초기화/제어문 등)
		// 1부터 100까지 더해서 콘솔창에 "계산 결과=xx" 출력
		int i= 0;
		for(int j=1; j<=100; j++) {
			i += j;
		}
		System.out.println("계산 결과= " +i);
	
      out.write("\r\n");
      out.write("	<p>\r\n");
      out.write("		브라우저 화면에 출력하고자 한다면 <br>\r\n");
      out.write("		스크립틀릿을 사용하여 출력 가능 : ");
 out.println(i); 
      out.write("<br>\r\n");
      out.write("		표현식(출력식)을 사용하여 출력 가능 : ");
      out.print( i );
      out.write("\r\n");
      out.write("	</p>\r\n");
      out.write("	\r\n");
      out.write("	");

		// 문자열 배열에 좋아하는 음식 5가지 정도 초기화
		String[] foods = {"음식1", "음식2", "음식3", "음식4", "음식5"};
	
      out.write("\r\n");
      out.write("	<h5>배열의 길이 : ");
      out.print( foods.length );
      out.write("</h5>\r\n");
      out.write("	<h5>배열에 담긴 값 : ");
      out.print( String.join("-", foods) );
      out.write("</h5>\r\n");
      out.write("	\r\n");
      out.write("	<h5>반복문을 사용하여 HTML 요소를 반복적으로 화면에 출력</h5>\r\n");
      out.write("	<ol>\r\n");
      out.write("		");
 for(String food : foods) { 
      out.write("\r\n");
      out.write("			<li>");
      out.print( food );
      out.write("</li>\r\n");
      out.write("		");
 } 
      out.write("\r\n");
      out.write("	</ol>\r\n");
      out.write("	<!-- 메소드 정의 : 현재 jsp 페이지 내에서만 사용할 수 있는 메소드를 정의 -->\r\n");
      out.write("	");
      out.write("\r\n");
      out.write("	<!-- 메소드 호출(실행) -->\r\n");
      out.write("	");
 test(); 
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	포함된 jsp 페이지에서 선언된 변수 사용 가능 <br>\r\n");
      out.write("	1부터 100까지 총 합계 : ");
      out.print( i );
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
