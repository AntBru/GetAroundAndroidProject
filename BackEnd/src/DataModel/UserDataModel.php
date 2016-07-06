<?php

namespace Src\DataModel;


class UserDataModel
{
    private $id;
    private $nome;
    private $cognome;
    private $email;
    private $città;
	private $frase;
	private $username;
	private $password;
	private $descrizione;
	private $chiave;

    public function __construct($id, $nome, $cognome, $email, $città, $frase, $username, $password, $chiave, $descrizione)
    {
        $this->id = $id;
        $this->nome = $nome;
        $this->cognome = $cognome;
        $this->email = $email;
        $this->città = $città;
        $this->frase = $frase;
        $this->username = $username;
        $this->password = $password;
        $this->chiave = $chiave;
        $this->descrizione = $descrizione;
    }


    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getNome()
    {
        return $this->nome;
    }

    /**
     * @param mixed $nome
     */
    public function setNome($nome)
    {
        $this->nome = $nome;
    }

    /**
     * @return mixed
     */
    public function getCognome()
    {
        return $this->cognome;
    }

    /**
     * @param mixed $cognome
     */
    public function setCognome($cognome)
    {
        $this->cognome = $cognome;
    }

    /**
     * @return mixed
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * @param mixed $email
     */
    public function setEmail($email)
    {
        $this->email = $email;
    }

    public function getCittà()
    {
        return $this->città;
    }

    public function setCittà($città)
    {
        $this->città = $città;
    }

    public function getFrase()
    {
        return $this->frase;
    }

    
    public function setFrase($frase)
    {
        $this->frase = $frase;
    }

    public function getUsername()
    {
        return $this->username;
    }

    
    public function setUsername($username)
    {
        $this->username = $username;
    }

    
    public function getPassword()
    {
        return $this->password;
    }

    public function setPassword($password)
    {
        $this->password = $password;
    }
	
	public function getChiave()
    {
        return $this->chiave;
    }

    public function setChiave($chiave)
    {
        $this->chiave = $chiave;
    }

	public function getDescrizione()
    {
        return $this->descrizione;
    }

    public function setDescrizione($descrizione)
    {
        $this->descrizione = $descrizione;
    }

}