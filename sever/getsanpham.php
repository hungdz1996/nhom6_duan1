<?php
    
    include  "connect.php";

    $sql = "select * from sanpham";
    $result = mysqli_query($conn, $sql) or die("Error in Selecting ". mysqli_error($conn));
    // biến $conn bên bảng connect.php gọi lại

    //create an array
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