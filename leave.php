<?php

require "connect.php";
error_reporting(E_ERROR | E_PARSE);

$rollno = $_GET['rollno'];
$date = $_GET['date'];
$reason = $_GET['reason'];


mysql_select_db("student");

$insert_query = "insert into leaveappl values('$rollno','$date','$reason');";

if(!mysql_query($insert_query, $connect)){
	echo "Failure!";
}else{
	echo "Page Added.";
}
?>