import login.models.Login;
import login.useCases.LoginUseCase;

public class AppLogin {
    public static void main(String[] args) {
        System.out.println("=====  Sistema de Notas Evalix - Gestión de Logins =====\n");

        try {
            LoginUseCase loginUseCase = new LoginUseCase();

            // === CREAR LOGINS ===
            System.out.println("== CREAR LOGINS ==");
            System.out.println(loginUseCase.create(new Login("cliente1", "cliente1@mail.com", "1234!")));
            System.out.println(loginUseCase.create(new Login("cliente2", "cliente2@mail.com", "1234!")));
            System.out.println();

            // === CONSULTAR TODOS ===
            System.out.println("==  LISTA DE LOGINS ==");
            System.out.println(loginUseCase.all());
            System.out.println();

            // === BUSCAR POR ÍNDICE ===
            System.out.println("==  BUSCAR POR ÍNDICE ==");
            System.out.println(loginUseCase.findByIndex(1));
            System.out.println();

            // === ACTUALIZAR ===
            System.out.println("==  ACTUALIZAR LOGIN ==");
            System.out.println(loginUseCase.update(0, new Login("Cliente1Mod", "nuevo1@mail.com", "9999")));
            System.out.println(loginUseCase.all());
            System.out.println();

            // === ELIMINAR ===
            System.out.println("==  ELIMINAR LOGIN ==");
            System.out.println(loginUseCase.delete(1));
            System.out.println(loginUseCase.all());
            System.out.println();

            System.out.println(" Proceso completado correctamente.");

        } catch (Exception e) {
            System.out.println(" Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }
}
