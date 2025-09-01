package com.macortes.bdd;

import io.cucumber.java.en.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

  // "Sistema" mínimo en memoria
  private Map<String, String> usuarios = new HashMap<>();
  private String pantalla = "";
  private String ultimoMensajeError = "";
  private boolean accesoConcedido = false;
  private int accesosExitosos = 0;

  @Given("el sistema tiene usuarios registrados")
  public void el_sistema_tiene_usuarios_registrados() {
    usuarios.put("user@empresa.com", "Correcta123");
  }

  @When("intento iniciar sesión con email {string} y contraseña {string}")
  public void intento_iniciar_sesion_con_email_y_contraseña(String email, String password) {
    // Reglas simples
    if (!email.contains("@")) {
      ultimoMensajeError = "Formato de email inválido";
      accesoConcedido = false;
      pantalla = "login";
      return;
    }
    if (!usuarios.containsKey(email)) {
      ultimoMensajeError = "Usuario no registrado";
      accesoConcedido = false;
      pantalla = "login";
      return;
    }
    if (!Objects.equals(usuarios.get(email), password)) {
      ultimoMensajeError = "Credenciales inválidas";
      accesoConcedido = false;
      pantalla = "login";
      return;
    }
    // Éxito
    accesoConcedido = true;
    pantalla = "dashboard";
    accesosExitosos++;
  }

  @Then("debo ver el {string}")
  public void debo_ver_la_pantalla(String esperada) {
    assertEquals(esperada, pantalla);
  }

  @Then("el sistema registra un acceso exitoso")
  public void el_sistema_registra_un_acceso_exitoso() {
    assertTrue(accesosExitosos > 0);
  }

  @Then("debo ver el mensaje de error {string}")
  public void debo_ver_el_mensaje_de_error(String mensaje) {
    assertEquals(mensaje, ultimoMensajeError);
  }

  @Then("el acceso no debe concederse")
  public void el_acceso_no_debe_concederse() {
    assertFalse(accesoConcedido);
  }
}
