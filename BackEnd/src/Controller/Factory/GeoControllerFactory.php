<?php

namespace Src\Controller\Factory;

use Src\Controller\User\RegisterNewUserController;
use Src\Controller\User\CheckLoginController;

class GeoControllerFactory
{
    public function getControllerByAction($geoAction){
        switch($geoAction){
            case 'checkLogin': return new CheckLoginController(); break;
            error_log("checkLogin");
            case "registerNewUser": return new RegisterNewUserController(); break;
            error_log("registerNewUser");
            
            default:
                return new CheckLoginController(); break;
        }
    }

}