<%@ page import="java.sql.*, javax.naming.*, javax.sql.*" contentType="text/html;charset=utf-8"%>
<%
    Connection conn = null;
    try {
        Context initCtx = new InitialContext();
        DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/TestDB");
        conn = ds.getConnection();

        out.println("<h2>DB 연결 성공 ✅</h2>");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NOW() as now"); // 현재 시간 확인용
        while(rs.next()) {
            out.println("DB 서버 시간: " + rs.getString("now"));
        }
    } catch(Exception e) {
        out.println("<h2>DB 연결 실패 ❌</h2>");
        e.printStackTrace(new java.io.PrintWriter(out));
    } finally {
        if(conn != null) try { conn.close(); } catch(Exception e) {}
    }
%>