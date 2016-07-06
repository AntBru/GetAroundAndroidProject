<?php
/**
 * Created by PhpStorm.
 * User: abruno
 * Date: 08/04/2016
 * Time: 09:41
 */

namespace Src\View;


use Src\View\Common\AbstractView;

class ShowInsertUserFormView extends AbstractView
{
    protected function renderView($viewParameter){

        require_once('../src/Template/ShowInsertUserFormTemplate.php');

    }

}