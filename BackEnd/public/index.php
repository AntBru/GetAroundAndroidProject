<?php

require_once "../vendor/autoload.php";

$geoAction = "";
if(isset($_REQUEST['geoAction'])){
    $geoAction = $_REQUEST['geoAction'];
}

$controllerFactory = new \Src\Controller\Factory\GeoControllerFactory();
$controller = $controllerFactory->getControllerByAction($geoAction);
$controller->process();
