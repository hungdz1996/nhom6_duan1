<?php

include "connect.php";
 $emparray = array();
if ($conn->connect_error) {

die("Conect_fail: " .conn->connect_error);
}
if (isset($_POST['id'])) {
	$id= $_POST['id'];

	$sql ="Delete from donhang where  where id = $id";

	if ($conn ->connect_error ===True) {


		$emparray["success"] =1;
		$emparray["message"] ="Delete thành công";

		echo json_encode($emparray);

		// code...
	}else {
		$emparray["success"] =0;
		$emparray["message"] ="Delete thành công";
	echo json_encode($emparray);
	}
	$conn ->close();
}đây
	else{
		$emparray["success"] =0;
		$emparray["message"] ="Xóa không thành công";

		echo json_encode($emparray);
	}
?>