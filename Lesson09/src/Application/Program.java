package Application;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.println("|------------------------------|");
		System.out.println("|                              |");
		System.out.println("|          NordesTeam          |");
		System.out.println("|                              |");
		System.out.println("|------------------------------|");
		ProductDao productDao = DaoFactory.createProductDao();
		showMenu();
		int option = sc.nextInt();

		do {
			switch (option) {
			case 0:
				System.out.println("END OF PROGRAM!");
				break;
			case 1:
				System.out.println("You're in INSERT selection ");
				System.out.print("Enter the name of product : ");
				sc.nextLine();
				String nameProduct = sc.nextLine();
				System.out.print("Enter the description of product : ");
				String descriptionProduct = sc.nextLine();
				System.out.print("Enter the discout of product : ");
				double discount = sc.nextDouble();
				Product newProduct = new Product(null, nameProduct, descriptionProduct, discount, new Date());
				productDao.insert(newProduct);
				System.out.println("\nINSERT COMPLETED!");
				System.out.println("Do you want to exit the program or go to menu ? ");
				System.out.println("0-EXIT\n1-MENU");
				int exitOrMenu = sc.nextInt();
				if(exitOrMenu == 0) {
					option = 0;
				} else {
					showMenu();
					option = sc.nextInt();
				}
				break;
			case 2:
				int searchId;
				System.out.println("You're in UPDATE selection");
				System.out.print("Enter the product id that you desire update : ");
				searchId = sc.nextInt();
				Product productUpdate = productDao.findById(searchId);
				System.out.print("Enter the name of product : ");
				sc.nextLine();
				nameProduct = sc.nextLine();
				productUpdate.setName(nameProduct);
				System.out.print("Enter the description of product : ");
				descriptionProduct = sc.nextLine();
				productUpdate.setDescription(descriptionProduct);
				System.out.print("Enter the discout of product : ");
				discount = sc.nextDouble();
				productUpdate.setDiscount(discount);
				productDao.update(productUpdate);
				System.out.println("\nUPDATE COMPLETED! ");
				System.out.println("Do you want to exit the program or go to menu ? ");
				System.out.println("0-EXIT\n1-MENU");
				exitOrMenu = sc.nextInt();
				if(exitOrMenu == 0) {
					option = 0;
				} else {
					showMenu();
					option = sc.nextInt();
				}
				break;

			case 3:
				System.out.println("WARNING!!");
				System.out.println("You're in delete offer, be carefull");
				System.out.println("We recommend you verify the id and go back for this selection");
				System.out.println("Do you want continue or go back to menu and verify the offer id ?");
				System.out.println("0-CONTINUE\n1-MENU");
				int continueOrMenu = sc.nextInt();
				if (continueOrMenu == 0) {
					System.out.println("Enter the id for delete an offer");
					int idDeleteOffer = sc.nextInt();
					productDao.deleteById(idDeleteOffer);
					System.out.println("\nDELETE COMPLETED");
					System.out.println("Do you want to exit the program or go to menu ? ");
					System.out.println("0-EXIT\n1-MENU");
					exitOrMenu = sc.nextInt();
						if(exitOrMenu == 0) {
							option = 0;
						} else {
							showMenu();
							option = sc.nextInt();
						}
				} else {
					showMenu();
					option = sc.nextInt();
				}
				
				break;

			case 4:
				System.out.println("Enter the offer name to list by order id");
				sc.nextLine();
				String productName = sc.nextLine();
				List<Product> listFindByName = productDao.findByName(productName);
				for(Product obj : listFindByName) {
					System.out.println(obj);
				}
				System.out.println("\nDo you want to exit the program or go to menu ? ");
				System.out.println("0-EXIT\n1-MENU");
				exitOrMenu = sc.nextInt();
					if(exitOrMenu == 0) {
						option = 0;
					} else {
						showMenu();
						option = sc.nextInt();
					}

			case 5:
				productDao.findAll();
				List<Product> listFindAll = productDao.findAll();
				for(Product obj : listFindAll) {
					System.out.println(obj);
				}
				System.out.println();
				showMenu();
				option = sc.nextInt();
				break;
			}
		} while (option != 0);
		System.out.println("END OF PROGRAM");

		sc.close();
	}

	public static void showMenu() {
		System.out.println("========== COMPASS SYSTEM ==========");
		System.out.println("Enter the option : ");
		System.out.println("1- to INSERT an offer");
		System.out.println("2- to UPDATE an offer");
		System.out.println("3- to DELETE an offer");
		System.out.println("4- to list the word that found in database");
		System.out.println("5- to SHOW your current table database");
		System.out.println("0- EXIT");
	}

}
