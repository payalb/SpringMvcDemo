<%@page isELIgnored="false" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>

<link href="resource/css/style.css" rel="stylesheet">
</head>
<body class="bgcolor">
	<a href="./index?locale=en">English</a>
	<a href="./index?locale=fr">French</a>
	<a href="./index?locale=zh">Chinese</a> ${header1}
	<h2>
		<spring:message code="greet" />
	</h2>
	Menu:
	<form action="./deposit" method="post">

		Account id:<input type="number" name="accId"> 
		<br/> Amount<input type="number"
			name="amt" />
		<!-- add a text box to enter to which account u want to transfer -->
		<input type="submit" value="Deposit Money" />
	</form>
	<form action="./withdraw" method="post">

		Account id:<input type="number" name="accId"> 
		<br/>Amount<input type="number"
			name="amt" /> <input type="submit" value="Withdraw Money" />
	</form>
	<form action="./transfer" method="post">

	Account1	<input type="number" name="accId1">
	<br/>Account2 <input type="number"
			name="accId2"> <input type="number" name="amt" />
			<br/>Amount <input
			type="submit" value="Transfer Money" />

	</form>
</body>
</html>
