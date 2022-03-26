package Controller;

public class Film {
    private BigController bigController;
    private Menu menu;
    private View.Film film_view;
    Cinema.Film film;
    public Film(BigController co){
        this.bigController=co;
        film= new Cinema.Film();
        film_view= new View.Film(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(film_view);
    }
}
