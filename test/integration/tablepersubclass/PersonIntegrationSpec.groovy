package tablepersubclass

import spock.lang.Specification

class PersonIntegrationSpec extends Specification {

    void "create person and programmer check for equality"() {
        given:
        Person person = new Person(name: "theperson").save(failOnError: true)
        Programmer programmer = new Programmer(name: "theprogrammer", salary: 100.00).save(failOnError: true)

        when:
        List<Person> people = Person.list(sort: "name")

        then:
        assert people.id == [person, programmer].id  // passes in unit & integration tests
        assert people == [person, programmer]        // FAILS only in unit tests!
    }
}
