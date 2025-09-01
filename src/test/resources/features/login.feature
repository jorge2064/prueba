Feature: Login de usuario
  Como cliente del sistema
  Quiero iniciar sesión
  Para acceder a mis funcionalidades

  Background:
    Given existe un usuario "alice" con contraseña "secret"

  Scenario: Login exitoso con credenciales válidas
    When intento iniciar sesión con usuario "alice" y contraseña "secret"
    Then el resultado del inicio de sesión debe ser "exitoso"

  Scenario Outline: Login fallido con credenciales inválidas
    When intento iniciar sesión con usuario "<usuario>" y contraseña "<clave>"
    Then el resultado del inicio de sesión debe ser "fallido"

    Examples:
      | usuario | clave   |
      | pedro   | 1234    |
      | manuel  | perro   |
      | samuel  | gato    |