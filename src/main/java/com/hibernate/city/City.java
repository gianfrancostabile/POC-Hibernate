package com.hibernate.city;

import com.hibernate.abstractions.AbstractPOJO;
import com.hibernate.state.State;

import java.util.Objects;

public class City extends AbstractPOJO {
   private String code;
   private String name;
   private State state;

   public City() {

   }

   public String getCode() {
      return code;
   }

   public City setCode(String code) {
      this.code = code;
      return this;
   }

   public String getName() {
      return name;
   }

   public City setName(String name) {
      this.name = name;
      return this;
   }

   public State getState() {
      return state;
   }

   public City setState(State state) {
      this.state = state;
      return this;
   }

   @Override
   public String toString() {
      return new StringBuilder().append("City {")
         .append("code='").append(getCode()).append("'")
         .append(", name='").append(getName()).append("'")
         .append(", state=").append(getState())
         .append("}").toString();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof City)) return false;
      City city = (City) o;
      return Objects.equals(getCode(), city.getCode()) &&
         Objects.equals(getName(), city.getName()) &&
         Objects.equals(getState(), city.getState());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getCode(), getName(), getState());
   }

   @Override
   public AbstractPOJO clone() {
      City that = new City();
      return that.setCode(getCode())
         .setName(getName())
         .setState(getState());
   }
}
