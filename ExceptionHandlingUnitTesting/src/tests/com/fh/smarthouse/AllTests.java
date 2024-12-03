package tests.com.fh.smarthouse;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ DataExchangeSimulatorTest.class, LogFileCreatorTest.class })
public class AllTests {

}
