package src.entities;

import src.repositories.MovieRepository;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public abstract class CLI {
    private boolean finished;
    private List<String> choicesList;

    public CLI(List<String> choicesList) {
        this.finished = false;
        this.choicesList = choicesList;
    }

    protected void displayOptions() {
        this.displaySectionTitle("Selecione uma opção");
        this.displaySectionItems();
        this.displayPointer();
        String userInput = this.scanInput();

        try {
            validateOption(userInput);
            this.handleOption(parseInt(userInput));
        } catch (Exception exception) {
            System.out.println("* Ocorreu um problema: ");
            System.out.println("* " + exception);
        }
    }

    protected void handleOption(int selectedOption) {}

    protected String scanUserInput(String question) {
        System.out.println(question);
        return this.scanInput();
    }

    protected void displaySectionTitle(String title) {
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("| " + title.toUpperCase());
        System.out.println("--------------------------------");
    }

    private void validateOption(String selectedValue) throws Exception {
        if (!selectedValue.matches("[0-9]+")) {
            throw new NumberFormatException("É necessário inserir um número entre 1 e " + this.choicesList.size() + ".");
        }

        int convertedValue = parseInt(selectedValue);
        if (convertedValue > this.choicesList.size() || convertedValue < 1) {
            throw new Exception("Opção selecionada é inválida. Insira um valor entre 1 e " + this.choicesList.size() + ".");
        }
    }

    private void displaySectionItems() {
        int choiceIndex = 1;

        for(String choice : this.choicesList) {
            System.out.println(choiceIndex + " - " + choice);
            choiceIndex++;
        }
    }

    private void displayPointer() {
        System.out.println("> ");
    }

    private String scanInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<String> getChoicesList() {
        return choicesList;
    }

    public void setChoicesList(List<String> choicesList) {
        this.choicesList = choicesList;
    }

    public void finishCli(String message) {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("X " + message);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        this.setFinished(true);
    }

    public void displayCinema(Theatre cinema) {
        System.out.println("| -----------------------------------------------");
        System.out.println("| #" + cinema.getId() + " " + cinema.getName());
        System.out.println("| Localizado no endereço " + cinema.getLocation());
    }

    public void displayCinema(List<Theatre> cinemaList) {
        for (Theatre cinema : cinemaList) {
            this.displayCinema(cinema);
        }
    }

    public void displaySession(Session session) {
        Movie sessionMovie = MovieRepository.getMovieById(session.getMovieId());

        String parsedDate = "dia " + session.getDate().getDayOfMonth() + "/" + session.getDate().getMonth();
        String parsedTime = " às " + session.getDate().getHour() + "h" + session.getDate().getMinute() + "min";
        String fullDate = parsedDate + parsedTime;

        System.out.println("#" + session.getId() + " - " + sessionMovie.getTitle() + " " + fullDate);
    }

    public void displaySession(List<Session> sessionList) {
        for (Session session : sessionList) {
            this.displaySession(session);
        }
    }

    public void displayMovie(Movie movie) {
        String parsedReleaseDate = movie.getReleaseDate().getDayOfMonth() + "/" + movie.getReleaseDate().getMonth();

        System.out.println("|--------------------------------------");
        System.out.println("| #" + movie.getId() + " " + movie.getTitle());
        System.out.println("| Lançamento dia " + parsedReleaseDate);
        System.out.println("| Sobre o filme: ");
        System.out.println(movie.getLore());
    }

    public void displayMovie(List<Movie> movieList) {
        for (Movie movie : movieList) {
            this.displayMovie(movie);
        }
    }
}
