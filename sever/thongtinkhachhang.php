<?php
include "connect.php";
$tenkhachhang =$_POST['tenkhachhang'];
$sodienthoai =$_POST['sodienthoai'];
$email =$_POST['email'];
$tinh =$_POST['Tinh'];
$huyen=$_POST['Huyen'];
if (strlen($tenkhachhang)>0 && strlen($email)>0&& strlen($sodienthoai)>0 && strlen($tinh)>0 && strlen($huyen)>0) {
	$query ="INSERT INTO donhang(id,tenkhachhang,sodienthoai,email,Tinh,Huyen)VALUES (null,'$tenkhachhang','$sodienthoai','$email','$tinh','$huyen')";
	if (mysqli_query($conn,$query)) {
		$iddonghang=$conn ->insert_id;
		echo  $iddonghang;
		// code...
	}else {
		echo "Thất Bại";
	}
}
else{
	echo "Bạn kiểm tra lại dữ liệu";
}
?>