<?php
require "connect.php";

$rollno = $_GET['rollno'];
$name = $_GET['name'];
$division = $_GET['division'];
$email = $_GET['email'];
$password = $_GET['password'];

mysql_select_db("student");

$insertQuery = "insert into user values('$rollno','$name','$email','$password','$division');";
if(!mysql_query($insertQuery)){
	$data['name'] = "Registration failed!";
	echo json_encode($data);
}else{
	$data['name'] = "Registration successfull";
	echo json_encode($data);
}
?>


