Opis
Opis formalny
Aplikacja do zarządzania swoją biżuterią. Główny cel - baza biżuterii w celu przeciwdziałania odsprzedaży czyjejś biżuterii/pomoc w kontakcie między znalazcą, a posiadaczem danej biżuterii.
Opis funkcjonalny
•	Możliwość rejestracji/logowania
•	Możliwość zgłoszenia sklepu
•	Możliwość dodania do bazy biżuterii przez użytkownika
•	Możliwość usunięcia z bazy biżuterii
•	Dodanie zdjęcia biżuterii
•	Możliwość określenia kategorii oraz kruszców biżuterii
Oprogramowanie
•	Spring Boot
•	Spring Security
•	Spring MVC
•	JPA
•	Thymeleaf
•	MySQL 
•	Bootstrap - prezentacja interfejsu użytkownika
•	Maven - zarządzanie zależnościami
•	Eclipse - środowisko developerskie
•	Java 8
•	Packaging (JAR) 
 
Stwórzmy projekt
Aby nie tworzyć każdego pliku z osobna polecam przekopiować pliki źródłowe z repozytorium GitHub: https://github.com/gszast/ss. 
Aby stworzyć nowy projekt
1.	Przejdź na stronę: https://start.spring.io/
2.	Następnie dodaj zależności Web, JPA, Security, MySQL, and Thymeleaf.
 
Naciśnij przycisk “Generate Project” - zostanie pobrany archiwum .zip z naszym projektem Maven’a.
Import projektu do Eclipse
1.	Wypakuj pliki.
2.	Z menu podręcznego Eclipse wybierz “File/Import/Existing Maven Project”
3.	Wybierz folder główny projektu ( z plikiem “pom.xml” ), kilknij “Finish”
  
Zależności
Teraz zajmiemy się dodaniem do pliku “pom.xml”, bibliotek:
•	Konektor bazy danych mySql
•	NekoHtml ( wsparcie HTML5)
•	Guava - zestaw narzędzi od Google
•	Kompiler HTML Thymeleaf
  	 <dependency>
  		 <groupId>mysql</groupId>
  		 <artifactId>mysql-connector-java</artifactId>
  		 <scope>runtime</scope>
  	 </dependency>

  	 <dependency>
  		 <groupId>net.sourceforge.nekohtml</groupId>
  		 <artifactId>nekohtml</artifactId>
  		 <version>1.9.21</version>
  	 </dependency>
  	 <dependency>
  		 <groupId>com.google.guava</groupId>
  		 <artifactId>guava</artifactId>
  	 </dependency>
  	 <dependency>
  		 <groupId>org.springframework.boot</groupId>
  		 <artifactId>spring-boot-starter-thymeleaf</artifactId>
  	 </dependency>
Modele
Struktura bazy danych zostanie automatycznie wygenerowana jeżeli nie zostaną odnalezione wymagane tabele. Dostępna jest również w pliku graficznym: „db_schema.png” oraz pliku „jguard.sql”
Pliki konfiguracyjne
WebMvcConfig.java
Ta klasa zawiera dekoder haseł który wstrzykniemy w warstwie Service.

SecurityConfiguration.java
W tej klasie implementujemy ograniczenia dostępu do zasobów. 
Zasoby: 
„/” „/login” „/registration”  - dostępne dla wszystkich
„/admin” – tylko dla użytkownika z rolą „ADMIN”
„/**” (reszta zasobów ) – wymagana jest rola „USER”
Application.yaml
W tym pliku konfigurujemy dostęp do bazy danych, użytkownika, hasło oraz automatyczne generowanie schematu encji bazy danych jeśli nie istnieją.
