package fr.codenames.projection;

public class Views {
	public static class Common { }
	public static class Plateau extends Common { }
	public static class PlateauEspion extends Plateau { }
	public static class PlateauJoueur extends Plateau { }
	public static class Carte extends Common { }
	public static class Partie extends Common { }
	public static class Grille extends Common { }
	public static class Case extends Common { }
	public static class CaseReponse extends Case { }
	public static class Message extends Common { }
}