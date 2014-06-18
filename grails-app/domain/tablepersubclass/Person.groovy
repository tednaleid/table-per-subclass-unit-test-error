package tablepersubclass

class Person {
    String name

    static mapping = {
        tablePerHierarchy false
    }


    static constraints = {
    }
}
