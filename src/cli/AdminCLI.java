package src.cli;

import src.entities.*;
import src.interfaces.Runnable;
import src.repositories.CinemaRepository;
import src.repositories.MovieRepository;
import src.repositories.SessionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AdminCLI extends CLI implements Runnable {
    public AdminCLI(Person user) {
        super(new ArrayList<>());

        List<String> choicesList = super.getChoicesList();
        choicesList.add("Listar todos os cinemas.");
        choicesList.add("Criar novo cinema.");
        choicesList.add("Deletar cinema.");
        choicesList.add("Listar todas as sessões de um cinema.");
        choicesList.add("Criar uma sessão.");
        choicesList.add("Remover uma sessão.");
        choicesList.add("Listar todos os filmes na galeria.");
        choicesList.add("Adicionar um filme à galeria.");
        choicesList.add("Remove um filme da galeria.");
        choicesList.add("Finalizar sessão como administrador.");
        super.setChoicesList(choicesList);

        System.out.println("Olá " + user.getName() + ".");
    }

    public void runCli() {
        while(!super.isFinished()) {
            super.displayOptions();
        }
    }

    @Override
    protected void handleOption(int selectedOption) {
        switch (selectedOption) {
            case 1 -> listAllTheatres();
            case 2 -> createTheatre();
            case 3 -> deleteTheatre();
            case 4 -> listAllSessions();
            case 5 -> createSession();
            case 6 -> deleteSession();
            case 7 -> listAllMovies();
            case 8 -> createMovie();
            case 9 -> deleteMovie();
            case 10 -> finishCli("Encerrando sessão de administrador.");
            default -> System.out.println("Sua opção foi " + selectedOption);
        }
    }

    private void listAllTheatres() {
        List<Theatre> cinemaList = CinemaRepository.getAllCinemas();

        if (cinemaList != null && cinemaList.size() > 0) {
            super.displayCinema(cinemaList);
        } else {
            System.out.println("Não há cinemas para serem exibidos no momento.");
        }
    }

    private void createTheatre() {
        super.displaySectionTitle("Criar Cinema");

        String theatreName = super.scanUserInput("Qual o nome do Cinema?");
        String theatreLocation = super.scanUserInput("Onde o cinema fica localizado?");

        Theatre cinema = new Theatre(
            theatreName,
            theatreLocation,
            0
        );

        if (CinemaRepository.createCinema(cinema)) {
            System.out.println("Cinema criado com sucesso!");
        } else {
            System.out.println("Não foi possível criar o cinema. Tente novamente.");
        }
    }

    private void deleteTheatre() {
        super.displaySectionTitle("Deletar Cinema");

        String theatreId = super.scanUserInput("Qual o ID do Cinema?");

        if(CinemaRepository.deleteCinema(parseInt(theatreId))) {
            System.out.println("Cinema deletado com sucesso.");
        } else {
            System.out.println("Houve um erro ao deletar o cinema. Por favor, tente novamente.");
        }
    }

    private void listAllSessions() {
        super.displaySectionTitle("Lista de Sessões por Cinema");

        String cinemaId = super.scanUserInput("Qual o ID do Cinema que você quer consultar as Sessões?");
        List<Session> sessionList = SessionRepository.getSessionsByCinema(parseInt(cinemaId));

        if (sessionList != null && sessionList.size() > 0) {
            System.out.println("Sessões disponíveis no cinema");
            System.out.println();
            super.displaySession(sessionList);
        } else {
            System.out.println("Não há sessões disponíveis nesse cinema");
        }
    }

    private void createSession() {
        super.displaySectionTitle("Criar Sessão");

        String movieId = super.scanUserInput("Qual o ID do filme dessa sessão?");
        String theatreId = scanUserInput("Qual o ID do Cinema dessa sessão?");
        String rawDate = super.scanUserInput("Qual a data da sessão? (ex. 2018-05-05T11:50:55)");
        String value = super.scanUserInput("Qual o valor da sessão em R$?");

        LocalDateTime date = LocalDateTime.parse(rawDate);

        Session session = new Session(
            date,
            parseDouble(value),
            1,
            parseInt(movieId),
            parseInt(theatreId)
        );

        if (SessionRepository.createSession(session)) {
            System.out.println("Sessão criada com sucesso.");
        } else {
            System.out.println("Não foi possível adicionar a sessão ao cinema. Por favor, tente novamente.");
        }
    }

    private void deleteSession() {
        super.displaySectionTitle("Remover Sessão");

        String sessionId = super.scanUserInput("Qual o ID da Sessão?");

        if (SessionRepository.deleteSession(parseInt(sessionId))) {
            System.out.println("Sessão removida do cinema.");
        } else {
            System.out.println("Não foi possível remover a Sessão. Tente novamente.");
        }
    }

    private void listAllMovies() {
        super.displaySectionTitle("Galeria de Filmes");

        List<Movie> movieList = MovieRepository.getAllMovies();

        if (movieList != null && movieList.size() > 0) {
            super.displayMovie(movieList);
        } else {
            System.out.println("Não há filmes na galeria no momento.");
        }
    }

    private void createMovie() {
        super.displaySectionTitle("Adicionar filme à Galeria");

        String title = super.scanUserInput("Qual o título do filme?");
        String releaseDate = super.scanUserInput("Qual a data de lançamento? (ex. 2018-05-05T11:50:55)");
        String lore = super.scanUserInput("Qual o resumo do filme?");

        Movie movie = new Movie(
                LocalDateTime.parse(releaseDate),
                title,
                lore,
                0
        );

        if (MovieRepository.createMovie(movie)) {
            System.out.println("Filme adicionado à Galeria de filmes");
        } else {
            System.out.println("Não foi possível adicionar o filme. Tente novamente.");
        }
    }

    private void deleteMovie() {
        super.displaySectionTitle("Remover filme da Galeria");

        String movieId = super.scanUserInput("Qual o ID do filme?");

        if (MovieRepository.deleteMovie(parseInt(movieId))) {
            System.out.println("O filme foi removido da Galeria.");
        } else {
            System.out.println("Não foi possúvel remover o filme. Por favor, tente novamente.");
        }
    }
}
