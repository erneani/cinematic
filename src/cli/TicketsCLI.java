package src.cli;

import src.entities.CLI;
import src.entities.Session;
import src.entities.Theatre;
import src.errors.ErrorHandler;
import src.interfaces.Runnable;
import src.repositories.CinemaRepository;
import src.repositories.SessionRepository;
import src.repositories.TicketRepositories;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class TicketsCLI extends CLI implements Runnable {
    public TicketsCLI() {
        super(new ArrayList<>());

        List<String> choicesList = super.getChoicesList();
        choicesList.add("Listar todos os cinemas.");
        choicesList.add("Listar todas as sessões de um cinema.");
        choicesList.add("Comprar ingresso para uma sessão.");
        choicesList.add("Encerrar sessão.");
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
            case 1 -> listAllCinemas();
            case 2 -> listAllCinemaSessions();
            case 3 -> buyTicketForSection();
            case 4 -> super.finishCli("Encerrando sessão de usuário.");
            default -> ErrorHandler.handleWarning("Opção inválida inserida.");
        }
    }

    private void listAllCinemas() {
        super.displaySectionTitle("Lista de cinemas");

        List<Theatre> cinemaList = CinemaRepository.getAllCinemas();

        if (cinemaList != null && cinemaList.size() > 0) {
            super.displayCinema(cinemaList);
        } else {
            System.out.println("| Não há cinemas para exibir no momento.");
        }
    }

    private void listAllCinemaSessions() {
        super.displaySectionTitle("Sessões por Cinema");

        String cinemaId = super.scanUserInput("Qual o ID do cinema que você quer checar as sessões?");

        List<Session> sessionsList = SessionRepository.getSessionsByCinema(parseInt(cinemaId));

        if (sessionsList != null && sessionsList.size() > 0) {
            super.displaySession(sessionsList);
        } else {
            System.out.println("| Não há sessões nesse cinema no momento.");
        }
    }

    private void buyTicketForSection() {
        super.displaySectionTitle("Comprar ingressos para sessão.");

        String sessionId = super.scanUserInput("Qual o ID da sessão que você quer comprar?");
        Session session = SessionRepository.getSessionById(parseInt(sessionId));

        if (session == null) {
            System.out.println("Não há nenhuma sessão com esse ID.");
            return;
        }

        System.out.println("### Essa é a sessão que você procura? ###");
        super.displaySession(session);
        System.out.println("| R$" + session.getValue());

        String interestedSession = super.scanUserInput("É essa sessão que você procura? (Digite S/sim - Qualquer texto/não)");
        boolean isSession = interestedSession.equalsIgnoreCase("s");

        if (!isSession) {
            System.out.println("Direcionando você para a tela de opções.");
            return;
        }
        String ticketsQuantity = super.scanUserInput("Quantos ingressos você deseja comprar?");
        this.selectSeatsForTickets(parseInt(ticketsQuantity), session.getId());
    }

    private void selectSeatsForTickets(int quantity, int sessionId) {
        char[] letters = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        List<String> selectedChairs = new ArrayList<>();
        listChairs(sessionId);

        for (int i = 1; i <= quantity; i++) {
            String selectedChair = super.scanUserInput("Qual cadeira você deseja comprar para o Ticket " + i + "? (ex. D4)");

            if (selectedChair.length() != 2) {
                System.out.println("Cadeira inválida");
                return;
            }

            selectedChairs.add(selectedChair);
        }

        if (TicketRepositories.reserveSeats(selectedChairs, sessionId)) {
            System.out.println("Cadeiras reservadas com sucesso!");
        } else {
            System.out.println("Não foi possível reservar as cadeiras. Por favor, tente novamente.");
        }
    }

    private void listChairs(int sessionId) {
        char[] letters = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        List<String> reservedSeats = TicketRepositories.getReservedSeatsBySession(sessionId);

        System.out.println("################# TELA #################");
        System.out.print("   ");
        for (int i = 1; i <= 15; i++) {
            System.out.print(letters[i - 1] + " ");
        }

        System.out.println();

        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= 15; j++) {
                String chairId = letters[j] + "" + i;

                if (reservedSeats!= null && reservedSeats.contains(chairId)) {
                    System.out.print("|X");
                } else {
                    System.out.print("|O");
                }
            }
            System.out.print("|");
            System.out.println();
        }

        System.out.println();
        System.out.println("|O| -> Cadeiras vazias");
        System.out.println("|X| -> Cadeiras já reservadas");
    }
}
