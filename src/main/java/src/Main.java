package src;

import src.dao.hibernate.PersonDaoHib;
import src.loader.Loader;
import src.queries.Queries;

public class Main {

    public static void main(String[] args) {
//        Loader loader = new Loader();
//        loader.load();

        Queries queries = new Queries();
        queries.conditionProjection();

    }
}
