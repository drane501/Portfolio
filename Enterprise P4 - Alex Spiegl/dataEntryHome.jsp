<%-- 
    Name: Alex Spiegl
    Course: CNT 4714 – Spring 2023 – Project 4
    Assignment title: A Three-Tier Distributed Web-Based Application
    Date: Monday April 24, 2023
--%>
    
<!doctype html">

<html>
<head>
   <title>CNT 4714 Three-Tiered Database Management System</title>
   
    <style>
    
        <!--
	    body{background-color: black; text-align: center; font-family: Arial;}
        h1 {color:red; font-size:28pt;}
        h2 {color:cyan; font-size:24pt;}
        input {color:yellow; background:#665D1E; font-weight:bold; font-size: 16pt;}
        p {color:white; font-size:13pt;}
        .red {color:red;}
        .green {color:lime;}
        table{font-family: Verdana; border:3px white;}
        textarea {background: #665D1E; color: white; font-family: Verdana; font-size:15pt; width: 300px; height: 50px;}
        th, td {color:white; padding: 5px; border: 1px white;}
        .main {color:white;}
        #bl {color: #708090;}
        legend {color:white;}
	    -->
	
    </style>
    
    <script type="text/javascript">
        function eraseText() {
            document.getElementById("cmd").innerHTML = " ";
        }
    </script>

</head>
<body>
    
    <h1>Welcome to the Spring 2023 Project 4 Enterprise Database System</h1>
    <h2>Data Entry Application </h2>
    <hr>
    <p class="main">You are connected to the Project 4 Enterprise System database as a <span class="red">data-entry-level</span> user. <br />
        Enter the data values in a form below to add a new record to the corresponding database table. <br />
    </p>
    
    <form action="AddSupplierRecord" method="post">
    	<fieldset>
    	<legend> Suppliers Record Insert </legend>
    	<center>
    	<table>
    		<tr>
    			<th>snum</th>
    			<th>sname</th>
    			<th>status</th>
    			<th>city</th>
    		</tr>
    		<tr>
    			<td><textarea id="cmd" name="snum"></textarea></td>
    			<td><textarea id="cmd" name="sname"></textarea></td>
    			<td><textarea id="cmd" name="status"></textarea></td>
    			<td><textarea id="cmd" name="city"></textarea></td>
    	</table>
    	<center>
        <input type="submit" value= "Enter Supplier Records Into Database" name="suppliersform" <span class="green"></span> /> &nbsp; &nbsp; &nbsp;
        <input type="reset" value="Clear Data and Results" onclick="javascript:eraseText(%=message%);" />
        </fieldset>
    </form>
  
    <form action="AddPartRecord" method="post">
    	<fieldset>
    	<legend> Part Record Insert </legend>
    	<center>
    	<table>
    		<tr>
    			<th>pnum</th>
    			<th>pname</th>
    			<th>color</th>
    			<th>weight</th>
    			<th>city</th>
    		</tr>
    		<tr>
    			<td><textarea id="cmd" name="pnum"></textarea></td>
    			<td><textarea id="cmd" name="pname"></textarea></td>
    			<td><textarea id="cmd" name="color"></textarea></td>
    			<td><textarea id="cmd" name="weight"></textarea></td>
    			<td><textarea id="cmd" name="city"></textarea></td>
    	</table>
    	<center>
        <input type="submit" value= "Enter Part Records Into Database" name="partform" <span class="green"></span> /> &nbsp; &nbsp; &nbsp;
        <input type="reset" value="Clear Data and Results" onclick="javascript:eraseText(%=message%);" />
        </fieldset>
    </form>
    
    <form action="AddJobRecord" method="post">
    	<fieldset>
    	<legend> Job Record Insert </legend>
    	<center>
    	<table>
    		<tr>
    			<th>jnum</th>
    			<th>jname</th>
    			<th>numworkers</th>
    			<th>city</th>
    		</tr>
    		<tr>
    			<td><textarea id="cmd" name="snum"></textarea></td>
    			<td><textarea id="cmd" name="pnum"></textarea></td>
    			<td><textarea id="cmd" name="jnum"></textarea></td>
    			<td><textarea id="cmd" name="quantity"></textarea></td>
    	</table>
    	<center>
        <input type="submit" value= "Enter Job Records Into Database" name="jobform" <span class="green"></span> /> &nbsp; &nbsp; &nbsp;
        <input type="reset" value="Clear Data and Results" onclick="javascript:eraseText(%=message%);" />
        </fieldset>
    </form>
    
    <form action="AddShipmentRecord" method="post">
    	<fieldset>
    	<legend> Shipment Record Insert </legend>
    	<center>
    	<table>
    		<tr>
    			<th>snum</th>
    			<th>sname</th>
    			<th>status</th>
    			<th>city</th>
    		</tr>
    		<tr>
    			<td><textarea id="cmd" name="snum"></textarea></td>
    			<td><textarea id="cmd" name="sname"></textarea></td>
    			<td><textarea id="cmd" name="status"></textarea></td>
    			<td><textarea id="cmd" name="city"></textarea></td>
    	</table>
    	<center>
        <input type="submit" value= "Enter Shipment Records Into Database" name="shipmentform" <span class="green"></span> /> &nbsp; &nbsp; &nbsp;
        <input type="reset" value="Clear Data and Results" onclick="javascript:eraseText(%=message%);" />
        </fieldset>
    </form>
    
    <p class="main"> <br /> All execution results will appear below this line. </p>
    <hr>
    <center>
        <p>
            <b class="main">Database Results:</b><br>
        </p>    
    </center>
</body>
</html>