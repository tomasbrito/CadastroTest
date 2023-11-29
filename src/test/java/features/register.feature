Feature: Register


  Scenario: Verificar elementos en la pagina de registro
    Given estoy en la pagina de registro
    Then deberia ver el campo de nombre con el valor inicial vacio
    And deberia ver el campo de correo electronico con el valor inicial vacio
    And deberia ver el campo de contrasenia con el valor inicial vacio y caracteres enmascarados
    And deberia ver el boton de registro


  Scenario: Visualizar error en campos vacios
    Given estoy en la pagina de registro
    When hago clic en el boton 'cadastrar'
    Then deberia ver los errores 'O campo Nome é obrigatório.', 'O campo E-mail é obrigatório.' y 'O campo Senha é obrigatório.' en los campos vacios


  Scenario: Visualizar error en nombre
    Given estoy en la pagina de registro
    When ingreso "Tomas" en el campo nombre
    And ingreso "tomas@gmail.com" en el campo email
    And ingreso "12345678" en el campo contrasenia
    And hago clic en el boton 'cadastrar'
    Then deberia ver el error "Por favor, insira um nome completo." en el campo nombre


  Scenario: Visualizar error en email
    Given estoy en la pagina de registro
    When ingreso "Tomas Brito" en el campo nombre
    And ingreso "tomas.gmail.com" en el campo email
    And ingreso "12345678" en el campo contrasenia
    And hago clic en el boton 'cadastrar'
    Then deberia ver el error "Por favor, insira um e-mail válido." en el campo email


  Scenario: Visualizar error en contraseña
    Given estoy en la pagina de registro
    When ingreso "Tomas Brito" en el campo nombre
    And ingreso "tomas@gmail.com" en el campo email
    And ingreso "1234567" en el campo contrasenia
    And hago clic en el boton 'cadastrar'
    Then deberia ver el error "A senha deve conter ao menos 8 caracteres." en el campo contrasenia


  Scenario: Registrar usuario
    Given estoy en la pagina de registro
    When ingreso "Tomas Brito" en el campo nombre
    And ingreso "tomas@gmail.com" en el campo email
    And ingreso "12345678" en el campo contrasenia
    And hago clic en el boton 'cadastrar'
    Then deberia ver el usuario en la lista
    And deberia ver un boton para eliminar el usuario




  Scenario: Eliminar usuario
    Given estoy en la pagina de registro
    When creo un usuario con nombre "Usuario 1", correo "usuario1@email.com" y contrasenia "password1"
    And creo un usuario con nombre "Usuario 2", correo "usuario2@email.com" y contrasenia "password2"
    And creo un usuario con nombre "Usuario 3", correo "usuario3@email.com" y contrasenia "password3"
    And presiono la opcion Eliminar desde un elemento de la tabla de usuario para el usuario con ID '2'
    Then deberia ver una tabla con los datos de los usuarios ordenados por ID
      | 1  | Usuario 1 | usuario1@email.com  |
      | 3  | Usuario 3 | usuario3@email.com  |

