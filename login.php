<?php
error_reporting(E_ERROR | E_PARSE);
require "connect.php";
$email = $_POST["email"];
$password = $_POST["password"];
$division = $_POST["division"];

mysql_select_db("student");

$login_query = mysql_query("select * from user where email='$email' and password='$password' and division='$division';");
if(mysql_affected_rows() == 1){
	while($data = mysql_fetch_assoc($login_query)){
		$data_array = $data;
	}
	echo json_encode($data_array);
}else{
	echo "Login failed!".mysql_error();
}
?>
