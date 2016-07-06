<?php

namespace Src\Controller\Common;

abstract class AbstractController
{
    public function process(){
        error_log("AbstractController - start");

        try{
            $this->processRequest();
        }catch(\Exception $ex){
            echo "Mi dispiace c'� stato un errore.";
            echo "Error -> ".$ex->getMessage();
        }
        error_log("AbstractController - end");
    }

    abstract protected function processRequest();

}