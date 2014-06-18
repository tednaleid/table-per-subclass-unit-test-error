table-per-subclass-unit-test-error
==================================

Sample project showing error that happens with unit tests, that doesn't happen with integration tests when using the table-per-subclass pattern in domain classes.

Object equality with the objects returned from the unit tests does not work.

Just run `grails test-app` to replicate the issue.

The test that passes in the integration test, but fails in the unit test is:

```
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
```

And this is the failing message only in unit tests:

```
Testcase: create person and programmer check for equality took 1.358 sec
	FAILED
Condition not satisfied:

people == [person, programmer]        // FAILS only in unit tests!
|      |   |       |
|      |   |       tablepersubclass.Programmer : 2
|      |   tablepersubclass.Person : 1
|      false
[tablepersubclass.Person : 1, tablepersubclass.Programmer : 2]
```
