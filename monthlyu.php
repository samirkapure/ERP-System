<?php

require "connect.php";
error_reporting(E_ERROR | E_PARSE);

$rollno = $_GET['rollno'];
$date = $_GET['date'];
$month = $_GET['month'];


mysql_select_db("student");

$insert_query = "insert into monthlyundertaking values('$rollno','$date','$month');";

if(!mysql_query($insert_query, $connect)){
	echo "Failure!";
}else{
	echo "Page Added.";
}
?>