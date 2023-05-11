  <html>
  <body>
  <%
  String name=request.getParameter("uname");
  out.print("welcome "+name);
  %>
  <%= "Welcome "+request.getParameter("uname")%>
  </body>
  </html>