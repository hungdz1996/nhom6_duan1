<?php
    include  "connect.php";

    $sql = "select * from donhang";
    $result = mysqli_query($conn, $sql) or die("Error in Selecting ". mysqli_error($conn));
    // biến $conn bên bảng connect.php gọi lại

    //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        array_push($emparray,[
            'id' => $row['id'],
            'tenkhachhang' => $row['tenkhachhang'],
            'sodienthoai'  => $row['sodienthoai'],
            'email' => $row['email'],
            'Tinh' => $row['Tinh'],
            'Huyen' => $row['Huyen']
        ]);
    }

    $json_getdonhang = json_encode($emparray,JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
    echo $json_getdonhang;


    //close the db connection
    mysqli_close($conn);
?>
