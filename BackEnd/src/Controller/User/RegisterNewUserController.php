<?php

namespace Src\Controller\User;


use Src\Controller\Common\AbstractController;
use Src\Model\UserModel;

class RegisterNewUserController extends AbstractController{

    protected function processRequest(){
        error_log("sono entrato nel register new user controller");

        $username = $_POST['username'];
        $password = $_POST['password'];
        $nome = $_POST['nome'];
        $cognome = $_POST['cognome'];
        $email = $_POST['email'];
        $citta = $_POST['citta'];
        $frase = $_POST['frase'];

        $email = filter_var($email, FILTER_SANITIZE_EMAIL);
        $nome=filter_var ( $nome, FILTER_SANITIZE_STRING);
        $cognome = filter_var ($cognome, FILTER_SANITIZE_STRING);
        $username = filter_var ($username, FILTER_SANITIZE_STRING);
        $password = filter_var ($password, FILTER_SANITIZE_STRING);
        $citta = filter_var ($citta, FILTER_SANITIZE_STRING);
        $frase = filter_var ($frase, FILTER_SANITIZE_STRING);
        
        
        if (!empty($email) and !empty($username) and !empty($citta) and !empty($password) 
            and !empty($nome) and !empty($cognome) and !empty($frase)) {
            $model = new UserModel();
            $model->registerNewUser($email, $nome, $cognome,$username,$password,$citta,$frase);
            //$model->registerNewUser("rossi1@tin.it", "Valentino1", "Rossi1","VRossi1","Rossi12345","Milano","hey");
        } else {
            $model = new UserModel();
            $registerUser = $model->registerUserFailed();
        }
    }
}
?>

