Feature: Login de usuarios
  Como usuario registrado
  Quiero iniciar sesión con email y contraseña
  Para acceder a mi dashboard de manera segura

Background:
    Given el sistema tiene usuarios registrados

Scenario: Login exitoso
    When intento iniciar sesión con email "user@empresa.com" y contraseña "Correcta123"
    Then debo ver el "dashboard"
    And el sistema registra un acceso exitoso

Scenario Outline: Login fallido por credenciales inválidas
    When intento iniciar sesión con email "<email>" y contraseña "<password>"
    Then debo ver el mensaje de error "<mensaje>"
    And el acceso no debe concederse

Examples:
      | email                 | password     | mensaje                         |
      | user@empresa.com      | mala123      | Credenciales inválidas          |
      | userempresa.com       | Correcta123  | Formato de email inválido       |
      | desconocido@empresa.com | Cualquier1 | Usuario no registrado           |
