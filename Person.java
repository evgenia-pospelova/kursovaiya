package com.company;

public class Person implements Comparable<Person> {
    private String name;
    private String surname;
    private String patronymic; //отчество
    private int clas; // класс
    private int seniority; //стаж работы
    private int salary;
    private static String sort;

    public Person(String name, String surname, String patronymic, int clas, int seniority, int salary) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.clas = clas;
        this.seniority = seniority;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getClas() {
        return clas;
    }

    public void setClas(int clas) {
        this.clas = clas;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public static void setSort(String sort) {
        Person.sort = sort;
    }

    @Override
    public int compareTo(Person o) {
        if(sort.equals("name"))
            return this.name.compareTo(o.name);
        if(sort.equals("surname"))
            return this.surname.compareTo(o.surname);
        if(sort.equals("patronymic"))
            return this.patronymic.compareTo(o.patronymic);
        if(sort.equals("clas"))
            return Integer.compare(this.clas, o.clas);
        if(sort.equals("seniority"))
            return Integer.compare(this.seniority, o.seniority);
        if(sort.equals("salary"))
            return Integer.compare(this.salary, o.salary);
        return 0;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + patronymic + " " + clas + " " + seniority + " " + salary;
    }
}
