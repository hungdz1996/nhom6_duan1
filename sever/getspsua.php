<?php
include "connect.php";
$page =$_GET['page'];
$idsp=2;
$space=5;
$limit=($page - 1) *$space;
 $sql = "select * from sanpham where idsanpham=$idsp limit $limit,$space";
    $result = mysqli_query($conn, $sql) or die("Error in Selecting ". mysqli_error($conn));
   $emparray = array();
    while($row =mysqli_fetch_assoc($result))
   
        {
        array_push($emparray,[
            'id' => $row['id'],
            'tensanpham' => $row['tensanpham'],
            'giasanpham' =>$row['giasanpham'],
            'hinhanhsanpham' =>$row['hinhanhsanpham'],
            'motasanpham' => $row['motasanpham'],
            'idsanpham'  => $row['idsanpham']
        ]);
       
    }
      $json_sanpham = json_encode($emparray,JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
    echo $json_sanpham;


    //close the db connection
    mysqli_close($conn);
?>