<?php

namespace Src\Model;


use Src\Database\DBManager;
use Src\DataModel\UserDataModel;

class UserModel
{
    
    private $dbConnection;
    
    //input output db
    /**
     * UserModel constructor.
     */
    public function __construct()
    {
        $this->dbConnection = DBManager::getInstance();
    }

    public function checkLoginExists($username, $password){
        
        error_log("Parametri checkLoginExists: Username: ".$username ."Password: " .$password);      
        $checkUser =  $this->checkUserExists($username);
        $checkPassword = $this->checkPasswordExists($username, $password);
        
        $response = array();
         
        
        if($checkUser and $checkPassword){
            $response["success"] = 1;
            //$response["message"] = "Login effettuato!";
            
        }
        else if ($checkUser and !$checkPassword){
            //$count--;
            $response["success"] = 2;
            //$response["message"] = "Password non valida!";
            error_log("Password non valida");
        }
        else {
            $response["success"] = 0;
            //$response["message"] = "User non valido!";
        }
        echo json_encode($response);
        
        header('Content-Type: application/json');
    }
        
    public function checkPasswordExists($username, $password){
        
        $response = array();
        
        $key = $this->dbConnection->prepare("select chiave from users where username = '$username' ");
        $key->execute();
        $resultArray = $key->fetch();
        $key_value = $resultArray['chiave'];
                              
        //$password_encrypted = base64_encode(md5($password . $chiave, true) . $chiave);
        //$temp=md5($password . $valore_chiave);
      
        $password_encrypted = base64_encode(md5($password . $key_value) .$key_value);
        error_log("Verifica Password: ".$password_encrypted);
        
        $password_encrypted_savedDB = $this->dbConnection->prepare("select password from users where username = '$username'");
        error_log("Parametri checkPasswordExists: Password: " .$password);
        $password_encrypted_savedDB->execute();
        $result = $password_encrypted_savedDB->fetch();
        $password_value_savedDB = $result['password'];
        error_log("password encrypted login : ".$password_value_savedDB);
        error_log("Verifica Password login : ".$password_encrypted);
        error_log("chiave login ".$key_value);
        
        if($password_encrypted == $password_value_savedDB){
            return true;
        }
        else{
            
            return false;
        }
    }
    
    public function checkUserExists($username){
        $dbStatement = $this->dbConnection->prepare("select * from users where username = '$username' ");
        error_log("Parametri checkUserExists: Username: ".$username);
        $dbStatement->execute();
        $resultArray = $dbStatement->rowCount();
                
        if($resultArray == 1){
            return true;
        }
        else{
            return false;
        }
    }
	
    public function registerNewUser($email, $nome, $cognome,$username,$password,$citta,$frase){
             
        // array for JSON response
        $response = array();
        
        $result = $this->dbConnection->prepare("SELECT * FROM users WHERE username = '$username' OR email = '$email'");
        $result->execute();
        $count = $result->fetch();
                
        if($count){
           $response["success"] = 0;
           //$response["message"] = "Username o email già esistenti";
           echo json_encode($response);
        }
        else if(strlen($nome) < '3'){
            $response["success"] = 2;
            echo json_encode($response);
        }
        else if(strlen($cognome) < '2'){
            $response["success"] = 3;
            echo json_encode($response);
        }
        else if (!controllaMail($email)){
           $response["success"] = 4;
           //$response["message"] = "Indirizzo email sbagliato!";
           echo json_encode($response);
        }
        else if(strlen($username) < '5'){
            $response["success"] = 5;
            echo json_encode($response);
        }
        else if (strlen($password) < '8') {
           $response["success"] = 6;
           //$response["message"] = "La password deve contenere almeno 8 caratteri!";
           echo json_encode($response);
        }
        else if(!preg_match("#[0-9]+#",$password)) {
           $response["success"] = 7;
           //$response["message"] = "La password deve contenere almeno un numero!";
           echo json_encode($response);
        }
        else if(!preg_match("#[A-Z]+#",$password)) {
           $response["success"] = 8;
           //$response["message"] = "La password deve contenere almeno una lettera maiuscola!";
           echo json_encode($response);
        }
        else if(!preg_match("#[a-z]+#",$password)) {
           $response["success"] = 9;
           //$response["message"] = "La password deve contenere almeno una lettera minuscola!";
           echo json_encode($response);
        }			
        else{
           /*$options = [
                'cost' => 12,
           ];
           $password = password_hash($password, PASSWORD_BCRYPT, $options);*/
                    
           $key = md5(rand());
           $key = substr($key, 0, 10);
         
           $password_encrypted = base64_encode(md5($password . $key) . $key);  
           error_log("chiave register: ".$key);
           error_log("password register: ".$password);
           error_log("password criptata register: ".$password_encrypted);
           
           $result = $this->dbConnection->prepare("INSERT INTO users ". "(idUser, nome, cognome, citta, email, frase, username, password, 
                                                    chiave) ". "VALUES(NULL, '$nome','$cognome', '$citta', '$email', '$frase', 
                                                    '$username', '$password_encrypted','$key')");
           $result->execute();
                    
           if ($result){
              // successfully inserted into database
              $response["success"] = 1;
              //$response["message"] = "Nuovo utente salvato!";
              // echoing JSON response
              echo json_encode($response);					
           } else {
              // failed to insert row
              $response["success"] = 10;
              //$response["message"] = "Impossibile inserire l'utente!";
              // echoing JSON response
              echo json_encode($response);
           }
        }
            
        header('Content-Type: application/json');
    }
    
    public function registerUserFailed(){
        $response = array();
                
        $response["success"] = 11;
		//$response["message"] = "Riprovare! Uno o più campi sono mancanti!";
		echo json_encode($response);
    }
    
    
}
?>

<?php
function controllaMail($email)
{
	// elimino spazi, "a capo" e altro alle estremità della stringa
	$email = trim($email);

	// se la stringa è vuota sicuramente non è una mail
	if(!$email) {
		return false;
	}

	// controllo che ci sia una sola @ nella stringa
	$num_at = count(explode( '@', $email )) - 1;
	if($num_at != 1) {
		return false;
	}

	// controllo la presenza di ulteriori caratteri "pericolosi":
	if(strpos($email,';') || strpos($email,',') || strpos($email,' ')) {
		return false;
	}

	// la stringa rispetta il formato classico di una mail?
	if(!preg_match( '/^[\w\.\-]+@\w+[\w\.\-]*?\.\w{1,4}$/', $email)) {
		return false;
	}

	return true;
}


?>

