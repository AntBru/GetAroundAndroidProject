<?php

namespace Src\View\Common;

abstract class AbstractView
{
    public function render($viewParameter=null){
        error_log("AbstractView - start");

        require_once('../src/Template/Common/TopHtmlTemplate.php');
        $this->renderView($viewParameter);
        require_once('../src/Template/Common/BottomHtmlTemplate.php');

        error_log("AbstractView - end");
    }

    abstract protected function renderView($viewParameter);

}