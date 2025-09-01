package stepdefs;

import com.empresa.app.AuthService;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private final AuthService auth = new AuthService();
    private Boolean loginResult;

    @Given("existe un usuario {string} con contraseña {string}")
    public void existe_un_usuario_con_contraseña(String user, String pass) {
        auth.register(user.trim(), pass);
    }

    @When("intento iniciar sesión con usuario {string} y contraseña {string}")
    public void intento_iniciar_sesion(String user, String pass) {
        loginResult = auth.login(user.trim(), pass);
    }

    @Then("el resultado del inicio de sesión debe ser {string}")
    public void el_resultado_debe_ser(String esperado) {
        if ("exitoso".equalsIgnoreCase(esperado)) {
            assertTrue(loginResult, "Se esperaba login exitoso");
        } else if ("fallido".equalsIgnoreCase(esperado)) {
            assertFalse(loginResult, "Se esperaba login fallido");
        } else {
            fail("Valor esperado desconocido: " + esperado);
        }
    }
}