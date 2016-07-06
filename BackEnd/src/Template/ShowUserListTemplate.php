

<h1>List of Users</h1>

<table>
    <tr><th>Username</th><th>Password</th></tr>
    <?php foreach ($userDataModelArray as $post){ ?>
        <tr>
            <td><?php echo $post->getId(); ?></td>
            <td><?php echo $post->getUsername(); ?></td>
            <td><?php echo $post->getPassword(); ?></td>
            </tr>
    <?php } ?>
</table>
<br>
<a href="index.php?libraryAction=registerNewUser">Inserisci un nuovo utente</a>

