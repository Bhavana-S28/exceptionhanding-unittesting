import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
// This annotation specifies the test classes that will be run in the suite
@SelectClasses({
    DataExchangeSimulatorTest.class
})
public class DataExchangeTestSuite {
    // No additional methods are needed; this class is only for grouping tests
}
