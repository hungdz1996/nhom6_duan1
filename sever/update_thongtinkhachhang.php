<?php
include "connect.php";
 $emparray = array();
if ($conn->connect_error) {

die("Conect_fail: " .conn->connect_error);
}
if (isset($_POST['id'])&& isset($_POST['tenkhachhang'])&&isset($_POST['sodienthoai'])&& isset($_POST['email']) && isset($_POST['Tinh'])&& isset($_POST['Huyen'])) {
	$id=$_POST['id'];
	$tenkhachhang=$_POST['tenkhachhang'];
	$sodienthoai=$_POST['sodienthoai'];
	$email=$_POST['email'];
	$Tinh=$_POST['Tinh'];
	$Huyen=$_POST['Huyen'];
	
	$sql="Update donhang Set tenkhachhang ='$tenkhachhang',sodienthoai ='$sodienthoai',email = 'sodienthoai' ,Tinh = '$Tinh',Huyen ='$Huyen' where id = $id";

	if ($conn ->query($sql) === TRUE) {

		$emparray["success"] =1;
		$emparray["message"] ="Update thành công";

		echo json_encode($emparray);
		// code...
	}
	else{
			echo "Error:".$conn->error;
	}
	$conn ->close();
}
else{
		$emparray["success"] =0;
		$emparray["message"] ="Update không thành công";

		echo json_encode($emparray);
}
?>