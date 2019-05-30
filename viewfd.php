<?php
error_reporting(E_ERROR | E_PARSE);
require "connect.php";

$i = 0;
mysql_select_db("student");

$fd_query = mysql_query("select avg(classrooms) as classrooms,avg(labs) as labs,avg(corridors) as corridors,avg(washrooms) as washrooms,
	avg(library) as library,avg(canteen) as canteen from feedback;");
$data = mysql_fetch_assoc($fd_query);

echo json_encode($data);

		
?>
