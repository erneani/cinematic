package src.cli;

import src.entities.CLI;
import src.entities.Person;
import src.errors.ErrorHandler;
import src.interfaces.Runnable;
import src.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class LoginCLI extends CLI implements Runnable {
    public LoginCLI() {
        super(new ArrayList<>());

        List<String> choicesList = new ArrayList<>();
        choicesList.add("Fazer Login");
        choicesList.add("Criar novo usuário");
        choicesList.add("Finalizar programa");
        super.setChoicesList(choicesList);
    }
    public void runCli() {
        while(!super.isFinished()) {
            super.displayOptions();
        }
    }

    @Override
    protected void handleOption(int selectedOption) {
        switch (selectedOption) {
            case 1 -> loginUser();
            case 2 -> createUser();
            case 3-> super.finishCli("Encerrando a aplicação.");
            default -> ErrorHandler.handleWarning("Opção inválida inserida.");
        }
    }

    private void loginUser() {
        super.displaySectionTitle("Login");

        String username = super.scanUserInput("Nome de usuário: ");
        String pin = super.scanUserInput("PIN: ");

        if (UsersRepository.checkUserAndPin(username, pin)) {
            Person user = UsersRepository.getUserByUsername(username);

            if (user != null && user.getRole() == 1) {
                AdminCLI adminCLI = new AdminCLI(user);
                adminCLI.runCli();
            } else {
                TicketsCLI ticketsCLI = new TicketsCLI();
                ticketsCLI.runCli();
            }
        } else {
            System.out.println("Não é possível entrar na plataforma com as credenciais utilizadas.");
        }
    }

    private void createUser() {
        super.displaySectionTitle("CRIAR USUÁRIO");
        boolean existsAdminUsers = UsersRepository.checkIfAdminExists();
        boolean admin = false;

        System.out.println("Para criar um novo usuário, por favor providencie as informações abaixo");

        String name = super.scanUserInput("Nome: ");
        String username = super.scanUserInput("Nome de usuário: ");
        String pin = super.scanUserInput("PIN: ");

        if (!existsAdminUsers) {
            String adminText = super.scanUserInput("Esse usuário será administrador? (Digite sim/Qualquer outro texto, não)");
            admin = adminText.equalsIgnoreCase("sim");
        }

        Person person = new Person(
                name,
                username,
                pin,
                admin ? 1 : 2
        );

        if (UsersRepository.insertUser(person)) {
            System.out.println("Usuário criado com sucesso.");
        } else {
            System.out.println("Não foi possível criar o usuário. Tente novamente.");
        }
    }
}
