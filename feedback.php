<?php

require "connect.php";
error_reporting(E_ERROR | E_PARSE);

$rollno = $_GET['rollno'];
$classrooms = $_GET['classrooms'];
$labs = $_GET['labs'];
$corridors = $_GET['corridors'];
$washrooms = $_GET['washrooms'];
$library = $_GET['library'];
$canteen = $_GET['canteen'];



mysql_select_db("student");

$insert_query = "insert into feedback values('$rollno','$classrooms','$labs','$corridors','$washrooms','$library','$canteen');";

if(!mysql_query($insert_query, $connect)){
	echo "Failure!";
}else{
	echo "Page Added.";
}
?>