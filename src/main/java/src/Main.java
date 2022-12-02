package src;

import src.dao.hibernate.PersonDaoHib;
import src.loader.Loader;

public class Main {

    public static void main(String[] args) {
        Loader loader = new Loader();
//        loader.load();

        PersonDaoHib personDaoHib = new PersonDaoHib();
        System.out.println(personDaoHib.getBySurname("Goral"));

    }
}
