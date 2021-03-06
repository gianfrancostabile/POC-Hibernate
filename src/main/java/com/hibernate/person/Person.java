package com.hibernate.person;

import com.hibernate.abstractions.AbstractPOJO;
import com.hibernate.city.City;

import java.sql.Date;
import java.util.Objects;

public class Person extends AbstractPOJO {
   private long ID;
   private String name;
   private int age;
   private Date birthday;
   private String email;
   private String phone;
   private City city;

   public Person() {

   }

   public long getID() {
      return ID;
   }

   public Person setID(long id) {
      this.ID = id;
      return this;
   }

   public String getName() {
      return name;
   }

   public Person setName(String name) {
      this.name = name;
      return this;
   }

   public int getAge() {
      return age;
   }

   public Person setAge(int age) {
      this.age = age;
      return this;
   }

   public Date getBirthday() {
      return birthday;
   }

   public Person setBirthday(Date birthday) {
      this.birthday = birthday;
      return this;
   }

   public String getEmail() {
      return email;
   }

   public Person setEmail(String email) {
      this.email = email;
      return this;
   }

   public String getPhone() {
      return phone;
   }

   public Person setPhone(String phone) {
      this.phone = phone;
      return this;
   }

   public City getCity() {
      return city;
   }

   public Person setCity(City city) {
      this.city = city;
      return this;
   }

   @Override
   public String toString() {
      return new StringBuilder().append("PersonDTO {")
         .append("name='").append(name).append("'")
         .append(", age=").append(age)
         .append(", birthday=").append(birthday)
         .append(", email='").append(email).append("'")
         .append(", phone='").append(phone).append("'")
         .append(", city=").append(city)
         .append("}").toString();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Person)) return false;
      Person person = (Person) o;
      return getID() == person.getID() &&
         getAge() == person.getAge() &&
         Objects.equals(getName(), person.getName()) &&
         Objects.equals(getBirthday(), person.getBirthday()) &&
         Objects.equals(getEmail(), person.getEmail()) &&
         Objects.equals(getPhone(), person.getPhone()) &&
         Objects.equals(getCity(), person.getCity());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getID(), getName(), getAge(), getBirthday(), getEmail(), getPhone(), getCity());
   }

   @Override
   public AbstractPOJO clone() {
      Person that = new Person();
      that.setID(getID())
         .setName(getName())
         .setAge(getAge())
         .setBirthday(getBirthday())
         .setEmail(getEmail())
         .setPhone(getPhone())
         .setCity(getCity());
      return that;
   }
}