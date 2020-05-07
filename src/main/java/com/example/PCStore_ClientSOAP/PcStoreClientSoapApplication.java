package com.example.PCStore_ClientSOAP;

import com.example.PCStore_ClientSOAP.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class PcStoreClientSoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcStoreClientSoapApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(ProductClient quoteClient) {
		return args -> {
			while(true)
			{
				System.out.println("----------ВЫБЕРИТЕ ОПЦИЮ----------");
				System.out.println("1. Поиск товара");
				System.out.println("2. Добавить товар");
				System.out.println("3. Редактировать товар");
				System.out.println("4. Удалить товар");
				System.out.println("5. Показать все товары");
				System.out.println("6. Выход из программы");
				System.out.println("---------------------------------");

				BufferedReader readerMenu = new BufferedReader(new InputStreamReader(System.in));

				String Button = readerMenu.readLine();
				int ButtonOption = Integer.parseInt(Button);

				Thread.sleep(100);
				switch (ButtonOption) {
					case 1:
						System.out.println("-----------------");
						System.out.println("Введите товар");
						System.out.println("-----------------");

						BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
						String country = reader.readLine();

						if (args.length > 0) {
							country = args[0];
						}

						GetProductResponse response = quoteClient.getProduct(country);

						if(response.getProduct() == null)
						{
							System.err.println("Такого товара не существует");
						}

						else {
							System.err.println("ID: " + response.getProduct().getIdProduct());
							System.err.println("Наименование товара: " + response.getProduct().getName());
							System.err.println("Цена: " + response.getProduct().getPrice());
							}
						break;
					case 2:
						System.out.println("Добавление товара");
						BufferedReader newreader = new BufferedReader(new InputStreamReader(System.in));
						System.out.println("ID");
						String idProduct = newreader.readLine();
						int newIdProduct = Integer.parseInt(idProduct);
						System.out.println("Наименование товара");
						String nameProduct = newreader.readLine();
						System.out.println("Цена");
						String priceProduct = newreader.readLine();
						int newPriceProduct = Integer.parseInt(priceProduct);

						AddProductResponse addProductResponse = quoteClient.setProduct(newIdProduct,nameProduct,newPriceProduct);
						System.err.println("ID: " + addProductResponse.getIdProduct());
						System.err.println("Наименование: " + addProductResponse.getName());
						System.err.println("Цена: " + addProductResponse.getPrice());
						break;

					case 3:
						System.out.println("Введите наименование товара");
						BufferedReader editreader = new BufferedReader(new InputStreamReader(System.in));
						String oldProduct = editreader.readLine();

						GetProductResponse getProductResponse = quoteClient.getProduct(oldProduct);

						if(getProductResponse.getProduct() != null) {
							System.out.println("ТОВАР НАЙДЕН");

							BufferedReader editreader1 = new BufferedReader(new InputStreamReader(System.in));
							System.out.println("Наименование товара");
							String nameProduct1 = editreader1.readLine();
							System.out.println("Цена");
							String priceProduct1 = editreader1.readLine();
							int newPriceProduct1 = Integer.parseInt(priceProduct1);


							EditProductResponse editProductResponse = quoteClient.editProduct(oldProduct,nameProduct1, newPriceProduct1);
							System.out.println(editProductResponse.getIdProduct());
							System.out.println(editProductResponse.getName());
							System.out.println(editProductResponse.getPrice());

						}

						else {System.err.println("Товара не существует");}


						break;

					case 4:
						System.out.println("Введите ID-товара");
						BufferedReader deletereader = new BufferedReader(new InputStreamReader(System.in));
						String deletedIdProduct = deletereader.readLine();
						int newdeletedIdProduct = Integer.parseInt(deletedIdProduct);

						DeleteProductResponse deleteresponse = quoteClient.deleteProduct(newdeletedIdProduct);

						System.err.println(deleteresponse.getResponseMessage());

						break;

					case 5:
						GetALLProductsResponse getALLProductsResponse = quoteClient.getAllProducts("");
						for (int i=0; i<getALLProductsResponse.getAllProducts().size(); i++)
						{
							System.err.println(getALLProductsResponse.getAllProducts().get(i).getName());
						}
						break;
					case 6:
						System.out.println("Выходим из программы...");
						System.exit(0);
				}
			}
		};
	}
}
