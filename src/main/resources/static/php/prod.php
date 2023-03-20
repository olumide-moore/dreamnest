$sql = "SELECT * FROM dreamnest.product 
WHERE name LIKE :searchString 
ORDER BY name ASC";

$stmt = $conn->prepare($sql);
$stmt->bindParam(':searchString', $searchString);
$stmt->execute();
$result = $stmt->fetchAll();