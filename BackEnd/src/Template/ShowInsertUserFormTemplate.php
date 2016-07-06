<h1>Insert Users</h1>


<form  action="index.php"  method="post" name="modulo"  onsubmit="return ValidateUserForm();">


    <label>Nome </label>
    <input name="nome" type="text" />
    <span class="error">* <?php echo "Name is required";?></span>
    <br><br>

    <label>Cognome </label>
    <input name="cognome" type="text" />
    <span class="error">* <?php echo "Surname is required";?></span>
    <br><br>

    <label>Email </label>
    <input name="email" type="text" />
    <span class="error">* <?php echo "Email is required";?></span>
    <br><br>

    <input name="libraryAction" type="hidden" value="insertNewUser"/>

    <input name="aggiungi" type="submit" value="Insert"/>
</form>
<br>
<a href="index.php?libraryAction=showUserList">Indietro</a>

