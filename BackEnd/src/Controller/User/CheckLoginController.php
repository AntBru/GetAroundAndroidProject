<?php

namespace Src\Controller\User;

use Src\Controller\Common\AbstractController;
use Src\Model\UserModel;


class CheckLoginController extends AbstractController
{
    protected function processRequest(){
        error_log("sono entrato nel check login controller ");

        $username = $_POST['username'];
        $password = $_POST['password'];
        
        //istanzio l'oggetto model
        $model = new UserModel();
        error_log("Parametri passati: Username: " .$username ." password: ".$password);
        //creare l'oggetto getUserList
        $model->checkLoginExists($username, $password);       
        
        //$model->checkLoginExists("PGuardiola", "Messi100");
       
    }

}