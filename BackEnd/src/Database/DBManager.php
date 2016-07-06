<?php
/**
 * Created by PhpStorm.
 * User: abruno
 * Date: 06/04/2016
 * Time: 10:57
 */

namespace Src\Database;

class DBManager
{
    //singleton instance
    private static $instance = null;

    private static function getDB(){


        $connection = new \PDO(
            'mysql:host='.DBConnection::host.';dbname='.DBConnection::db_name,
            DBConnection::username,
            DBConnection::password
        );

        return $connection;
    }

    public static function getInstance()
    {
        if (DBManager::$instance == null) {
            DBManager::$instance = self::getDB();//self per accedere ad un metodo di classe,
                                                //mentre -> per accedere ad un metodo di istanza
        }
        return DBManager::$instance;
    }
}

?>