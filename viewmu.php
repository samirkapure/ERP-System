<?php
error_reporting(E_ERROR | E_PARSE);
require "connect.php";

$i = 0;
mysql_select_db("student");

$disp_query = mysql_query("select a.*,b.name,b.division from  monthlyundertaking as a INNER JOIN user as b ON a.rollno=b.rollno;");
$array = array();
while($row = mysql_fetch_assoc($disp_query)){
	
  // add each row returned into an array
  $array[$i] = $row;
	$i++;

}
echo json_encode($array);

		
?>
