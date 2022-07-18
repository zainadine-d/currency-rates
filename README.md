# currency-rates

Currency-rates is an API that provides exchange rates for diferrent currencys. With this API you'll have access to a list of different currencies as well as the exchange rates for the current date.

Clone this repository into your local machine and on the root folder of this project run the command "mvn clean install". This command will build the project for you. Next you should navegate to the "target" folder inside your root project folder using the command "cd target". To run the project execute the command "java -jar currency-rates-0.0.1-SNAPSHOT.jar".

Available end-points:

-List currencies:
http://localhost:8080/currencies

-List rates for all available currencies:
http://localhost:8080/rates

-List rates for specified currency:
http://localhost:8080/rates/MZN , where MZN is an example of available currency

-List rate for two different currencies (base, target):
http://localhost:8080/target/USD/MZN
