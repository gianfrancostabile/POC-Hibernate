package com.hibernate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.hibernate.city.CityConverter;
import com.hibernate.city.CityDAO;
import com.hibernate.country.CountryConverter;
import com.hibernate.country.CountryDAO;
import com.hibernate.country.CountryDTO;
import com.hibernate.person.PersonConverter;
import com.hibernate.person.PersonDAO;
import com.hibernate.state.StateConverter;
import com.hibernate.state.StateDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Iterator;

/**
 * Hello world!
 */
public class App {

   static Logger logger = Logger.getLogger(App.class);

   public static void main(String[] args) {
      BasicConfigurator.configure();
      // TODO : Make tests v1.0.5
      CountryDAO countryDAO = new CountryDAO();
      StateDAO stateDAO = new StateDAO();
      CityDAO cityDAO = new CityDAO();
      PersonDAO personDAO = new PersonDAO();

      CountryConverter countryConverter = new CountryConverter();
      StateConverter stateConverter = new StateConverter();
      CityConverter cityConverter = new CityConverter();
      PersonConverter personConverter = new PersonConverter();

      String rootPath = new File("").getAbsolutePath(),
         jsonPath = rootPath.concat("\\src\\main\\resources\\json\\"),
         countriesPath = jsonPath.concat("countries.json"),
         statesPath = jsonPath.concat("states.json");
      logger.info(countriesPath);

      JsonParser jsonParser = new JsonParser();
      try (JsonReader jsonReader = new JsonReader(new FileReader(countriesPath))) {
         JsonElement jsonElement = jsonParser.parse(jsonReader);
         if (jsonElement.isJsonArray()) {
            JsonArray jsonCountries = jsonElement.getAsJsonArray();
            Iterator<JsonElement> iteratorCountries = jsonCountries.iterator();

            countryDAO.setManuallySession(true);
            countryDAO.beginSession();
            countryDAO.beginTransaction();
            while (iteratorCountries.hasNext()) {
               JsonObject countryJson = iteratorCountries.next().getAsJsonObject();
               CountryDTO countryDTO = new CountryDTO();
               countryDTO.setCODE(countryJson.get("code").getAsString())
                  .setName(countryJson.get("name").getAsString());
               countryDAO.insertNonTransactional(countryDTO);
            }
            countryDAO.setManuallySession(false);
            countryDAO.commitTransaction();
            countryDAO.killSession();
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Deprecated
   public void testBefore_V104() {
      /*CountryDAO daoCountry = new CountryDAO();
      PersonDAO daoPerson = new PersonDAO();

      PersonConverter personConverter = new PersonConverter();
      CountryConverter countryConverter = new CountryConverter();

      Country country = new Country();
      CountryDTO countryDTO;
      Person person = new Person();
      PersonDTO personDTO;

      country.setName("Argentine");
      countryDTO = countryConverter.toDTO(country);

      Long idCountryInserted = daoCountry.insertTransactional(countryDTO);
      logger.info("Country inserted with ID - ".concat(idCountryInserted.toString()));
      countryDTO = daoCountry.retrieve(idCountryInserted);

      country = countryConverter.toPOJO(countryDTO);
      logger.info("Country Argentina retrieve: ".concat(country.toString()));

      person.setName("Gian Franco")
         .setAge(21)
         .setBirthday(new Date(890362800))
         .setEmail("gianfranco.stabile98@gmail.com")
         .setPhone("+542231111111")
         .setCountry(country);

      personDTO = personConverter.toDTO(person);
      personDTO.getCountry().setID(countryDTO.getID());

      Long idInserted = daoPerson.insertTransactional(personDTO);
      logger.info("Person inserted with ID - ".concat(idInserted.toString()));
      personDTO = daoPerson.retrieve(idInserted);

      Person personRetrieved = personConverter.toPOJO(personDTO);
      logger.info("Person I retrieved: ".concat(personRetrieved.toString()));

      countryDTO.setName("Brazil");
      daoCountry.updateTransactional(countryDTO);
      countryDTO = daoCountry.retrieve(countryDTO.getID());
      country = countryConverter.toPOJO(countryDTO);
      logger.info("Country Brazil retrieved: ".concat(country.toString()));

      personDTO.setName("Lionel Messi")
         .setAge(31)
         .setBirthday(new Date(551502000))
         .setEmail("lionelmessi@gmail.com")
         .setPhone("999999999");
      daoPerson.updateTransactional(personDTO);
      personDTO = daoPerson.retrieve(personDTO.getID());
      person = personConverter.toPOJO(personDTO);
      logger.info("Person Messi retrieved: ".concat(person.toString()));

      daoPerson.deleteByIdTransactional(personDTO.getID());
      logger.info("Messi was deleted.");
      daoCountry.deleteByIdTransactional(countryDTO.getID());
      logger.info("Brazil was deleted.");*/
   }
}
