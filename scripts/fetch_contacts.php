<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "youthexploringscience";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT first_name, last_name, email, phone, image FROM contacts";
$result = $conn->query($sql);
$results = array();


if ($result->num_rows > 0) {
    // insert data of each row into array
    while($row = $result->fetch_assoc()) {
        $results[] = array('firstName' => $row["first_name"],
                            'lastName' => $row["last_name"],
                            'email' => $row["email"],
                            'phone' => $row["phone"],
                            'image' => $row["image"]);
    }
}

echo json_encode($results);

$conn->close();

?>
