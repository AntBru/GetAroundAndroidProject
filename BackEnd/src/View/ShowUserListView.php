<?php

namespace Src\View;

use Src\View\Common\AbstractView;

class ShowUserListView extends AbstractView
{
    protected function renderView($userDataModelArray){

        require_once('../src/Template/ShowUserListTemplate.php');

    }

}