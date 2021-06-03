package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Person> allPeople = new LinkedList<>();
    private static String inputFileName = "data.txt";
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static void printPeople(){//вывод данных в листе allPeople

        for(Person pers: allPeople) {
            System.out.println(pers.getName() + " " + pers.getSurname() + " " + pers.getPatronymic() + " "
                    + pers.getClas() + " " + pers.getSeniority() + " " + pers.getSalary());
        }
    }
    private static void insertData() throws IOException { //ввод данных
        System.out.println("Выберете, откуда вводятся данные: \n 1.С клавиатуры \n 2.Из текстового файла. \n");
        String choice = bufferedReader.readLine(); // выбор
        switch (choice){
            case "1":

                while(true) {
                    System.out.println("Введите имя сотрудника: ");
                    String name = bufferedReader.readLine();
                    System.out.println("Введите фамилию сотрудника: ");
                    String surname = bufferedReader.readLine();
                    System.out.println("Введите отчество сотрудника: ");
                    String patronymic = bufferedReader.readLine(); // отчество
                    System.out.println("Введите класс: ");
                    int clas = Integer.parseInt(bufferedReader.readLine());
                    System.out.println("Введите стаж работы: ");
                    int seniority = Integer.parseInt(bufferedReader.readLine());
                    System.out.println("Введите отклад: ");
                    int salary = Integer.parseInt(bufferedReader.readLine());
                    Person person = new Person(name, surname, patronymic, clas, seniority, salary);
                    allPeople.add(person);


                    System.out.println("Ввести еще данные вручную: \n1. Да \n2. Нет \n");
                    String choice2 = bufferedReader.readLine();
                    if("1".equals(choice2)){}
                    else {
                        System.out.println("Данные были успешно добавлены:");

                        printPeople();
                        return;
                    }


                }
            case "2":
                BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
                String str;
                ArrayList<String> list = new ArrayList<>();
                while ((str = reader.readLine())!=null){
                    if(!str.isEmpty()){
                        list.add(str);
                    }
                }
                String[] dataFile = list.toArray(new String[0]);
                for(String data: list) {
                    String [] person = data.split(" ");

                    int classf = Integer.parseInt(person[3]);
                    int seniorityf = Integer.parseInt(person[4]);
                    int salaryf = Integer.parseInt(person[5]);
                    Person personf = new Person(person[0], person[1], person[2], classf, seniorityf, salaryf);
                    allPeople.add(personf);
                }
                System.out.println("Данные были успешно добавлены!");
                printPeople();
                break;
            case "3":
                System.out.println("Работа программы завершена!");
                break;
        }
    }
    private static void sortData() throws IOException { //сортировка данных

//            System.out.println("Сортировка данных...");
            System.out.println("Сортировка по: 1. По имени\n2. По фамиилии\n3. По отчеству\n4. По классу\n5. По стажу работы\n6. По окладу\n");
            String choice = bufferedReader.readLine(); // выбор
            switch (choice){
                case "1":
                    Person.setSort("name");
                    break;
                case "2":
                    Person.setSort("surname");
                    break;
                case "3":
                    Person.setSort("patronymic");
                    break;
                case "4":
                    Person.setSort("clas");
                    break;
                case "5":
                    Person.setSort("seniority");
                    break;
                case "6":
                    Person.setSort("salary");
                    break;
            }
            List<Person> result = new ArrayList<>();
            for(Person person: allPeople){
                insert(person, result);
            }
            System.out.println("Сортировка произошла:");
            allPeople = result;
            printPeople();

    }
    private static void searchData() throws IOException{ // поиск данных
        System.out.println("Поиск по: 1. По имени\n2. По фамиилии\n3. По отчеству\n4. По классу\n5. По стажу работы\n6. По окладу\n");
        String choice1 = bufferedReader.readLine(); // выбор

        System.out.println("Введите искомое значение: ");
        String choice2 = bufferedReader.readLine(); // выбор

        int index = -1;
        switch (choice1) {
            case "1":
                Person.setSort("name");
                break;
            case "2":
                Person.setSort("surname");
                break;
            case "3":
                Person.setSort("patronymic");
                break;
            case "4":
                Person.setSort("clas");
                break;
            case "5":
                Person.setSort("seniority");
                break;
            case "6":
                Person.setSort("salary");
        }

        Collections.sort(allPeople);
        switch (choice1) {
            case "1":
                index = Collections.binarySearch(allPeople.stream().map(Person::getName).collect(Collectors.toList()), choice2);
                break;
            case "2":
                index = Collections.binarySearch(allPeople.stream().map(Person::getSurname).collect(Collectors.toList()), choice2);
                break;
            case "3":
                index = Collections.binarySearch(allPeople.stream().map(Person::getPatronymic).collect(Collectors.toList()), choice2);
                break;
            case "4":
                index = Collections.binarySearch(allPeople.stream().map(Person::getClas).collect(Collectors.toList()), Integer.valueOf(choice2));
                break;
            case "5":
                index = Collections.binarySearch(allPeople.stream().map(Person::getSeniority).collect(Collectors.toList()), Integer.valueOf(choice2));
                break;
            case "6":
                index = Collections.binarySearch(allPeople.stream().map(Person::getSalary).collect(Collectors.toList()), Integer.valueOf(choice2));
                break;
        }

        if(index >= 0)
            System.out.println(allPeople.get(index));
        else {
            System.out.println("Значение не найдено!");
        }


        }
    private static void deleteData() throws IOException { //удаление данных
        System.out.println("Выберете, какую запись нужно удалить: ");
        printPeople();
        int index = Integer.parseInt(bufferedReader.readLine()) - 1;
        if (index < allPeople.size()){
            allPeople.remove(index);
        } else {
            System.out.println("Записи с таким индексом не существует!");
        }

        System.out.println("Данные были успешно удалены: ");
        printPeople();


    }
    private static void changeData() throws IOException { //изменение данных
        System.out.println("Выберете, какую запись нужно изменить: ");
        printPeople();
        int index = Integer.parseInt(bufferedReader.readLine()) - 1;
        System.out.println("Какое поле необходимо изменить? \n 1. Имя \n 2. Фамилию \n 3. Отчество \n 4.Класс \n 5.Стаж \n 6.Оклад");
        String choice2 = bufferedReader.readLine();
        System.out.println("На какое значение нужно заменить: ");
        String res = bufferedReader.readLine();
        switch (choice2){
            case "1":
                    allPeople.get(index).setName(res);
                    break;
            case "2":
                allPeople.get(index).setSurname(res);
                break;
            case "3":
                allPeople.get(index).setPatronymic(res);
                break;
            case "4":
                allPeople.get(index).setClas(Integer.parseInt(res));
                break;
            case "5":
                allPeople.get(index).setSeniority(Integer.parseInt(res));
                break;
            case "6":
                allPeople.get(index).setSalary(Integer.parseInt(res));
                break;
        }
        System.out.println("Данные были успешно изменены! ");
        printPeople();


    }

    private static void menu() throws IOException {
        while(true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Выберете, что необходимо сделать: \n 1.Ввести исходные данные \n 2.Произвести сортировку данных \n 3.Произвести поиск данных \n 4.Изменить данные\n 5.Удалить данные \n 6.Завершить программу");

            String answer = bufferedReader.readLine();
            switch (answer) {
                case "1":
                    insertData();
                    break;
                case "2":
                    sortData();
                    break;
                case "3":
                    searchData();
                    break;
                case "4":
                    changeData();
                    break;
                case "5":
                    deleteData();
                    System.out.println();
                    break;
                case "6":
                    System.out.println("Работа программы завершена");
                    return;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        menu();

    }

    private static void insert(Person c, List<Person> result)
    {

        for (int i = 0; i < result.size(); i++)
        {
            if(result.get(i).compareTo(c) > 0  )
            {
                result.add(i,c);
                return;
            }
        }
        result.add(c);
    }


}
