<%-- 
    Name: Alex Spiegl
    Course: CNT 4714 – Spring 2023 – Project 4
    Assignment title: A Three-Tier Distributed Web-Based Application
    Date: Monday April 24, 2023
--%>
    
<!doctype html">

<%-- start scriptlet --%>
    
<%
    String sqlStatement = (String) session.getAttribute("sqlStatement");
    if (sqlStatement == null) sqlStatement = "select * from suppliers";
    String message = (String) session.getAttribute("message");
    if (message == null) message = " ";
%>

<html>
<head>
   <title>CNT 4714 Three-Tiered Database Management System</title>
   
    <style>
   
        <!--
	    body{background-color: black; text-align: center; font-family: Arial;}
        h1 {color:yellow; font-size:28pt;}
        h2 {color:lime; font-size:24pt;}
        h3 {color:black; font-size:13pt;}
        input {color:yellow; background:#665D1E; font-weight:bold; font-size: 16pt;}
        p {color:black; font-size:13pt;}
        .red{color:red}
        .lime{color:lime}
        table{font-family: Verdana; border:3px solid black;}
        textarea {background: blue; color: white; font-family: Verdana; font-size:15pt; width: 900px; height: 275px;}
        th, td {padding: 5px; border: 1px solid black;}
        .button {padding: 0px 0px; background:#665D1E;}
        .main {color:white;}
        #bl {color: #708090;}
        -->
	
    </style>
    
    <script type="text/javascript">
        function eraseText() {
            document.getElementById("cmd").innerHTML = " ";
        }
    </script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
		$(document).ready(function(){
  			$("button").click(function(){
    			$("p").remove();
  			});
		});
	</script>
</head>
<body>
    
    <h1>Welcome to the Spring 2023 Project 4 Enterprise Database System</h1>
    <h2>A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container </h2>
    <hr>
    <h3 class="main">You are connected to the Project 4 Enterprise System database as a <span class="red">root-level</span> user. <br />
        Please enter any valid SQL query or update command in the box below. <br />
    </h3>
    
    <form action="rootServlet" method="post">
        <textarea id="cmd" name="sqlStatement" cols=60 rows=8><%=sqlStatement%></textarea><br>
        <br>
        <input type="submit" value="Execute Command" <span class="lime"></span> /> &nbsp; &nbsp; &nbsp;
        <input type="reset" value="Reset Form" onclick="javascript:eraseText();" <span class="red"></span>/> &nbsp; &nbsp; &nbsp;
        <button class="button"><input type="reset" value="Clear Result" /></button>
    </form>
    
    <h3 class="main"> <br /> All execution results will appear below this line. </h3>
    <hr>
    <center>
    	<h3>
    		<b class="main">Database Results:</b><br>
    	</h3>
        <p>
            <table id="data">
                <%=message%>
            </table>
        </p>    
    </center>
</body>
</html>