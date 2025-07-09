package ru.sniping.ru;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ru.sniping.ru.test.LoginTest.class,
        ru.sniping.ru.test.CalendarPageTest.class,
        ru.sniping.ru.test.RangePageTest.class
//        ru.sniping.ru.test.RegistrationTest.class

})

public class SmokeSuite {
}
