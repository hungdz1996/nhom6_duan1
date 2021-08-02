<?php
    
    include  "connect.php";

    $sql = "select * from loaisanpham";
    $result = mysqli_query($conn, $sql) or die("Error in Selecting ". mysqli_error($conn));
    // biến $conn bên bảng connect.php gọi lại

    //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        array_push($emparray,[
            'id' => $row['id'],
            'tenloaisanpham' => $row['tenloaisanpham'],
            'hinhanhsanpham' => $row['hinhanhsanpham']
        ]);
    }

    $json_loaisp = json_encode($emparray,JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
    echo $json_loaisp;


    //close the db connection
    mysqli_close($conn);
?>