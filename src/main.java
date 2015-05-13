import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class main {
	
	//on créé les sections en premier
	static Section sectionA10 = new Section("A10","Esch","Luxembourg",3);
	static Section sectionA11 = new Section("A11","Esch","Rodange",2);
	static Section sectionA12 = new Section("A12","Wiltz","Luxembourg",2);
	static Section sectionA13 = new Section("A13","Luxembourg","Metz",6);
	static Section sectionA14 = new Section("A14","Luxembourg","Trier",4);
	static Section sectionA15 = new Section("A15","Luxembourg","Arlon",5);
	

	public static Collection<Enregistrement> enregistrements = new ArrayList<Enregistrement>();
	
	private static <E, T> void process(Iterable<E> elements, Predicate<E> predicate, Function<E, T> mapper, Consumer<T> block) {
		for (E e : elements) {
			if (predicate.test(e)) {
				T value = mapper.apply(e);
				
				block.accept(value);
			}
		}
	}
	
	private static int getPrice(Car car){
		//il faut récupérer t pour ainsi faire section.getPrice
		process(enregistrements, e -> e.getPlate().contains(car.getPlate()), e -> e.getSection(), t -> System.out.println(t));
		
		return sectionA12.getPrice();
	}

	public static void main(String[] args) {
		Enregistrement car1 = new Enregistrement("757 ASP 57","A12", new Date(101, 0, 1));
		Car vehicle1 = new Car("757 ASP 57","car");
		Enregistrement car2 = new Enregistrement("347 JDD 54","A13", new Date(100, 6, 1));
		Enregistrement car3 = new Enregistrement("872 DOP 55","A12", new Date(103, 11, 15));
		Enregistrement car4 = new Enregistrement("984 YIO 75","A12", new Date(115, 4, 11));
		
		enregistrements.add(car1);
		enregistrements.add(car2);
		enregistrements.add(car3);
		enregistrements.add(car4);
		
		System.out.println("Tout les enregistrements:");
		process(enregistrements, e -> true, e -> e, e -> System.out.println(e));
		
		System.out.println();
		
		System.out.println("Les plaques des voitures circulants sur la -A12-:");
		process(enregistrements, e -> e.getSection().contains("A12"), e -> e.getPlate(), t -> System.out.println(t));

		System.out.println();
		
		System.out.println("Les sections utilisées par la voiture -757 ASP 57-:");
		process(enregistrements, e -> e.getPlate().contains("757 ASP 57"), e -> e.getSection(), t -> System.out.println(t));

		System.out.println();
		
		System.out.println("Les voitures circulant le 12 Mai 2015:");
		process(enregistrements, e -> e.getDate().compareTo(new Date(115, 4, 11))==0, e -> e.getPlate(), t -> System.out.println(t));
		
		System.out.println();
		System.out.println("Test pour créer un enregistrement automatiquement");
		Random rand = new Random();
		
		//il faut un random pour les plaques donc : un numéro de 100 à 999, trois fois un caractère de A à Z et 
		// un numéro entre 1 et 99
		int firstPlateNumber = rand.nextInt(999 - 100 + 1) + 100;
		char [] arrayLetter = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		int indiceLetter = rand.nextInt(arrayLetter.length);
		char firstLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		char secondLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		char thirdLetter = arrayLetter[indiceLetter];
		int secondPlateNumber = rand.nextInt(99 - 1 + 1) + 1;
		
		//il faut une sections aléatoire parmis les valeurs du tableau ci-dessous
		String [] lesSections = {"A10","A11","A12","A13","A14","A15"};
		int indiceSections = rand.nextInt(lesSections.length);
		String theSection = lesSections[indiceSections];
		
		//il faut une date aléatoire donc : un numéro de 100 à 115, un numéro de 0 à 11 et un numéro de 0 à 30
		int year = rand.nextInt(115 - 100 + 1) + 100;
		int month = rand.nextInt(11 - 0 + 1) + 0;
		int day = rand.nextInt(30 - 0 + 1) + 0;
		
		//ce qui nous donne une plaque, une section et une date donc un enregistrement. 
		Enregistrement car5 = new Enregistrement(firstPlateNumber+" "+firstLetter+secondLetter+thirdLetter+" "+secondPlateNumber,theSection, new Date(year, month, day));
		
		enregistrements.add(car5);
		
		System.out.println();
		System.out.println("Tout les enregistrements:");
		process(enregistrements, e -> true, e -> e, e -> System.out.println(e));
		
		System.out.println("------------");
		int price = getPrice(vehicle1);
		System.out.println(price);
	}

}