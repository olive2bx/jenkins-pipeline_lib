import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

class LibInitTest extends BasePipelineTest{

    @Before
    void setUp(){
        super.setUp()
        def scmBranch = "feature_test"
        helper.registerAllowedMethod("sh", [Map.class], {c -> "bcc19744fc4876848f3a21aefc92960ea4c716cf"})
        binding.setVariable('scm', [
                $class                           : 'GitSCM',
                branches                         : [[name: scmBranch]]
        ])

    }

    @Test
    void should_execute_test_without_error(){
        def script = loadScript("vars/libInit.groovy")
        script.execute()
        printCallStack()
    }
}