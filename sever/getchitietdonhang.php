<?php
include "connect.php";
$json =$_POST['json'];
    
  $data = json_decode($json,true);
  foreach ($data as $value) {
  	$makhachhang = $value['makhachhang'];
  	$masanpham = $value['masanpham'];
  	$tensanpham = $value['tensanpham'];
  	$giasanpham = $value['giasanpham'];
  	  $soluongsanpham = $value['soluongsanpham'];
  	  $query ="INSERT INTO chitietdonghang(id,	makhachhang,masanpham,tensanpham,giasanpham,soluongsanpham) VALUES(null,'$makhachhang','$masanpham','$tensanpham','$giasanpham',' $soluongsanpham ')";
  	  $Dtaa=mysqli_query($conn,$query);
  }
  if ($Dtaa) {
  	echo "1";
  }
  else{
  	echo "0";
  }

?>